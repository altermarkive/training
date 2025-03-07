package leetcode.lc203_remove_linked_list_elements;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/ #easy
 */
public final class LC203RemoveLinkedListElements {
    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public ListNode removeElements(final ListNode headNode, final int val) {
        ListNode head = headNode;
        if (head == null) {
            return null;
        }
        ListNode previous = null;
        ListNode node = head;
        while (node != null) {
            if (node.val == val) {
                if (previous == null) {
                    head = node.next;
                } else {
                    previous.next = node.next;
                }
            } else {
                previous = node;
            }
            node = node.next;
        }
        return head;
    }
}
package leetcode.lc203_remove_linked_list_elements;

import org.junit.jupiter.api.Test;

import leetcode.lc203_remove_linked_list_elements.LC203RemoveLinkedListElements.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC203RemoveLinkedListElementsTests {
    public ListNode convert(final int[] array) {
        ListNode list = null;
        ListNode last = null;
        for (int value : array) {
            ListNode node = new ListNode(value);
            node.next = null;
            if (list == null) {
                list = node;
            } else {
                last.next = node;
            }
            last = node;
        }
        return list;
    }

    @Test
    public void test612345676And6() throws Exception {
        int[] array = { 6, 1, 2, 3, 4, 5, 6, 7, 6 };
        ListNode list = convert(array);
        list = new LC203RemoveLinkedListElements().removeElements(list, 6);
        int[] expected = { 1, 2, 3, 4, 5, 7 };
        for (int value : expected) {
            assertEquals(value, list.val);
            list = list.next;
        }
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC203RemoveLinkedListElements().removeElements(null, 0));
    }
}
