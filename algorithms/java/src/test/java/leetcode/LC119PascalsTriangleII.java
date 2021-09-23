package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 */
public class LC119PascalsTriangleII {
    public class Solution {
        public List<Integer> getRow(int rowIndex) {
            rowIndex++;
            if (rowIndex < 0) {
                return null;
            }
            List<Integer> previous = null;
            List<Integer> current = null;
            for (int i = 0; i < rowIndex; i++) {
                current = new ArrayList<>();
                current.add(1);
                if (0 < i) {
                    for (int j = 0; j < i - 1; j++) {
                        current.add(previous.get(j) + previous.get(j + 1));
                    }
                    current.add(1);
                }
                previous = current;
            }
            return current;
        }
    }

    @Test
    public void test_3() throws Exception {
        int[] expected = {1, 3, 3, 1};
        List<Integer> result = new Solution().getRow(3);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }
}