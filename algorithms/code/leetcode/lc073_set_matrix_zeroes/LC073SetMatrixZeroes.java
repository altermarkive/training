package leetcode.lc073_set_matrix_zeroes;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * #medium
 */
public final class LC073SetMatrixZeroes {
    public void setZeroes(final int[][] matrix) {
        boolean row0 = false;
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                row0 = true;
            }
        }
        boolean col0 = false;
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                col0 = true;
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        for (int row = 0; row0 && row < matrix.length; row++) {
            matrix[row][0] = 0;
        }
        for (int col = 0; col0 && col < matrix[0].length; col++) {
            matrix[0][col] = 0;
        }
    }
}
package leetcode.lc073_set_matrix_zeroes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC073SetMatrixZeroesTests {
    public void test(final int[][] expected, final int[][] matrix) throws Exception {
        assertEquals(expected.length, matrix.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], matrix[row]);
        }
    }

    @Test
    public void testSmallerExample1() throws Exception {
        int[][] matrix = { { 1, 0 } };
        int[][] expected = { { 0, 0 } };
        new LC073SetMatrixZeroes().setZeroes(matrix);
        test(expected, matrix);
    }

    @Test
    public void testSmallerExample2() throws Exception {
        int[][] matrix = { { 0, 1 } };
        int[][] expected = { { 0, 0 } };
        new LC073SetMatrixZeroes().setZeroes(matrix);
        test(expected, matrix);
    }

    @Test
    public void testBiggerExample() throws Exception {
        int[][] matrix = { { 0, 0, 0, 5 }, { 4, 3, 1, 4 }, { 0, 1, 1, 4 }, { 1, 2, 1, 3 }, { 0, 0, 1, 1 } };
        int[][] expected = { { 0, 0, 0, 0 }, { 0, 0, 0, 4 }, { 0, 0, 0, 0 }, { 0, 0, 0, 3 }, { 0, 0, 0, 0 } };
        new LC073SetMatrixZeroes().setZeroes(matrix);
        test(expected, matrix);
    }

    @Test
    public void testOther() throws Exception {
        int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[][] expected = { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } };
        new LC073SetMatrixZeroes().setZeroes(matrix);
        test(expected, matrix);
    }
}
