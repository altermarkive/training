package leetcode.lc074_search_a_2d_matrix;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * #medium
 */
public final class LC074SearchA2DMatrix {
    public boolean searchMatrix(final int[][] matrix, final int target) {
        int ra = 0;
        int rz = matrix.length - 1;
        while (ra < rz) {
            int rm = 1 + (ra + rz) / 2;
            if (target >= matrix[rm][0]) {
                ra = rm;
            } else {
                rz = rm - 1;
            }
        }
        // if (rz < 0) {
        //     return false;
        // }
        int ca = 0;
        int cz = matrix[ra].length - 1;
        while (ca < cz) {
            int cm = 1 + (ca + cz) / 2;
            if (target == matrix[ra][cm]) {
                return true;
            }
            if (target > matrix[ra][cm]) {
                ca = cm + 1;
            } else {
                cz = cm - 1;
            }
        }
        return ca == cz && target == matrix[ra][ca];
    }
}
package leetcode.lc074_search_a_2d_matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC074SearchA2DMatrixTests {
    @Test
    public void testExample1() throws Exception {
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 50 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 3));
    }

    @Test
    public void testExample2() throws Exception {
        int[][] matrix = {
                { 1 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 1));
    }

    @Test
    public void testExample3() throws Exception {
        int[][] matrix = {
                { 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 0));
    }

    @Test
    public void testExample4() throws Exception {
        int[][] matrix = {
                { 1, 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 0));
    }

    @Test
    public void testExample5() throws Exception {
        int[][] matrix = {
                { 1, 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 2));
    }

    @Test
    public void testExample6() throws Exception {
        int[][] matrix = {
                { 1 },
                { 3 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 1));
    }

    @Test
    public void testOther() throws Exception {
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 13));
    }
}
