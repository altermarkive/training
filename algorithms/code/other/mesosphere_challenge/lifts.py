#!/usr/bin/env python3

import collections
import math
import random
import sys
import unittest
from abc import ABC, abstractmethod


def direction(origin, goal):
    if goal > origin:
        return 1
    if goal < origin:
        return -1
    return 0


def towards(lift, floor):
    if lift.floor == floor:
        return True
    orientation = direction(lift.floor, floor)
    return orientation == lift.direction


def max_distance(lift, floor, floor_cnt):
    apart = abs(lift.floor - floor)
    if towards(lift, floor):
        return apart
    return 2 * (floor_cnt - 1) - apart


class Request:
    def __init__(self, origin, goal):
        self.origin = origin
        self.goal = goal
        self.waited = 0
        self.ridden = 0

    def waiting(self):
        self.waited += 1

    def riding(self):
        self.ridden += 1

    def show(self):  # pragma: no cover
        if self is None:
            return '()'
        return '(%d->%d)' % (self.origin, self.goal)


class Lift:
    def __init__(self, capacity):
        self.capacity = capacity
        self.floor = 0
        self.passengers = []
        self.direction = 0

    def enter(self, request):
        self.passengers.append(request)

    def leave(self, really=False):
        leaving = []
        staying = []
        for passenger in self.passengers:
            which = leaving if passenger.goal == self.floor else staying
            which.append(passenger)
        if really:
            self.passengers = staying
        return leaving

    def empty(self):
        return not self.passengers

    def full(self):
        return len(self.passengers) >= self.capacity

    def step(self):
        if self.direction != 0:
            for passenger in self.passengers:
                passenger.riding()


class ElevatorControlSystemBase(ABC):
    def __init__(self, lift_cnt, floor_cnt, capacity):
        self.lift_cnt = lift_cnt
        self.floor_cnt = floor_cnt
        self.capacity = capacity
        self.lifts = [Lift(capacity) for i in range(lift_cnt)]
        self.through = []
        self.cycles = 0

    def dropoff(self, request):
        self.through.append(request)

    @abstractmethod
    def step_lift(self, i, lift, left):  # pragma: no cover
        pass

    @abstractmethod
    def step_waiting(self):  # pragma: no cover
        pass

    def step(self):
        for i, lift in enumerate(self.lifts):
            # Let passengers out
            left = lift.leave(True)
            # Update statistics for each leaving passenger
            for each in left:
                self.dropoff(each)
            # Run the logic
            self.step_lift(i, lift, left)
            # Update
            lift.floor += lift.direction
            # Update statistics for the riding passengers
            lift.step()
        # Update statistics for the waiting passengers
        self.step_waiting()
        # Update number of cycles
        self.cycles += 1

    def show(self):  # pragma: no cover
        for i in range(self.floor_cnt - 1, -1, -1):
            line = ''
            for lift in self.lifts:
                if lift.floor == i:
                    line += str(len(lift.passengers))
                else:
                    line += '.'
            print(line)
        lut = {-1: 'v', 0: '-', 1: '^'}
        print(''.join([lut[lift.direction] for lift in self.lifts]))
        print('')
        for i, lift in enumerate(self.lifts):
            items = [request.show() for request in lift.passengers]
            print('L%d %s' % (i, ' '.join(items)))

    def stats(self):
        through = self.through
        result = {}
        result['throughput'] = len(through) / self.cycles
        items = [request.waited for request in through]
        avg_wait = sum(items) / self.cycles
        items = [math.pow(entry.waited - avg_wait, 2) for entry in through]
        std_wait = math.sqrt(sum(items) / self.cycles)
        result['avg_wait_time'] = avg_wait
        result['std_wait_time'] = std_wait
        items = [request.ridden for request in through]
        avg_ride = sum(items) / self.cycles
        items = [math.pow(entry.ridden - avg_wait, 2) for entry in through]
        std_ride = math.sqrt(sum(items) / self.cycles)
        result['avg_ride_time'] = avg_ride
        result['std_ride_time'] = std_ride
        return result


class FCFS(ElevatorControlSystemBase):
    def __init__(self, lift_cnt, floor_cnt, capacity):
        ElevatorControlSystemBase.__init__(self, lift_cnt, floor_cnt, capacity)
        self.queue = collections.deque()
        self.assignments = [None for i in range(self.lift_cnt)]

    def pickup(self, request):
        self.queue.append(request)

    def step_lift(self, i, lift, left):
        assignment = self.assignments[i]
        if lift.empty():
            if assignment is None:
                if self.queue:
                    assignment = self.queue.popleft()
                    self.assignments[i] = assignment
                    lift.direction = direction(lift.floor, assignment.origin)
                else:
                    lift.direction = 0
            else:
                if lift.floor == assignment.origin:
                    lift.enter(assignment)
                    self.assignments[i] = None
                    lift.direction = direction(lift.floor, assignment.goal)

    def step_waiting(self):
        for request in self.queue:
            request.waiting()

    def show(self):  # pragma: no cover
        ElevatorControlSystemBase.show(self)
        items = [Request.show(assignment) for assignment in self.assignments]
        print('A %s' % ' '.join(items))
        print('Q %s' % ' '.join([request.show() for request in self.queue]))


