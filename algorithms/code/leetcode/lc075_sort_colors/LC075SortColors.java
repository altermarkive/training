package leetcode.lc075_sort_colors;

/**
 * https://leetcode.com/problems/sort-colors/
 * #medium
 */
public final class LC075SortColors {
    public void sortColors(final int[] nums) {
        int[] counters = { 0, 0, 0 };
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
package leetcode.lc075_sort_colors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC075SortColorsTests {
    @Test
    public void test2() throws Exception {
        int[] nums = { 2 };
        int[] expected = { 2 };
        new LC075SortColors().sortColors(nums);
        assertArrayEquals(expected, nums);
    }

    @Test
    public void test10() throws Exception {
        int[] nums = { 1, 0 };
        int[] expected = { 0, 1 };
        new LC075SortColors().sortColors(nums);
        assertArrayEquals(expected, nums);
    }
}
