#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/standardize-mobile-number-using-decorators
# #python

import io
import sys
import unittest


def wrapper(f):
    def fun(numbers):
        numbers = [f'+91 {number[-10:-5]} {number[-5:]}' for number in numbers]
        f(numbers)

    return fun


@wrapper
def sort_phone(numbers):
    print(*sorted(numbers), sep='\n')


def main():
    n = int(input().strip())
    numbers = [input() for _ in range(n)]
    sort_phone(numbers)


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
        with (
            open(
                __file__.replace('.py', f'.{which}.out'), 'r', encoding='utf-8'
            ) as expected,
            open(
                __file__.replace('.py', f'.{which}.in'), 'r', encoding='utf-8'
            ) as sys.stdin,
            io.StringIO() as sys.stdout,
        ):
            main()
            self.assertEqual(sys.stdout.getvalue(), expected.read())

    def test_0(self):
        self.generalized_test('0')
