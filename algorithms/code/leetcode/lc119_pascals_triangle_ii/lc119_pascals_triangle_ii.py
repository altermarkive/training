#!/usr/bin/env python3
# https://leetcode.com/problems/pascals-triangle-ii/

import unittest

from typing import List


class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        rowIndex += 1
        if rowIndex < 0:
            return None
        previous = []
        current = None
        for i in range(rowIndex):
            current = []
            current.append(1)
            if i > 0:
                for j in range(i - 1):
                    current.append(previous[j] + previous[j + 1])
                current.append(1)
            previous = current
        return current


class TestCode(unittest.TestCase):
    def test_3(self):
        expected = [1, 3, 3, 1]
        result = Solution().getRow(3)
        self.assertEqual(len(expected), len(result))
        for expected_i, result_i in zip(expected, result):
            self.assertEqual(expected_i, result_i)

    def test_nothing(self):
        self.assertIsNone(Solution().getRow(-2))