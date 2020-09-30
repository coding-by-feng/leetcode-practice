package me.fengorz.leetcode.hashtable.brick_wall;
//你的面前有一堵矩形的、由多行砖块组成的砖墙。 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。
//
// 砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。 
//
// 如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。 
//
// 你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。 
//
// 
//
// 示例： 
//
// 输入: [[1,2,2,1],
//      [3,1,2],
//      [1,3,2],
//      [2,4],
//      [3,1,2],
//      [1,3,1,1]]
//
//输出: 2
//
//解释: 
//
// 
//
// 
//
// 提示： 
//
// 
// 每一行砖块的宽度之和应该相等，并且不能超过 INT_MAX。 
// 每一行砖块的数量在 [1,10,000] 范围内， 墙的高度在 [1,10,000] 范围内， 总的砖块数量不超过 20,000。 
// 
// Related Topics 哈希表 
// 👍 101 👎 0


import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    /**
     * 官方巧妙的哈希表解法
     * 构建一个哈希表来存放每一列能避开的最大砖块数，遍历完每一行的每一列之后，再计算哈希表的最大值。
     *
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        int sum;
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (List<Integer> row : wall) {
            sum = 0;
            for (Integer brick : row) {
                sum += brick;
                hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
            }
            // hashMap.remove(sum); 不能使用remove，使用udpate时间开销会更少
            // 行尾不是一个正确的解
            hashMap.put(sum, 0);
        }
        int min = wall.size();
        // 为什么不使用values()，使用values每次都要new Values()，会造成空间额外的浪费，虽然代码会优雅一些。
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            min = Math.min(min, wall.size() - entry.getValue());
        }
        return min;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
