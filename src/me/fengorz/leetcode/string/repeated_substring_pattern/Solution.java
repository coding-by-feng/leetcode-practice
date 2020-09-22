package me.fengorz.leetcode.string.repeated_substring_pattern;
//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
//
// 示例 1: 
//
// 
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
// 
//
// 示例 2: 
//
// 
//输入: "aba"
//
//输出: False
// 
//
// 示例 3: 
//
// 
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
// 
// Related Topics 字符串 
// 👍 360 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    private int[] next;

    /**
     * 利用原生API的巧妙解法
     * 假定两个字符串：
     * ----- bdubdubdu（符合题目要求）
     * ---------- 那么将字符串double，然后计算double后的串所匹配到的第二个double前的串，
     * ---------- 会发现所匹配到的下标必定是小于double前串的总长度的，这是因为串的可重复性导致的。
     * ----- dbubdubbu（不符合题目要求）
     * ---------- 同上，这种不可由重复得到的串double之后，计算double后的串所匹配到的第二个double前的串
     * ---------- 会发现所匹配到的下标必定是从double前串的总长度开始的，这是因为串的不可重复性导致的。
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    /**
     * KMP解法，同样是基于上面的理解基础，只不过不用原生API实现
     * <p>
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了99.95%的用户
     *
     * @return
     */
    public boolean repeatedSubstringPattern_kmp(String s) {
        return kmp(s + s, s);
    }

    private boolean kmp(String main, String pattern) {
        // 先构建模式串的next数组，下标从0开始
        initNext(pattern);
        // i为主串指针，j为模式串指针，n为匹配第几次
        int i = 1, j = 0;
        while (i < main.length() && j < pattern.length()) {
            if (j == -1 || main.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }

        if (j >= pattern.length()) {
            return i - pattern.length() != pattern.length();
        } else {
            return false;
        }
    }

    private void initNext(String pattern) {
        // 冗余出最后一个元素位，防止下标溢出
        next = new int[pattern.length() + 1];
        int i = 1, j = -1;
        next[0] = j;
        while (i < pattern.length()) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }
    // TODO ZSF 优化版KMP算法解法

    public static void main(String[] args) {
        System.out.println(new Solution().repeatedSubstringPattern_kmp("abab"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
