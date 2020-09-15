package me.fengorz.leetcode.tree.minimum_distance_between_bst_nodes;
//给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。
//
// 
//
// 示例： 
//
// 输入: root = [4,2,6,1,3,null,null]
//输出: 1
//解释:
//注意，root是树节点对象(TreeNode object)，而不是数组。
//
//给定的树 [4,2,6,1,3,null,null] 可表示为下图:
//
//          4
//        /   \
//      2      6
//     / \    
//    1   3  
//
//最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。 
//
// 
//
// 注意： 
//
// 
// 二叉树的大小范围在 2 到 100。 
// 二叉树总是有效的，每个节点的值都是整数，且不重复。 
// 本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 
//相同 
// 
// Related Topics 树 递归 
// 👍 79 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {

    private Queue<Integer> queue;
    private List<Integer> list;
    private Integer prev;
    private int min = Integer.MAX_VALUE;

    public Solution() {
        this.queue = new PriorityQueue<>();
        this.list = new ArrayList<>();
    }

    /**
     * 基于题目条件的二叉搜索树，可以使用中根次序遍历
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param root
     * @return
     */
    public int optimumSolution(TreeNode root) {
        inOrder(root);
        return this.min;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if (prev != null) {
            min = Math.min(min, node.val - prev);
        }
        prev = node.val;
        inOrder(node.right);
    }

    /**
     * 普通排序，不基于优先队列
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        preOrder(root);
        Collections.sort(list);

        int min = 100;
        /**
         * for循环中++i和i++的区别（引用网上文章）
         * 根据上面的for循环的语法定义 ++i 和 i++的结果是一样的，都要等代码块执行完毕才能执行语句3，但是性能是不同的。在大量数据的时候++i的性能要比i++的性能好原因：
         * i++由于是在使用当前值之后再+1，所以需要一个临时的变量来转存；
         * 而++i则是在直接+1，省去了对内存的操作的环节，相对而言能够提高性能。
         */
        for (int i = 0; i < list.size() - 1; ++i) {
            min = Math.min(min, list.get(i + 1) - list.get(i));
        }

        return min;
    }

    /**
     * 排序解法，基于优先队列，排序之后，只需要计算相邻每两个节点值之间的差值
     *
     * @param root
     * @return
     */
    public int minDiffInBST_sort(TreeNode root) {
        preOrder(root);

        int min = 100;
        int cursor = this.queue.poll();

        do {
            int temp = this.queue.poll();
            min = Math.min(min, temp - cursor);
            cursor = temp;
        } while (!this.queue.isEmpty());

        return min;
    }

    /**
     * 先根次序递归
     *
     * @param node
     * @return
     */
    private void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // this.queue.add(node.val);
        this.list.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
