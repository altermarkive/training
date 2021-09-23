package leetcode;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class LC042TrappingRainWater {
    public class Solution {
        private class MappingComparator implements Comparator<Integer> {
            private int[] other;

            public MappingComparator(int[] other) {
                this.other = other;
            }

            public int compare(Integer i1, Integer i2) {
                return other[i2] - other[i1];
            }
        }

        private int amount(int[] height, int from, int to) {
            int amount = Math.min(height[from], height[to]) * (to - from - 1);
            for (int i = from + 1; i < to; i++) {
                amount -= height[i];
            }
            return amount;
        }

        public int trap(int[] height) {
            if (height == null || height.length < 3) {
                return 0;
            }
            // Sort the terrain
            Integer[] sorted = new Integer[height.length];
            for (int i = 0; i < height.length; i++) {
                sorted[i] = i;
            }
            Arrays.sort(sorted, new MappingComparator(height));
            // Fill from the top
            // (pick highest and then extend "exclusion zone"
            int count = 0;
            int left = sorted[0], right = sorted[0];
            for (int i : sorted) {
                if (right < i) {
                    count += amount(height, right, i);
                    right = i;
                }
                if (i < left) {
                    count += amount(height, i, left);
                    left = i;
                }
            }
            return count;
        }
    }

    @Test
    public void test_0_1_0_2_1_0_1_3_2_1_2_1() throws Exception {
        int[] terrain = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        assertEquals(6, new Solution().trap(terrain));
    }
}
