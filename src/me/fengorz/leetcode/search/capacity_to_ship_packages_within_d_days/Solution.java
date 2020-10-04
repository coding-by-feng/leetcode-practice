package me.fengorz.leetcode.search.capacity_to_ship_packages_within_d_days;
//传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
//
// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。 
//
// 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。 
//
// 
//
// 示例 1： 
//
// 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
//0) 是不允许的。 
// 
//
// 示例 2： 
//
// 输入：weights = [3,2,2,4,1,4], D = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
// 
//
// 示例 3： 
//
// 输入：weights = [1,2,3,1,1], D = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= D <= weights.length <= 50000 
// 1 <= weights[i] <= 500 
// 
// Related Topics 数组 二分查找 
// 👍 127 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    /**
     * 使用二分查找的思维来求解
     * ---- 先估算一个可能存在的解，设定最低载重M = 总的weights值除以天数
     * -------- 如果此时的M可满足题目，将low设定为0，high设定为M，进行折半查找；
     * -------- 如果此时的M不可满足题目，那么将low设定为M，high设定为M*D，进行折半查找（high的Max值不会超过weights的总值）；
     * -------- 然后重复折半查找。
     * <p>
     * 实际上将high直接置为Integer.MAX_VALUE会更快，当weights的长度超过远远超过30个左右时会更加明显。
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        // int sum = Arrays.stream(weights).sum();
        // int average = sum / D;
        // int low, high;
        // if (couldShip(weights, D, average)) {
        //     low = 0;
        //     high = average;
        // } else {
        //     low = average;
        //     high = sum;
        // }

        int low = 0;
        int high = Integer.MAX_VALUE;

        while (low < high) {
            int mid = (low + high) / 2;
            if (couldShip(weights, D, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean couldShip(int[] weights, int d, int m) {
        int temp = 0;
        for (int weight : weights) {
            // 每件货物必须小于最低承载
            if (weight > m || d < 1) {
                return false;
            }
            if (weight + temp <= m) {
                temp += weight;
            } else {
                temp = weight;
                d--;
            }
        }
        // 最后一个获取上船如果也超重了，天数也要减一
        return d > 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
