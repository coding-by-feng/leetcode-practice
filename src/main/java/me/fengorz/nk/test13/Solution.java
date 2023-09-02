package me.fengorz.nk.test13;

import java.util.HashSet;
import java.util.Set;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums   int整型一维数组
     * @param target int整型
     * @return int整型
     */
    public int combination(int[] nums, int target) {
        // write code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<String> unique = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (target % nums[i] == 0) {
                unique.add(nums[i] + "*" + target / nums[i]);
            } else {
                for (int j = 0; j < nums.length; j++) {

                }
            }
        }
        return 0;
    }
}