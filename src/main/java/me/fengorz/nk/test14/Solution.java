package me.fengorz.nk.test14;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int maxProduct(int[] nums) {
        // write code here
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            int temp = 1;
            while (j < nums.length) {
                if (i == j) {
                    if (nums[i] > maxProduct) {
                        maxProduct = nums[i];
                        temp = maxProduct;
                        j++;
                    } else {
                        break;
                    }
                } else {
                    temp *= nums[j];
                    if (temp > maxProduct) {
                        maxProduct = temp;
                    }
                    j++;
                }
            }
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new int[]{3, 2, -1, 4}));
    }
}