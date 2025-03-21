package leetcode.lc173_binary_search_tree_iterator;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * #medium
 */
public final class LC173BinarySearchTreeIterator {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int x) {
            val = x;
        }
    }

    public static class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(final TreeNode rootValue) {
            TreeNode root = rootValue;
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return stack.size() != 0;
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode node = stack.pop();
            int result = node.val;
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            return result;
        }
    }
}
package leetcode.lc173_binary_search_tree_iterator;

import org.junit.jupiter.api.Test;

import leetcode.lc173_binary_search_tree_iterator.LC173BinarySearchTreeIterator.BSTIterator;
import leetcode.lc173_binary_search_tree_iterator.LC173BinarySearchTreeIterator.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class LC173BinarySearchTreeIteratorTests {
    @Test
    public void testExample() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);
        TreeNode n11 = new TreeNode(11);
        n6.left = n2;
        n6.right = n10;
        n2.left = n1;
        n2.right = n4;
        n4.left = n3;
        n4.right = n5;
        n10.left = n9;
        n10.right = n11;
        n9.left = n8;
        n8.left = n7;
        BSTIterator iterator = new BSTIterator(n6);
        int i = 1;
        while (iterator.hasNext()) {
            assertEquals(i, iterator.next());
            i++;
        }
    }

    @Test
    public void testNothing() {
        assertNotNull(new LC173BinarySearchTreeIterator());
    }
}
