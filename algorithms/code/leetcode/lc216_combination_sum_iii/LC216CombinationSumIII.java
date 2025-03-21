package leetcode.lc216_combination_sum_iii;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * #medium
 */
public final class LC216CombinationSumIII {
    private void traverse(final int contains, final int sum, final int left, final int n, final List<Integer> found,
            final int start) {
        if (left == 0 && sum == n) {
            found.add(contains);
        } else {
            for (int i = start; i <= 9; i++) {
                int mask = 1 << i;
                // if ((contains & mask) == 0) {
                traverse(contains | mask, sum + i, left - 1, n, found, i + 1);
                // }
            }
        }
    }

    public List<List<Integer>> combinationSum3(final int k, final int n) {
        List<Integer> found = new ArrayList<>();
        traverse(0, 0, k, n, found, 1);
        List<List<Integer>> all = new ArrayList<>();
        for (int contains : found) {
            List<Integer> entry = new ArrayList<>();
            all.add(entry);
            for (int i = 1; i <= 9; i++) {
                int mask = 1 << i;
                if ((contains & mask) != 0) {
                    entry.add(i);
                }
            }
        }
        return all;
    }
}
package leetcode.lc216_combination_sum_iii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

public final class LC216CombinationSumIIITests {
    private void test(final int[][] expected, final List<List<Integer>> result) {
        for (List<Integer> entry : result) {
            Collections.sort(entry);
        }
        // Collections.sort(result, );
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            List<Integer> entry = result.get(i);
            assertEquals(expected[i].length, entry.size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], entry.get(j).intValue());
            }
        }
    }

    @Test
    public void test37() throws Exception {
        int[][] expected = { { 1, 2, 4 } };
        test(expected, new LC216CombinationSumIII().combinationSum3(3, 7));
    }

    @Test
    public void test39() throws Exception {
        int[][] expected = { { 1, 2, 6 }, { 1, 3, 5 }, { 2, 3, 4 } };
        test(expected, new LC216CombinationSumIII().combinationSum3(3, 9));
    }
}
