package me.fengorz.leetcode.stack.minimum_remove_to_make_valid_parentheses;
//给你一个由 '('、')' 和小写字母组成的字符串 s。
//
// 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。 
//
// 请返回任意一个合法字符串。 
//
// 有效「括号字符串」应当符合以下 任意一条 要求： 
//
// 
// 空字符串或只包含小写字母的字符串 
// 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」 
// 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」 
// 
//
// 
//
// 示例 1： 
//
// 输入：s = "lee(t(c)o)de)"
//输出："lee(t(c)o)de"
//解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
// 
//
// 示例 2： 
//
// 输入：s = "a)b(c)d"
//输出："ab(c)d"
// 
//
// 示例 3： 
//
// 输入：s = "))(("
//输出：""
//解释：空字符串也是有效的
// 
//
// 示例 4： 
//
// 输入：s = "(a(b(c)d)"
//输出："a(b(c)d)"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10^5 
// s[i] 可能是 '('、')' 或英文小写字母 
// 
// Related Topics 栈 字符串 
// 👍 55 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    private static final char LEFT = '(';
    private static final char RIGHT = ')';
    private static final char EMPTY = ' ';

    /**
     * 使用双端队列代替栈，中间用负值作为屏障标志
     * 队尾插入需要被抵消的左括号
     * 队头插入没办法被抵消的右括号
     * <p>
     * 执行用时：15 ms, 在所有 Java 提交中击败了94.78%的用户
     *
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(Integer.MIN_VALUE);
        char[] sArr = s.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] == LEFT) {
                deque.addLast(i);
            } else if (sArr[i] == RIGHT) {
                // 判断能否被前面出现过的左括号抵消掉
                if (Integer.MIN_VALUE == deque.peekLast()) {
                    deque.addFirst(i);
                } else {
                    deque.pollLast();
                }
            }
        }

        //开始清除无效括号
        if (deque.size() > 1) {
            while (!deque.isEmpty()) {
                int temp = deque.poll();
                if (temp != Integer.MIN_VALUE) {
                    sArr[temp] = EMPTY;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (char c : sArr) {
            if (EMPTY != c) {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        new Solution().minRemoveToMakeValid("lee(t(c)o)de)");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
