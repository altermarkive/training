package leetcode.lc238_product_of_array_except_self;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * #medium
 */
public final class LC238ProductOfArrayExceptSelf {
    public int[] productExceptSelf(final int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int other = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            other *= nums[i + 1];
            result[i] *= other;
        }
        return result;
    }
}
package leetcode.lc238_product_of_array_except_self;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC238ProductOfArrayExceptSelfTests {
    @Test
    public void test1234() throws Exception {
        int[] nums = { 1, 2, 3, 4 };
        int[] expected = { 24, 12, 8, 6 };
        int[] actual = new LC238ProductOfArrayExceptSelf().productExceptSelf(nums);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void test90Minus2() throws Exception {
        int[] nums = { 9, 0, -2 };
        int[] expected = { 0, -18, 0 };
        int[] actual = new LC238ProductOfArrayExceptSelf().productExceptSelf(nums);
        assertArrayEquals(expected, actual);
    }
}
```rust
fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
    // Initialize result with ones
    let mut result = vec![1; nums.len()];
    
    // Calculate products of all elements to the left
    for i in 1..nums.len() {
        result[i] = result[i - 1] * nums[i - 1];
    }
    
    // Calculate product of all elements except current one and update result
    let mut other = 1;
    for i in (0..nums.len()).rev() {
        other *= nums[i + 1];
        result[i] *= other;
    }
    return result;
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test1234() {
        let nums = vec![1, 2, 3, 4];
        let expected = vec![24, 12, 8, 6];
        assert_eq!(product_except_self(nums), expected);
    }

    #[test]
    fn test90Minus2() {
        let nums = vec![9, 0, -2];
        let expected = vec![0, -18, 0];
        assert_eq!(product_except_self(nums), expected);
    }
}
```