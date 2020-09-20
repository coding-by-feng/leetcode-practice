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

    // TODO ZSF KMP算法解法
    // TODO ZSF 优化版KMP算法解法
}
//leetcode submit region end(Prohibit modification and deletion)
