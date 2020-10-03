package me.fengorz.leetcode.search.heaters;
//冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
//
// 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。 
//
// 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。 
//
// 说明: 
//
// 
// 给出的房屋和供暖器的数目是非负数且不会超过 25000。 
// 给出的房屋和供暖器的位置均是非负数且不会超过10^9。 
// 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。 
// 所有供暖器都遵循你的半径标准，加热的半径也一样。 
// 
//
// 示例 1: 
//
// 
//输入: [1,2,3],[2]
//输出: 1
//解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
// 
//
// 示例 2: 
//
// 
//输入: [1,2,3,4],[1,4]
//输出: 1
//解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
// 
// Related Topics 二分查找 
// 👍 154 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    private static int heatersHigh;
    private int high;
    private int low;

    /**
     * 1、遍历每个屋子，然后根据二分查找法找到每个屋子最近的供暖器
     * 2、然后筛选出最远的距离值
     * 问题A：计算距离值时，如果屋子前后都有供暖器，要取屋子距离前后供暖器距离中，较小的一个。
     * <p>
     * 时间复杂度更低的一个解法是：不使用二分查找，进行双排序之后，每次找出屋子后面的供暖器（如果存在的话），然后声明一个游标在遍历屋子中跟随寻找供暖器的下标递增。
     */
    public int findRadius(int[] houses, int[] heaters) {
        // 要先对数组进行排序，二分查找只适合排序的线性表或数组
        Arrays.sort(heaters);
        Arrays.sort(houses);
        heatersHigh = heaters.length - 1;
        int housesHigh = houses.length - 1;

        int max = 0;
        int i = 0;
        for (int house : houses) {
            ++i;
            if (house > heaters[heatersHigh]) {
                break;
            }
            if (bs(heaters, house)) {// 屋子与供暖器相等时跳过
                continue;
            }
            // 解决问题A
            int right = Math.abs(heaters[Math.min(low, heatersHigh)] - house);// 当前屋子与后面供暖器的距离，存在负数的可能
            int left = Math.abs(house - heaters[Math.max(high, 0)]);// 当前屋子与前面供暖器的距离，存在负数的可能
            max = Math.max(max, Math.min(right, left));// 前后两个供暖器要去较近的一个
        }
        if (i < houses[housesHigh]) {
            max = Math.max(max, houses[housesHigh] - heaters[heatersHigh]);
        }
        return max;
    }

    /**
     * 二分查找
     *
     * @param heaters
     * @param house
     * @return
     */
    private boolean bs(int[] heaters, int house) {
        low = 0;
        high = heatersHigh;
        while (low <= high) {
            int half = (low + high) / 2;
            if (heaters[half] == house) {
                return true;
            } else if (heaters[half] > house) {
                high = half - 1;
            } else {
                low = half + 1;
            }
        }
        return false;
    }


}

