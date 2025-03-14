package leetcode.lc059_spiral_matrix_ii;

/**
 * https://leetcode.com/problems/spiral-matrix-ii/
 * #medium
 */
public final class LC059SpiralMatrixII {
    public int[][] generateMatrix(final int n) {
        int[] limits = { n - 1, n - 1, 0, 0 };
        int[] restrict = { 1, -1, -1, 1 };
        int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int[] indices = { 0, -1 };
        int[][] matrix = new int[n][n];
        int value = 1;
        int stage = 0;
        int index = 1;
        while (/*limits[0] >= limits[2] && */limits[1] >= limits[3]) {
            do {
                indices[0] += delta[stage][0];
                indices[1] += delta[stage][1];
                matrix[indices[0]][indices[1]] = value++;
            } while (indices[index] != limits[stage]);
            limits[(stage + 3) % 4] += restrict[stage];
            stage = (stage + 1) % 4;
            index = (index + 1) % 2;
        }
        return matrix;
    }
}
package leetcode.lc059_spiral_matrix_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class LC059SpiralMatrixIITests {
    @Test
    public void testExample() throws Exception {
        int[][] expected = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
        int[][] result = new LC059SpiralMatrixII().generateMatrix(3);
        assertNotEquals(null, result);
        assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }
}
