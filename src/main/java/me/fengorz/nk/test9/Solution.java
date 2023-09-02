package me.fengorz.nk.test9;

import java.util.ArrayList;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n int整型
     * @return string字符串ArrayList
     */
    ArrayList<String> moveOrder = new ArrayList<>();

    public ArrayList<String> getSolution(int n) {
        // write code here
        hannuota(n, "left", "mid", "right");
        return moveOrder;
    }

    private void hannuota(int n, String left, String mid, String right) {
        String move;
        if (n == 1) {
            move = "move from " + left + " to " + right;
            moveOrder.add(move);
            return;
        }
        hannuota(n - 1, left, right, mid);
        move = "move from " + left + " to " + right;
        moveOrder.add(move);
        hannuota(n - 1, mid, left, right);
    }

}