class ElevatorControlSystem(ElevatorControlSystemBase):
    def __init__(self, lift_cnt, floor_cnt, capacity):
        ElevatorControlSystemBase.__init__(self, lift_cnt, floor_cnt, capacity)
        self.queues = [[] for i in range(lift_cnt)]

    def pickup(self, request):
        origin = request.origin
        goal = request.goal
        available = []
        # Sort by distance the lifts which are approaching and head
        # in the same direction
        for i, lift in enumerate(self.lifts):
            idle = lift.direction == 0
            along = direction(origin, goal) == lift.direction
            approaching = towards(lift, origin)
            if idle or (along and approaching):
                available.append(i)
        available = sorted(
            available, key=lambda i: abs(self.lifts[i].floor - origin)
        )
        # Otherwise sort all lifts by distance
        if len(available) == 0:
            available = sorted(
                range(self.lift_cnt),
                key=lambda i: max_distance(
                    self.lifts[i], origin, self.floor_cnt
                ),
            )
        # Pick closest lift
        self.queues[available[0]].append(request)

    def step_lift(self, i, lift, left):
        # Let passengers in
        for request in self.queues[i][:]:
            heading_direction = direction(request.origin, request.goal)
            if lift.empty():
                if request.origin != lift.floor:
                    heading_direction = direction(lift.floor, request.origin)
                lift.direction = heading_direction
            if request.origin == lift.floor:
                self.queues[i].remove(request)
                if heading_direction == lift.direction and not lift.full():
                    lift.passengers.append(request)
                else:
                    self.pickup(request)
        # If empty then set idle
        if lift.empty() and len(self.queues[i]) == 0:
            lift.direction = 0

    def step_waiting(self):
        for queue in self.queues:
            for request in queue:
                request.waiting()

    def show(self):  # pragma: no cover
        ElevatorControlSystemBase.show(self)
        for i, _ in enumerate(self.lifts):
            q = ' '.join([request.show() for request in self.queues[i]])
            print('Q%d %s' % (i, q))


def simulation(
    system, floor_cnt, request_probability, cycles=None
):  # pragma: no cover
    rng = random.SystemRandom()
    interactive = cycles is None
    while interactive or cycles > 0:
        if not interactive and cycles > 0:
            cycles -= 1
        if interactive:
            system.show()
        if rng.random() < request_probability:
            pickup_floor = rng.randint(0, floor_cnt - 1)
            goal_floor = rng.randint(0, floor_cnt - 1)
            if pickup_floor != goal_floor:
                system.pickup(Request(pickup_floor, goal_floor))
        system.step()
        if interactive:
            input()
    return system.stats()


class Tests(unittest.TestCase):
    def test_randomized(self):
        lift_cnt = 4
        floor_cnt = 14
        capacity = 1
        request_probability = 1
        system = FCFS(lift_cnt, floor_cnt, capacity)
        fcfs_stats = simulation(system, floor_cnt, request_probability, 5000)
        system = ElevatorControlSystem(lift_cnt, floor_cnt, capacity)
        ecs_stats = simulation(system, floor_cnt, request_probability, 5000)
        fcfs_avg_wait = fcfs_stats['avg_wait_time']
        ecs_avg_wait = ecs_stats['avg_wait_time']
        fcfs_std_wait = fcfs_stats['std_wait_time']
        ecs_std_wait = ecs_stats['std_wait_time']
        self.assertTrue(ecs_avg_wait < fcfs_avg_wait)
        self.assertTrue(ecs_std_wait < fcfs_std_wait)


if __name__ == '__main__':  # pragma: no cover
    if len(sys.argv) > 1:
        sys.argv = [sys.argv[0]]
        # Run unattended test to gather statistics
        unittest.main()
    else:
        # Run interactive test
        main_lift_cnt = 4
        main_floor_cnt = 14
        main_capacity = 4
        main_request_probability = 0.75
        main_system = ElevatorControlSystem(
            main_lift_cnt, main_floor_cnt, main_capacity
        )
        simulation(main_system, main_floor_cnt, main_request_probability, None)
