package me.fengorz.nk.test11;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param numbers int整型一维数组
     * @return bool布尔型
     */
    public boolean IsContinuous(int[] numbers) {
        // write code here
        int kingCount = 0;
        Set<Integer> notSameNumSet = new HashSet<>();
        for (int number : numbers) {
            if (number == 0) {
                kingCount++;
            } else {
                notSameNumSet.add(number);
            }
        }
        if (notSameNumSet.size() + kingCount != 5) {
            return false;
        }
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                continue;
            }
            int connect = numbers[i + 1] - numbers[i] - 1;
            if (connect > 0 && kingCount < connect) {
                return false;
            } else {
                kingCount -= connect;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().IsContinuous(new int[]{6, 0, 2, 0, 4}));
    }
}