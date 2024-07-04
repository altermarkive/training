#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/clique
# See also: https://en.wikipedia.org/wiki/Tur%C3%A1n_graph
# #python

import io
import sys
import unittest


def calculate(x, n):
    n_2 = n * n
    mod = n % x
    up = n // x + 1
    dn = n // x
    up_2 = up * up
    dn_2 = dn * dn
    return (n_2 - mod * up_2 - (x - mod) * dn_2) // 2


def find_largest_possible_clique(n, m):
    a = 0
    z = n + 1
    while a + 1 < z:
        x = (a + z) // 2
        if calculate(x, n) < m:
            a = x
        else:
            z = x
    return z


def main():
    t = int(input().strip())
    for _ in range(t):
        n, m = list(map(int, input().strip().split()))
        print(find_largest_possible_clique(n, m))


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
