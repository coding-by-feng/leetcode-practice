package me.fengorz.leetcode.sort._reorder_data_in_log_files;
//你有一个日志数组 logs。每条日志都是以空格分隔的字串。
//
// 对于每条日志，其第一个字为字母与数字混合的 标识符 ，除标识符之外的所有字为这一条日志的 内容 。 
//
// 
// 除标识符之外，所有字均由小写字母组成的，称为 字母日志 
// 除标识符之外，所有字均由数字组成的，称为 数字日志 
// 
//
// 题目所用数据保证每个日志在其标识符后面至少有一个字。 
//
// 请按下述规则将日志重新排序： 
//
// 
// 所有 字母日志 都排在 数字日志 之前。 
// 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序； 
// 数字日志 应该按原来的顺序排列。 
// 
//
// 返回日志的最终顺序。 
//
// 
//
// 示例 ： 
//
// 输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
//输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= logs.length <= 100 
// 3 <= logs[i].length <= 100 
// logs[i] 保证有一个标识符，并且标识符后面有一个字。 
// 
// Related Topics 字符串 
// 👍 49 👎 0

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * Amazon Problems.
     * <p>
     * 简单的排序解法
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了88.06%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param logs
     * @return
     */
    public String[] reorderLogFiles(String[] logs) {
        final String blank = " ";
        List<String> alphabetLogs = new LinkedList<>();
        List<String> digitLogs = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String log : logs) {
            int index = log.indexOf(blank) + 1;
            map.put(log, index);
            if (log.charAt(index) > 64) {
                alphabetLogs.add(log);
            } else {
                digitLogs.add(log);
            }
        }
        alphabetLogs.sort((prevLog, nextLog) -> {
            Integer prevIndex = map.get(prevLog);
            Integer nextIndex = map.get(nextLog);
            int cmp = prevLog.substring(prevIndex).compareTo(nextLog.substring(nextIndex));
            if (cmp == 0) {
                return prevLog.substring(0, prevIndex - 1).compareTo(nextLog.substring(0, nextIndex - 1));
            }
            return cmp;
        });
        alphabetLogs.addAll(digitLogs);
        return alphabetLogs.toArray(new String[logs.length]);
    }
}

