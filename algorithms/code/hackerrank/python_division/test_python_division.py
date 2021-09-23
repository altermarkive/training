#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-division

import io
import sys
import unittest


def divide(first, second):
    int_val = first // second
    float_val = first / second
    return int_val, float_val


def main():
    first_in = int(input().strip())
    second_in = int(input().strip())
    int_out, float_out = divide(first_in, second_in)
    print(int_out)
    print(float_out)


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
        sys.stdin = open(__file__.replace('.py', f'.{which}.in'), 'r')
        sys.stdout = io.StringIO()
        expected = open(__file__.replace('.py', f'.{which}.out'), 'r')
        main()
        self.assertEqual(sys.stdout.getvalue(), expected.read())
        for handle in [sys.stdin, sys.stdout, expected]:
            handle.close()

    def test_0(self):
        self.generalized_test('0')
