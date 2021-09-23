package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 */
public class LC172FactorialTrailingZeroes {
    public class Solution {
        public int trailingZeroes(int n) {
            long step = 5;
            long count = 0;
            while (step <= n) {
                count += n / step;
                step = step * 5;
            }
            return (int) count;
        }
    }

    @Test
    public void test_5() throws Exception {
        assertEquals(1, new Solution().trailingZeroes(5));
    }

    @Test
    public void test_1808548329() throws Exception {
        assertEquals(452137076, new Solution().trailingZeroes(1808548329));
    }

    @Test
    public void test_2147483647() throws Exception {
        assertEquals(536870902, new Solution().trailingZeroes(2147483647));
    }
}
