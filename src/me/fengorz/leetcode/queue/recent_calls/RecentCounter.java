package me.fengorz.leetcode.queue.recent_calls;
//写一个 RecentCounter 类来计算最近的请求。
//
// 它只有一个方法：ping(int t)，其中 t 代表以毫秒为单位的某个时间。 
//
// 返回从 3000 毫秒前到现在的 ping 数。 
//
// 任何处于 [t - 3000, t] 时间范围之内的 ping 都将会被计算在内，包括当前（指 t 时刻）的 ping。 
//
// 保证每次对 ping 的调用都使用比之前更大的 t 值。 
//
// 
//
// 示例： 
//
// 输入：inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[
//100],[3001],[3002]]
//输出：[null,1,2,3,3] 
//
// 
//
// 提示： 
//
// 
// 每个测试用例最多调用 10000 次 ping。 
// 每个测试用例会使用严格递增的 t 值来调用 ping。 
// 每次调用 ping 都有 1 <= t <= 10^9。 
// 
//
// 
// Related Topics 队列 
// 👍 61 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
public class RecentCounter {

    private Queue<Integer> queue;

    public RecentCounter() {
        // 使用链表实现的队列比ArrayDeque顺序表方式空间复杂度更低
        this.queue = new LinkedList<>();
    }

    /**
     * 简单的队列解法
     * 执行用时：28 ms, 在所有 Java 提交中击败了90.78%的用户
     * 内存消耗：48 MB, 在所有 Java 提交中击败了95.20%的用户
     * @param t
     * @return
     */
    public int ping(int t) {
        this.queue.add(t);
        while (t - 3000 > this.queue.peek()) {
            this.queue.poll();
        }
        return this.queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
//leetcode submit region end(Prohibit modification and deletion)
