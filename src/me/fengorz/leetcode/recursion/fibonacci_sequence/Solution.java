package me.fengorz.leetcode.recursion.fibonacci_sequence;
//写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
//
// F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1. 
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 
//
// 示例 1： 
//
// 输入：n = 2
//输出：1
// 
//
// 示例 2： 
//
// 输入：n = 5
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 509 题相同：https://leetcode-cn.com/problems/fibonacci-number/ 
// Related Topics 递归 
// 👍 59 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 用递归解法容易超出时间限制
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 2) + fib(n - 1) % 1000000007;
    }

    private HashMap<Integer, Integer> fMap;

    public Solution() {
        this.fMap = new HashMap<>();
    }

    /**
     * 使用额外的HashMap解法
     * 减少重复计算的开销，如果是重量级的计算的话效果呈现出来。
     * @param n
     * @return
     */
    public int hashMapFib(int n) {
        if (n < 2) {
            return n;
        }
        if (this.fMap.get(n) != null) {
            return this.fMap.get(n);
        }
        int f = (fib(n - 2) + fib(n - 1)) % 1000000007;
        this.fMap.put(n, f);
        return f;
    }

    /**
     * 简单遍历解法
     *
     * @param n
     * @return
     */
    public int iterateFib(int n) {
        if (n == 0) {
            return 0;
        }
        int f = 1, fMinus1 = 1, fMinus2 = 0;
        for (int i = 1; i < n; i++) {
            f = (fMinus1 + fMinus2) % 1000000007;
            fMinus2 = fMinus1;
            fMinus1 = f;
        }
        return f;
    }

    // 力扣上最搞笑解法
    int f[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 134903163, 836311896, 971215059, 807526948, 778742000, 586268941, 365010934, 951279875, 316290802, 267570670, 583861472, 851432142, 435293607, 286725742, 722019349, 8745084, 730764433, 739509517, 470273943, 209783453, 680057396, 889840849, 569898238, 459739080, 29637311, 489376391, 519013702, 8390086, 527403788, 535793874, 63197655, 598991529, 662189184, 261180706, 923369890, 184550589, 107920472, 292471061, 400391533, 692862594, 93254120, 786116714, 879370834, 665487541, 544858368, 210345902, 755204270, 965550172, 720754435, 686304600, 407059028, 93363621, 500422649, 593786270, 94208912, 687995182};

    int jokeFib(int n) {
        return f[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
