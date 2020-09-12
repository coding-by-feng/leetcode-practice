package me.fengorz.leetcode.tree.nary_tree_preorder_traversal;
//给定一个 N 叉树，返回其节点值的前序遍历。

//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 100 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    private List<Integer> result;

    public Solution() {
        // 使用LinkedList会降低空间复杂度，而且对所求的的解只是做简单的遍历读取，在这点上LinkedList和ArrayList都是O(n)
        this.result = new LinkedList<>();
    }

    /**
     * 很简单的递归解法
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.79%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了98.43%的用户
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        next(root);
        return this.result;
        // return once(root);
    }

    private void next(Node node) {
        if (node == null) {
            return;
        }
        this.result.add(node.val);
        if (null == node.children || node.children.isEmpty()) {
            return;
        }
        for (Node child : node.children) {
            next(child);
        }
    }

    /**
     * 迭代解法，使用双端队列，后进先出（LIFO），实际上是基于栈
     *
     * @param root
     * @return
     */
    private List<Integer> once(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new LinkedList<>();
        // 这里LinkedList不能用Stack接口来声明
        // deque使用LinkedList如果在数据量巨大且元素是重量级对象时，性能会有明显提升。
        Deque<Node> deque = new LinkedList<>();
        // Stack<Node> deque = new Stack<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            Node node = deque.pollLast();
            result.add(node.val);
            if (null == node.children || node.children.isEmpty()) {
                continue;
            }
            Collections.reverse(node.children);
            for (Node child : node.children) {
                deque.addLast(child);
            }
        }

        return result;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
