package leetcode.lc342_power_of_four;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC342PowerOfFourTests {
    @Test
    public void test16() throws Exception {
        assertTrue(new LC342PowerOfFour().isPowerOfFour(16));
    }

    @Test
    public void test5() throws Exception {
        assertFalse(new LC342PowerOfFour().isPowerOfFour(5));
    }

    @Test
    public void testNonPositive() throws Exception {
        assertFalse(new LC342PowerOfFour().isPowerOfFour(0));
        assertFalse(new LC342PowerOfFour().isPowerOfFour(-1));
    }
}