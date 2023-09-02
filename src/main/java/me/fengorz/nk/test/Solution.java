package me.fengorz.nk.test;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param target int整型
     * @param array  int整型二维数组
     * @return bool布尔型
     */
    public boolean Find(int target, int[][] array) {
        // write code here
        if (array == null || array.length == 0 || array[0] == null || array[0].length == 0) {
            return false;
        }
        // 1,2,3,4,5,6,7,8,9
        for (int[] row : array) {
            int left = 0;
            int right = row.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (target == row[mid]) {
                    return true;
                } else if (target < row[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        System.out.println(new Solution().Find(7, array));
    }
}