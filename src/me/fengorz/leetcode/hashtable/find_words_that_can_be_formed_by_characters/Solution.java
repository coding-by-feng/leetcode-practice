package me.fengorz.leetcode.hashtable.find_words_that_can_be_formed_by_characters;
//给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
//
// 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。 
//
// 注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。 
//
// 返回词汇表 words 中你掌握的所有单词的 长度之和。 
//
// 
//
// 示例 1： 
//
// 输入：words = ["cat","bt","hat","tree"], chars = "atach"
//输出：6
//解释： 
//可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
// 
//
// 示例 2： 
//
// 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
//输出：10
//解释：
//可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 1000 
// 1 <= words[i].length, chars.length <= 100 
// 所有字符串中都仅包含小写英文字母 
// 
// Related Topics 数组 哈希表 
// 👍 110 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了94.94%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了83.51%的用户
     *
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {
        // 使用一个int数组来代替哈希表，时间复杂度会更低
        int[] charCount = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            ++charCount[chars.charAt(i) - 'a'];
        }
        int sum = 0;
        for (String word : words) {
            int[] tempArr = Arrays.copyOf(charCount, 26);
            int i = 0;
            for (; i < word.length(); i++) {
                int temp = word.charAt(i) - 'a';
                if (tempArr[temp] == 0) {
                    break;
                } else {
                    --tempArr[temp];
                }
            }
            if (i == word.length()) {
                sum += word.length();
            }
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
