package leetcode.lc089_gray_code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/gray-code/
 * #medium
 */
public final class LC089GrayCode {
    public List<Integer> grayCode(final int bitsValue) {
        int bits = bitsValue;
        if (bits == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        int shifted = 2;
        while (bits > 1) {
            bits--;
            int n = list.size();
            for (int i = n - 1; i >= 0; i--) {
                int value = list.get(i);
                list.add(shifted | value);
            }
            shifted <<= 1;
        }
        return list;
    }
}
package leetcode.lc089_gray_code;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC089GrayCodeTests {
    private int[] freeze(final List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Test
    public void test4() throws Exception {
        int[] expected = { 0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8 };
        assertArrayEquals(expected, freeze(new LC089GrayCode().grayCode(4)));
    }

    @Test
    public void test0() throws Exception {
        int[] expected = { 0 };
        assertArrayEquals(expected, freeze(new LC089GrayCode().grayCode(0)));
    }
}
```rust
fn gray_code(bits_value: i32) -> Vec<i32> {
    let bits = bits_value;
    if bits == 0 {
        vec![0]
    } else {
        let mut list = vec![0, 1];
        let mut shifted = 2;
        while bits > 1 {
            bits -= 1;
            let n = list.len();
            for i in (n - 1..0).rev() {
                let value = list[i];
                list.push(shifted | value);
            }
            shifted <<= 1;
        }
        list
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test4() {
        let expected = vec![0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8];
        assert_eq!(gray_code(4), expected);
    }

    #[test]
    fn test0() {
        let expected = vec![0];
        assert_eq!(gray_code(0), expected);
    }
}
```