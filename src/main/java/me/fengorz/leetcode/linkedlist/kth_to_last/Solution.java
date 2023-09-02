package me.fengorz.leetcode.linkedlist.kth_to_last;
//实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
//
// 注意：本题相对原题稍作改动 
//
// 示例： 
//
// 输入： 1->2->3->4->5 和 k = 2
//输出： 4 
//
// 说明： 
//
// 给定的 k 保证是有效的。 
// Related Topics 链表 双指针 
// 👍 40 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    // 使用双指针解决问题，就不用统计链表的长度。
    public int kthToLast(ListNode head, int k) {
        ListNode tmp = head;
        for (int i = 0; i < k; i++) {
            tmp = tmp.next;
        }
        while (tmp != null) {
            tmp = tmp.next;
            head = head.next;
        }
        return head.val;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
