package me.fengorz.nk.test12;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<String> memoryAddress = new HashSet<>();
        while (head.next != null) {
            if (memoryAddress.contains(head.toString())) {
                return true;
            } else {
                memoryAddress.add(head.toString());
                head = head.next;
            }
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}