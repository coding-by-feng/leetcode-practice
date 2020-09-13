package me.fengorz.leetcode.queue.task_scheduler;
//给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务
//都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。 
//
// 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的最短时间。 
//
// 
//
// 示例 ： 
//
// 输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 
//
// 提示： 
//
// 
// 任务的总个数为 [1, 10000]。 
// n 的取值范围为 [0, 100]。 
// 
// Related Topics 贪心算法 队列 数组
// 👍 370 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    /**
     * 优先队列解法
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        final char A = 'A';
        // 使用char与int值对应关系定义一个int数组来代替26个字母
        int[] alphabet = new int[26];
        for (char letter : tasks) {
            alphabet[letter - A]++;
        }
        // 假定每一轮执行的计算是n+1，n+1能够确保每一轮计算中没有出现重复的字母
        // 比如n=2，每一轮当中，不可能出现A->*->A的情况，一定是A->*->*
        // 使用优先队列来装26个字母依次在tasks里面出现的次数，每一轮先被拿出来的字母优先权与字母出现次数呈正相关
        // 要使用反向排序Collections.reverseOrder()，因为PriorityQueue是呈现升序优先的
        Queue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        for (int letterSum : alphabet) {
            // 如果字母在tasks中不存在，就没有必要入队
            if (letterSum > 0) {
                queue.add(letterSum);
            }
        }
        // 总共的花费时间单位
        int time = 0;
        // 临时队列不要放到循环体里面去创建，这是一个"热点容器"
        List<Integer> transition = new LinkedList<>();
        while (!queue.isEmpty()) {
            // 当前轮已执行的时间单位
            int roundCursor = 0;
            // 每个字母的存量
            int poll = 0;
            while (roundCursor <= n) {
                if (!queue.isEmpty()) {
                    poll = queue.poll();
                    if (poll > 1) {
                        // 该字母任务被计算，需要-1
                        transition.add(poll - 1);
                    }
                }
                time++;
                // 如果是最后一轮的情况下，右边连续的待命任务不计入总的时间单位
                if (queue.isEmpty() && transition.isEmpty()) {
                    break;
                }
                roundCursor++;
            }
            // 运行过的字母如果要有存量，要继续扔会队列计算
            queue.addAll(transition);
            transition.clear();
        }
        return time;
    }

    /**
     * 排序解法？
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval_sort(char[] tasks, int n) {
        // TODO ZSF n多种排序有待深入学习
        return 0;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
