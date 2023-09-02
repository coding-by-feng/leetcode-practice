package me.fengorz.leetcode.stack.valid_parentheses;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 1830 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    // 吊炸天的解法
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (left1 == c) {
                stack.push(right1);
            } else if (left2 == c) {
                stack.push(right2);
            } else if (left3 == c) {
                stack.push(right3);
            } else if (stack.empty() || stack.pop() != c) {
                return false;
            }
        }
        // 单线程使用empty比isEmpty快
        return stack.empty();
    }

    private static final char left1 = '(';
    private static final char right1 = ')';
    private static final char left2 = '[';
    private static final char right2 = ']';
    private static final char left3 = '{';
    private static final char right3 = '}';

    // public boolean isValid_my(String s) {
    //     if (s == null || s.length() < 2) {
    //         return false;
    //     }
    //     Stack<String> stack = new Stack<>();
    //     String left = s.substring(0, 1);
    //     for (int i = 1; i < s.length(); i++) {
    //         if (left == null) {
    //             left = s.substring(i, i + 1);
    //             continue;
    //         }
    //         String right = s.substring(i, i + 1);
    //         if (!(left1.equals(left) && right1.equals(right)
    //                 || left2.equals(left) && right2.equals(right)
    //                 || left3.equals(left) && right3.equals(right))) {
    //             stack.push(left);
    //             left = right;
    //         } else {
    //             if (stack.isEmpty()) {
    //                 left = null;
    //                 continue;
    //             }
    //             left = stack.pop();
    //         }
    //     }
    //     return left == null;
    // }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("([{}])1"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
