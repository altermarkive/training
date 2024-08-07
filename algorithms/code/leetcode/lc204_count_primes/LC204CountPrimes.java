package leetcode.lc204_count_primes;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/count-primes/
 * #medium
 */
public final class LC204CountPrimes {
    public int countPrimes(final int n) {
        if (n < 2) {
            return 0;
        }
        // Eratosthenes sieve
        boolean[] sieve = new boolean[n - 2];
        Arrays.fill(sieve, true);
        int count = 0;
        for (int i = 0; i < sieve.length; i++) {
            if (!sieve[i]) {
                continue;
            }
            count++;
            int number = 2 + i;
            for (int j = i + number; j < sieve.length; j += number) {
                sieve[j] = false;
            }
        }
        return count;
    }
}
