package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class LC237DeleteNodeInALinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public void deleteNode(ListNode node) {
            while (node != null) {
                if (node.next != null) {
                    node.val = node.next.val;
                    if (node.next.next == null) {
                        node.next = null;
                    }
                }
                node = node.next;
            }
        }
    }

    @Test
    public void test_example() throws Exception {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        new Solution().deleteNode(n3);
        int[] expected = {1, 2, 4};
        ListNode n = n1;
        for (int value : expected) {
            assertNotEquals(null, n);
            assertEquals(value, n.val);
            n = n.next;
        }
        assertEquals(null, n);
    }
}
