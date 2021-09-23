package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/partition-list/
 */
public class LC086PartitionList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ListNode partition(ListNode head, int x) {
            if (head == null) {
                return null;
            }
            ListNode less = new ListNode(0);
            ListNode more = new ListNode(0);
            ListNode before = less;
            ListNode after = more;
            while (head != null) {
                if (head.val < x) {
                    less.next = head;
                    less = head;
                }
                if (head.val >= x) {
                    more.next = head;
                    more = head;
                }
                head = head.next;
            }
            less.next = after.next;
            more.next = null;
            return before.next;
        }
    }

    private ListNode build(int[] array) {
        ListNode head = null;
        ListNode tail = null;
        for (int value : array) {
            if (head == null) {
                head = tail = new ListNode(value);
            } else {
                tail.next = new ListNode(value);
                tail = tail.next;
            }
        }
        if (tail != null) {
            tail.next = null;
        }
        return head;
    }

    @Test
    public void test_1_4_3_2_5_2__3() throws Exception {
        ListNode list = build(new int[]{1, 4, 3, 2, 5, 2});
        int[] expected = {1, 2, 2, 4, 3, 5};
        ListNode result = new Solution().partition(list, 3);
        for (int value : expected) {
            assertEquals(value, result.val);
            result = result.next;
        }
    }
}
