// https://leetcode.com/problems/two-sum/

using System.Collections.Generic;

namespace AlgorithmDesign.leetcode.lc001_two_sum
{
    public class Solution
    {
        public int[] TwoSum(int[] nums, int target)
        {
            if (nums == null)
            {
                return null;
            }
            Dictionary<int, int> map = new Dictionary<int, int>();
            for (int i = 0; i < nums.Length; i++)
            {
                int expected = target - nums[i];
                if (map.ContainsKey(expected))
                {
                    int found = map[expected];
                    return new[] { found, i };
                }
                else
                {
                    map[nums[i]] = i;
                }
            }
            return null;
        }
    }
}
