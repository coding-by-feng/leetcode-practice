package me.fengorz.nk.test10;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param x int整型 
     * @return bool布尔型
     */
    public boolean isPalindrome (int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        // write code here
        String num = String.valueOf(x);
        return new StringBuilder(num).reverse().toString().equals(num);
    }
}