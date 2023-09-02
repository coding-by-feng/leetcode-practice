package me.fengorz.nk.test6;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders(TreeNode root) {
        if (root == null) {
            return new int[3][0];
        }
        List<Integer> preOrder = new LinkedList<>();
        List<Integer> inOrder = new LinkedList<>();
        List<Integer> postOrder = new LinkedList<>();
        preOrder(root, preOrder);
        inOrder(root, inOrder);
        postOrder(root, postOrder);
        int[][] result = new int[3][preOrder.size()];
        for (int i = 0; i < preOrder.size(); i++) {
            result[0][i] = preOrder.get(i);
        }
        for (int i = 0; i < inOrder.size(); i++) {
            result[1][i] = inOrder.get(i);
        }
        for (int i = 0; i < postOrder.size(); i++) {
            result[2][i] = postOrder.get(i);
        }
        return result;
    }

    private void preOrder(TreeNode node, List<Integer> order) {
        if (node == null) {
            return;
        }
        order.add(node.val);
        preOrder(node.left, order);
        preOrder(node.right, order);
    }
    private void inOrder(TreeNode node, List<Integer> order) {
        if (node == null) {
            return;
        }
        inOrder(node.left, order);
        order.add(node.val);
        inOrder(node.right, order);
    }

    private void postOrder(TreeNode node, List<Integer> order) {
        if (node == null) {
            return;
        }
        postOrder(node.left, order);
        postOrder(node.right, order);
        order.add(node.val);
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}