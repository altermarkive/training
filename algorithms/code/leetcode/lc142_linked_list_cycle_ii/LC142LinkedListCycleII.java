package leetcode.lc142_linked_list_cycle_ii;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * #medium
 */
public final class LC142LinkedListCycleII {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(final ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                break;
            }
        }
        if (p2 == null || p2.next == null) {
            return null;
        }
        p1 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
package leetcode.lc142_linked_list_cycle_ii;

import org.junit.jupiter.api.Test;

import leetcode.lc142_linked_list_cycle_ii.LC142LinkedListCycleII.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC142LinkedListCycleIITests {
    @Test
    public void testExample() throws Exception {
        ListNode a1 = new ListNode(0xA1);
        ListNode a2 = new ListNode(0xA2);
        ListNode b1 = new ListNode(0xB1);
        ListNode b2 = new ListNode(0xB2);
        ListNode b3 = new ListNode(0xB3);
        ListNode c1 = new ListNode(0xC1);
        ListNode c2 = new ListNode(0xC2);
        ListNode c3 = new ListNode(0xC3);
        a1.next = a2;
        a2.next = c1;
        b1.next = b2;
        b2.next = b3;
        b3.next = c1;
        c1.next = c2;
        c2.next = c3;
        c3.next = b1;
        assertEquals(c1, new LC142LinkedListCycleII().detectCycle(a1));
        assertEquals(0xA1, a1.val);
        assertEquals(0xA2, a2.val);
        assertEquals(0xB1, b1.val);
        assertEquals(0xB2, b2.val);
        assertEquals(0xB3, b3.val);
        assertEquals(0xC1, c1.val);
        assertEquals(0xC2, c2.val);
        assertEquals(0xC3, c3.val);
    }

    @Test
    public void testOneAndMinusOne() throws Exception {
        ListNode node = new ListNode(1);
        assertNull(new LC142LinkedListCycleII().detectCycle(node));
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC142LinkedListCycleII().detectCycle(null));
    }
}
