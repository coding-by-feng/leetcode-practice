package me.fengorz.nk.test15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 两次交易所能获得的最大收益
     *
     * @param prices int整型一维数组 股票每一天的价格
     * @return int整型
     */
    public int maxProfit(int[] prices) {
        // write code here
        int maxProfit = 0;
        List<List<Integer>> profits = new ArrayList<>();
        for (int k = 0; k < prices.length; k++) {
            int buy = prices[k];
            List<Integer> currentProfits = new ArrayList<Integer>();
            for (int i = k; i < prices.length - 1; i++) {
                // 每一轮i进来都是先买入，然后剩下的日期，每一天都要卖出，只要卖出时盈利的，都卖出一次，将profit加到list
                for (int j = i + 1; j < prices.length; j++) {
                    if (prices[j] > buy) {
                        currentProfits.add(prices[j] - buy);
                        buy = 0;
                        i = j;
                        break;
                    }
                }
            }
            profits.add(currentProfits);
        }
        for (List<Integer> profit : profits) {
            System.out.println(Arrays.toString(profit.toArray()));
            int sum = 0;
            for (Integer p : profit) {
                sum += p;
            }
            if (sum > maxProfit) {
                maxProfit = sum;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 2, 3, 4, 5}));
    }
}