package me.fengorz.leetcode.linkedlist.merge_two_lists;
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1242 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {

    // 递归解法
    //     list1[0]+merge(list1[1:],list2)        list1[0]<list2[0]
    //     list2[0]+merge(list1,list2[1:])        otherwise
    public ListNode mergeTwoLists_0(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists_0(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_0(l1, l2.next);
            return l2;
        }
    }

    // 遍历解法
    public ListNode mergeTwoLists_1(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        final ListNode result = new ListNode();
        ListNode tmp = result;
        while (true) {
            if (l1 != null && (l2 == null || l1.val < l2.val)) {
                tmp.val = l1.val;
                l1 = l1.next;
            } else {
                tmp.val = l2.val;
                l2 = l2.next;
            }
            if (l1 == null && l2 == null) {
                break;
            }
            tmp.next = new ListNode();
            tmp = tmp.next;
        }
        return result;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode l1_0 = new ListNode();
        ListNode l1_1 = new ListNode();
        ListNode l1_2 = new ListNode();
        l1_0.val = 1;
        l1_0.next = l1_1;
        l1_1.val = 2;
        l1_1.next = l1_2;
        l1_2.val = 4;

        ListNode l2_0 = new ListNode();
        ListNode l2_1 = new ListNode();
        ListNode l2_2 = new ListNode();
        l2_0.val = 1;
        l2_0.next = l2_1;
        l2_1.val = 3;
        l2_1.next = l2_2;
        l2_2.val = 4;

        Solution solution = new Solution();
        ListNode listNode = solution.mergeTwoLists_1(l1_0, l2_0);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
