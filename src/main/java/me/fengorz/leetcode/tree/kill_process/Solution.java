package me.fengorz.leetcode.tree.kill_process;
//给 n 个进程，每个进程都有一个独一无二的 PID （进程编号）和它的 PPID （父进程编号）。
//
// 每一个进程只有一个父进程，但是每个进程可能会有一个或者多个孩子进程。它们形成的关系就像一个树状结构。只有一个进程的 PPID 是 0 ，意味着这个进程没有
//父进程。所有的 PID 都会是唯一的正整数。 
//
// 我们用两个序列来表示这些进程，第一个序列包含所有进程的 PID ，第二个序列包含所有进程对应的 PPID。 
//
// 现在给定这两个序列和一个 PID 表示你要杀死的进程，函数返回一个 PID 序列，表示因为杀这个进程而导致的所有被杀掉的进程的编号。 
//
// 当一个进程被杀掉的时候，它所有的孩子进程和后代进程都要被杀掉。 
//
// 你可以以任意顺序排列返回的 PID 序列。 
//
// 示例 1: 
//
// 输入: 
//pid =  [1, 3, 10, 5]
//ppid = [3, 0, 5, 3]
//kill = 5
//输出: [5,10]
//解释: 
//           3
//         /   \
//        1     5
//             /
//            10
//杀掉进程 5 ，同时它的后代进程 10 也被杀掉。
// 
//
// 
//
// 注意: 
//
// 
// 被杀掉的进程编号一定在 PID 序列中。 
// n >= 1. 
// 
//
// 
// Related Topics 树 队列 
// 👍 36 👎 0


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    /**
     * 深度优先搜索（DFS）
     * <p>
     * 不过单纯DFS容易超出限制。
     *
     * @param pid
     * @param ppid
     * @param kill
     * @return
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> result = new LinkedList<>();
        if (kill == 0) {
            return result;
        }
        result.add(kill);

        for (int i = 0; i < ppid.size(); i++) {
            // 当父进场id为kill时，代表当前节点包含其子树都要被杀死，所有必死的子树在ppid都会被找到。
            // 直到所有必死子树的叶子节点都杀完为止
            if (ppid.get(i) == kill) {
                result.addAll(killProcess(pid, ppid, pid.get(i)));
            }
        }

        return result;
    }

    // TODO ZSF 模拟树解法
    // TODO ZSF 宽度优先搜索解法
}
//leetcode submit region end(Prohibit modification and deletion)
