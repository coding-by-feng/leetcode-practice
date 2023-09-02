package me.fengorz.nk.test2;

import java.util.Arrays;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int InversePairs(int[] nums) {
        // write code here
        sort(nums);
        return Math.floorMod(count, 1000000007);
    }

    private int count = 0;

    public int[] sort(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        return merge(sort(left), sort(right));
    }

    public int[] merge(int[] left, int[] right) {
        int[] temp = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] >= right[0]) {
                if (left[0] > right[0]) {
                    count += left.length;
                    count %= 1000000007;
                }
                temp[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            } else {
                temp[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            }
        }
        while (left.length > 0) {
            temp[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }
        while (right.length > 0) {
            temp[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.InversePairs(new int[]{1, 2, 3, 4, 5, 6, 7, 0}));
        System.out.println(Arrays.toString(solution.sort(new int[]{1, 2, 3, 4, 5, 6, 7, 0})));
        System.out.println(solution.count);
    }
}