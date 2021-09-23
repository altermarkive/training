package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/nim-game/
 */
public class LC292NimGame {
    public class Solution {
        public boolean canWinNim(int n) {
            return n % 4 != 0;
        }
    }

    @Test
    public void test_1to10() throws Exception {
        assertEquals(true, new Solution().canWinNim(1));  // +  1 o
        assertEquals(true, new Solution().canWinNim(2));  // +  2 o o
        assertEquals(true, new Solution().canWinNim(3));  // +  3 o o o
        assertEquals(false, new Solution().canWinNim(4)); // -  4 o ? ? x
        assertEquals(true, new Solution().canWinNim(5));  // +  5 o x ? ? o
        assertEquals(true, new Solution().canWinNim(6));  // +  6 o o x ? ? o
        assertEquals(true, new Solution().canWinNim(7));  // +  7 o o o x ? ? o
        assertEquals(false, new Solution().canWinNim(8)); // -  8 o ? ? . . . . x     (leads to 7, 6 or 5)
        assertEquals(true, new Solution().canWinNim(9));  // +  9 o x ? ? . . . . o   (leads to 8)
        assertEquals(true, new Solution().canWinNim(10)); // + 10 o o x ? ? . . . . o (leads to 8)
    }
}
