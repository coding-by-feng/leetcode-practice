package me.fengorz.nk.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param num int整型一维数组
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // write code here
        if (num == null || num.length < 3) {
            return new ArrayList<>();
        }
        Set<String> unique = new HashSet<>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                for (int k = j + 1; k < num.length; k++) {
                    if (num[i] + num[j] + num[k] == 0 && !unique.contains(num[i] + "," + num[j] + "," + num[k])) {
                        ArrayList<Integer> integers = new ArrayList<>();
                        integers.add(num[i]);
                        integers.add(num[j]);
                        integers.add(num[k]);
                        list.add(integers);
                        System.out.println(Arrays.toString(integers.toArray()));
                        unique.add(num[i] + "," + num[j] + "," + num[k]);
                    }
                }
            }
        }
        return list;
    }
}