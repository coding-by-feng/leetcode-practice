package me.fengorz.leetcode.queue.moving_average;
//给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
//
// 示例: 
//
// MovingAverage m = new MovingAverage(3);
//m.next(1) = 1
//m.next(10) = (1 + 10) / 2
//m.next(3) = (1 + 10 + 3) / 3
//m.next(5) = (10 + 3 + 5) / 3
// 
//
// 
// Related Topics 设计 队列 
// 👍 31 👎 0

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class MovingAverage {

    private int size, tmpSum = 0;
    private boolean isDeuqeOverflow = false;
    private List<Integer> list = new ArrayList<>();
    private Deque<Integer> deque = new ArrayDeque<>();

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        this.size = size;
    }

    /**
     * 顺序表解法
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了98.56%的用户
     *
     * @param val
     * @return
     */
    public double next_arrayList(int val) {
        this.list.add(val);
        int sum = 0;
        for (int i = 1; i <= this.size && i <= this.list.size(); i++) {
            sum += this.list.get(this.list.size() - i);
        }
        return sum * 1D / Math.min(this.list.size(), this.size);
    }

    /**
     * 双端队列解法，这个解法时间复杂度和空间复杂度更低
     * 执行用时：25 ms, 在所有 Java 提交中击败了96.59%的用户
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了98.09%的用户
     *
     * @param val
     * @return
     */
    public double next_deque(int val) {
        this.deque.add(val);
        this.tmpSum += val;
        if (this.deque.size() > this.size) {
            this.isDeuqeOverflow = true;
            this.tmpSum -= this.deque.pop();
        }
        return this.isDeuqeOverflow ? tmpSum * 1D / this.size : tmpSum * 1D / this.deque.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
//leetcode submit region end(Prohibit modification and deletion)
