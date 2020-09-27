package me.fengorz.leetcode.hashtable.uncommon_words_from_two_sentences;
//给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
//
// 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。 
//
// 返回所有不常用单词的列表。 
//
// 您可以按任何顺序返回列表。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：A = "this apple is sweet", B = "this apple is sour"
//输出：["sweet","sour"]
// 
//
// 示例 2： 
//
// 输入：A = "apple apple", B = "banana"
//输出：["banana"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= A.length <= 200 
// 0 <= B.length <= 200 
// A 和 B 都只包含空格和小写字母。 
// 
// Related Topics 哈希表 
// 👍 67 👎 0


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> wordMap = new HashMap<>();
        final String EMPTY = " ";
        for (String word : A.split(EMPTY)) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        for (String word : B.split(EMPTY)) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        List<String> result = new LinkedList<>();
        wordMap.forEach((k, v) -> {
            if (v == 1) {
                result.add(k);
            }
        });
        return result.toArray(new String[0]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
