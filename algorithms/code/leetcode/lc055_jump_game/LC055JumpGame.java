package leetcode.lc055_jump_game;

/**
 * https://leetcode.com/problems/jump-game/
 * #medium
 */
public final class LC055JumpGame {
    public boolean canJump(final int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int front = 0;
        for (int i = 0; /*i <= front*/; i++) {
            if (front >= nums.length - 1) {
                return true;
            }
            if (i == front && nums[front] == 0) {
                return false;
            }
            if (front < i + nums[i]) {
                front = i + nums[i];
            }
        }
        // return false;
    }
}
package leetcode.lc055_jump_game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC055JumpGameTests {
    @Test
    public void test25002Integers() throws Exception {
        int[] nums1 = new int[25003];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = 25000 - i;
        }
        nums1[25000] = 1;
        nums1[25001] = 0;
        nums1[25002] = 0;
        assertFalse(new LC055JumpGame().canJump(nums1));
    }

    @Test
    public void test123() throws Exception {
        int[] nums2 = { 1, 2, 3 };
        assertTrue(new LC055JumpGame().canJump(nums2));
    }

    @Test
    public void testNothing() throws Exception {
        assertFalse(new LC055JumpGame().canJump(null));
        assertFalse(new LC055JumpGame().canJump(new int[] {}));
        assertTrue(new LC055JumpGame().canJump(new int[] { 0 }));
    }
}
