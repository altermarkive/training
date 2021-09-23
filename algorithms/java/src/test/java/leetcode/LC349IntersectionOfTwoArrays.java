package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/
 */
public class LC349IntersectionOfTwoArrays {
    public class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            Set<Integer> found = new HashSet<>();
            for (int i1 = 0, i2 = 0; i1 < nums1.length && i2 < nums2.length; ) {
                if (nums1[i1] < nums2[i2]) {
                    i1++;
                    continue;
                }
                if (nums1[i1] > nums2[i2]) {
                    i2++;
                    continue;
                }
                found.add(nums1[i1]);
                i1++;
                i2++;
            }
            int[] result = new int[found.size()];
            int index = 0;
            for (int value : found) {
                result[index] = value;
                index++;
            }
            return result;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result = new Solution().intersection(nums1, nums2);
        Arrays.sort(result);
        int[] expected = {2};
        assertArrayEquals(expected, result);
    }
}
