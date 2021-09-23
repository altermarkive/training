package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 */
public class LC081SearchInRotatedSortedArrayII {
    public class Solution {
        public boolean search(int[] nums, int target) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] > nums[i]) {
                    boolean ante = Arrays.binarySearch(nums, 0, i, target) >= 0;
                    boolean post = Arrays.binarySearch(nums, i, nums.length, target) >= 0;
                    return ante || post;
                }
            }
            return Arrays.binarySearch(nums, 0, nums.length, target) >= 0;
        }
    }

    @Test
    public void test_simple_example() {
        int[] nums = {4, 5, 6, 6, 7, 0, 1, 2};
        assertEquals(true, new Solution().search(nums, 1));
    }

    @Test
    public void test_tricky_example() {
        int[] nums = {1, 1, 3, 1, 1, 1, 1, 1};
        assertEquals(false, new Solution().search(nums, 2));
    }
}
