package leetcode;

import java.util.HashMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/house-robber/
 */
public class LC198HouseRobber {
    public class Solution {
        public int rob(int[] nums, int offset, HashMap<Integer, Integer> maxed) {
            if (nums.length <= offset) {
                return 0;
            }
            if (maxed.containsKey(offset)) {
                return maxed.get(offset);
            } else {
                int result = nums[offset] + rob(nums, offset + 2, maxed);
                if (offset + 1 < nums.length) {
                    int other = nums[offset + 1] + rob(nums, offset + 3, maxed);
                    result = Math.max(result, other);
                }
                maxed.put(offset, result);
                return (result);
            }
        }

        public int rob(int[] nums) {
            return rob(nums, 0, new HashMap<Integer, Integer>());
        }
    }

    @Test
    public void test_6_6_4_8_4_3_3_10() throws Exception {
        int[] nums = {6, 6, 4, 8, 4, 3, 3, 10};
        assertEquals(27, new Solution().rob(nums));
    }
}
