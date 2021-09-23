package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class LC334IncreasingTripletSubsequence {
    public class Solution {
        public boolean increasingTriplet(int[] nums) {
            if (nums == null || nums.length < 3) return false;
            int[] minBefore = new int[nums.length];
            int[] maxAfter = new int[nums.length];
            minBefore[0] = nums[0];
            maxAfter[nums.length - 1] = nums[nums.length - 1];
            for (int i = 1; i < nums.length - 1; i++) {
                minBefore[i] = Math.min(minBefore[i - 1], nums[i - 1]);
                maxAfter[nums.length - 1 - i] = Math.max(maxAfter[nums.length - i], nums[nums.length - i]);
            }
            for (int i = 1; i < nums.length - 1; i++) {
                if (minBefore[i] < nums[i] && nums[i] < maxAfter[i]) {
                    return true;
                }
            }
            return false;
        }
    }

    @Test
    public void test_empty() throws Exception {
        assertEquals(false, new Solution().increasingTriplet(new int[]{}));
    }

    @Test
    public void test_example_1() throws Exception {
        assertEquals(true, new Solution().increasingTriplet(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void test_example_2() throws Exception {
        assertEquals(false, new Solution().increasingTriplet(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void test_other() throws Exception {
        assertEquals(true, new Solution().increasingTriplet(new int[]{1, 2, 3, 1, 2, 1}));
    }
}
