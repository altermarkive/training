package leetcode.lc230_kth_smallest_element_in_a_bst;

import org.junit.jupiter.api.Test;

import leetcode.lc230_kth_smallest_element_in_a_bst.LC230KthSmallestElementInABST.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC230KthSmallestElementInABSTTests {
    @Test
    public void testLeft() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n4.left = n3;
        n3.left = n2;
        n2.left = n1;
        assertEquals(2, new LC230KthSmallestElementInABST().kthSmallest(n4, 2));
    }

    @Test
    public void testRight() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        assertEquals(2, new LC230KthSmallestElementInABST().kthSmallest(n1, 2));
    }

    @Test
    public void testCoverage() throws Exception {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n2.left = n1;
        n2.right = n3;
        n4.left = n2;
        n4.right = n5;
        n5.right = n6;
        assertEquals(5, new LC230KthSmallestElementInABST().kthSmallest(n4, 5));
    }
}