package me.fengorz.nk.test3;

import java.util.Stack;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pushV int整型一维数组
     * @param popV int整型一维数组
     * @return bool布尔型
     */
    public boolean IsPopOrder(int[] pushV, int[] popV) {
        // write code here
        if (pushV == null || pushV.length == 0) {
            return popV == null || popV.length == 0;
        }
        Stack<Integer> pushStack = new Stack<>();
        int pushIndex = 0;
        for (int i = 0; i < popV.length; i++) {
            while (pushV[pushIndex] == popV[i]) {
                pushIndex++;
            }
        }
        
        return pushStack.empty() && pushIndex == popV.length;
    }
}