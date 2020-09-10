package me.fengorz.leetcode.recursion.hanota;
//在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只
//能放在更大的盘子上面)。移动圆盘时受到以下限制: 
//(1) 每次只能移动一个盘子; 
//(2) 盘子只能从柱子顶端滑出移到下一根柱子; 
//(3) 盘子只能叠在比它大的盘子上。 
//
// 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。 
//
// 你需要原地修改栈。 
//
// 示例1: 
//
//  输入：A = [2, 1, 0], B = [], C = []
// 输出：C = [2, 1, 0]
// 
//
// 示例2: 
//
//  输入：A = [1, 0], B = [], C = []
// 输出：C = [1, 0]
// 
//
// 提示: 
//
// 
// A中盘子的数目不大于14个。 
// 
// Related Topics 递归 
// 👍 44 👎 0


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    /**
     * 经典的递归解法
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        next(A.size(), A, B, C);
    }

    /**
     * 很多题解都分解成三步走：
     * 1：先把上面 n - 1 个盘子从 A 移到 B（递归）；
     * 2：再将最大的盘子从 A 移到 C；
     * 3：再将 B 上 n - 1 个盘子从 B 移到 C（递归）。
     * <p>
     * 其实我认为不妨把问题的解答理解为：
     * 每一个重复的原子操作都是为了将A中最大的盘子抽到C，当抽完最后一个最大盘子（也就是最小盘子）时，就解答完成。
     * <p>
     * 于是可以将上面1、2、3步的第3步理解为：
     * 将B所在的位置tmpB和A所在的位置tmpA调换一下，因为此时的tmpB位置的最底部盘子是未移动到tmpC位置的所有盘子的最大盘子！（tmpC位置永远是放着C的盘子）
     * <p>
     * 所以，第3不做完就完成了一个原子操作，可以继续将tmpA位置的最底部盘子抽到tmpC位置。
     *
     * @param level
     * @param tmpA
     * @param tmpB
     * @param tmpC
     */
    private void next(int level, List<Integer> tmpA, List<Integer> tmpB, List<Integer> tmpC) {
        if (level == 1) {
            // tmpA剩下最后一个盘子
            tmpC.add(tmpA.remove(tmpA.size() - 1));
            return;
        }

        // 先把上面 n - 1 个盘子从tmpA位置移到tmpB位置
        next(level - 1, tmpA, tmpC, tmpB);
        // 将A（tmpA位置）中最大的盘子抽到C（tmpC位置）
        tmpC.add(tmpA.remove(tmpA.size() - 1));
        // 将B所在的位置tmpB和A所在的位置tmpA调换一下
        next(level - 1, tmpB, tmpA, tmpC);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
