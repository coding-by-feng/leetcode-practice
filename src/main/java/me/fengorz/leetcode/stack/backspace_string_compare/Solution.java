package me.fengorz.leetcode.stack.backspace_string_compare;
//给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
//
// 注意：如果对空文本输入退格字符，文本继续为空。 
//
// 
//
// 示例 1： 
//
// 输入：S = "ab#c", T = "ad#c"
//输出：true
//解释：S 和 T 都会变成 “ac”。
// 
//
// 示例 2： 
//
// 输入：S = "ab##", T = "c#d#"
//输出：true
//解释：S 和 T 都会变成 “”。
// 
//
// 示例 3： 
//
// 输入：S = "a##c", T = "#a#c"
//输出：true
//解释：S 和 T 都会变成 “c”。
// 
//
// 示例 4： 
//
// 输入：S = "a#c", T = "b"
//输出：false
//解释：S 会变成 “c”，但 T 仍然是 “b”。 
//
// 
//
// 提示： 
//
// 
// 1 <= S.length <= 200 
// 1 <= T.length <= 200 
// S 和 T 只含有小写字母以及字符 '#'。 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？ 
// 
//
// 
// Related Topics 栈 双指针 
// 👍 148 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    private final static char flag = '#';

    // 双栈解法
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> sStack = reBuild(S);
        Stack<Character> tStack = reBuild(T);
        return sStack.size() == tStack.size() && sStack.equals(tStack);
    }


    // 双指针解法
    // S "y#fo##f"
    // T "fy#f#o#"
    // T "ffy#f#o##"
    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Backspace String Compare.
    // Memory Usage: 37.5 MB, less than 91.28% of Java online submissions for Backspace String Compare.
    public boolean backspaceCompare_twoPointer(String S, String T) {
        // s指向S的当前有效字母，t同（从右往左）
        int s = S.length() - 1, t = T.length() - 1;
        // sStep记录当前在S中要找到s需要往前调整的步数，tStep同（从右往左）
        int sStep = 0, tStep = 0;
        // 设定验证结果M为已经按了所有已知退格、或者没有退格的情况，捕捉到一个有效字母；
        // 设定每个验证结果N为还未按下所有已知退格的情况；（这一阶段每验证一次都要往前调整2步，等同于删除无效字母之前多了一次删除#，也就是要s = s-2）
        while (s > -1 || t > -1) {
            // 拿到单个有效字母s
            while (s > -1) {
                // 验证阶段，发现当前s等于#
                if (flag == S.charAt(s)) {
                    // 需要当前调整1步
                    sStep++;
                    // 属于N，本次删除#，还需要删除一次字母
                    s--;
                } else if (sStep > 0) {// 调整步数大于0，属于N，本次删除无效字母
                    sStep--;
                    s--;
                } else {// 没有需要调整的步数，属于M，将有效字母s与t比对，只要是不一致直接false
                    break;
                }
            }

            // 拿到单个有效字母t，同上
            while (t > -1) {
                if (flag == T.charAt(t)) {
                    tStep++;
                    t--;
                } else if (tStep > 0) {
                    tStep--;
                    t--;
                } else {
                    break;
                }
            }

            // 如果是"##"之类的，要防止此类下标越界
            // 比对s和t
            if (s > -1 && t > -1 && S.charAt(s) != T.charAt(t)) {
                return false;
            }
            // 如果S是""，T是"abc"之类的，没必要再继续做重复下去了，反之也是
            if (s < 0 && t > -1 || s > -1 && t < 0) {
                // if ((s > -1) != (t > -1)) {
                return false;
            }

            // 比对完了如果前面还有字符还继续重复
            s--;
            t--;
        }

        return true;
    }


    private Stack<Character> reBuild(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (flag == c) {
                if (stack.empty()) {
                    continue;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
