#!/usr/bin/env python3
# https://leetcode.com/problems/permutations-ii/

import unittest

from collections import Counter
from typing import List


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        # Count each number
        counted = Counter(nums)
        # Generate the permutations
        permutations = []
        self.generate([], len(nums), counted, permutations)
        return permutations

    def generate(self, permutation, limit, counted, permutations):
        if len(permutation) == limit:
            permutations.append(permutation.copy())
            return
        for key in counted.keys():
            count = counted.get(key)
            if count != 0:
                permutation.append(key)
                counted[key] -= 1
                self.generate(permutation, limit, counted, permutations)
                counted[key] = count
                permutation.pop()


class TestCode(unittest.TestCase):
    def test_example(self):
        nums = [1, 1, 2]
        result = Solution().permuteUnique(nums)
        result = sorted(result)
        expected = [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
        self.assertEqual(len(result), len(expected))
        for i, _ in enumerate(expected):
            self.assertEqual(len(result[i]), len(expected[i]))
            for j, _ in enumerate(expected[i]):
                self.assertEqual(result[i][j], expected[i][j])