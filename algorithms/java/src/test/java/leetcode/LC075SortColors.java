package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problems/sort-colors/
 */
public class LC075SortColors {
    public class Solution {
        public void sortColors(int[] nums) {
            int[] counters = {0, 0, 0};
            for (int value : nums) {
                counters[value]++;
            }
            for (int i = 0, j = 0; i < counters.length; i++) {
                for (int k = 0; k < counters[i]; k++, j++) {
                    nums[j] = i;
                }
            }
        }
    }

    @Test
    public void test_2() throws Exception {
        int[] nums = {2};
        int[] expected = {2};
        new Solution().sortColors(nums);
        assertArrayEquals(expected, nums);
    }


    @Test
    public void test_1_0() throws Exception {
        int[] nums = {1, 0};
        int[] expected = {0, 1};
        new Solution().sortColors(nums);
        assertArrayEquals(expected, nums);
    }
}
