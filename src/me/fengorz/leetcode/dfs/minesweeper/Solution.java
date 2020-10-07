package me.fengorz.leetcode.dfs.minesweeper;
//让我们一起来玩扫雷游戏！
//
// 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）
//地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。 
//
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 
// 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。 
// 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回面板。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 示例 2： 
//
// 输入: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 
//
// 注意： 
//
// 
// 输入矩阵的宽和高的范围为 [1,50]。 
// 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。 
// 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 
// 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 182 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    private final static char B = 'B';
    private final static char M = 'M';
    private final static char X = 'X';
    private static int boardYPreBorder;
    private static int boardXPreBorder;
    private int[][] visitRecord;

    /**
     * DFS解法
     * ---- 如果踩雷，M变为X，return
     * ---- 如果安全，搜寻其周围的雷数
     * -------- 如果周围有雷，E变为雷数，比继续DFS其周围的方块（避免踩雷）
     * -------- 如果周围没雷，继续DFS（访问过的方块pass），注意要向八个方向递归，刚开始只递归四个方向一直出错。
     *
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == M) {
            board[click[0]][click[1]] = X;
            return board;
        }
        boardYPreBorder = board[0].length - 1;
        boardXPreBorder = board.length - 1;
        visitRecord = new int[board.length][board[0].length];
        dfs(board, click[0], click[1], B);
        return board;
    }

    private void dfs(char[][] board, int x, int y, char pre) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        if (visitRecord[x][y] == 1) {
            return;
        }
        visitRecord[x][y] = 1;
        char result = board[x][y] = recur(board, x, y);
        // 挖到数字不挖旁边的方块
        if (result != B) {
            return;
        }
        // 往四周八个方向扩展
        dfs(board, x - 1, y, result);
        dfs(board, x - 1, y - 1, result);
        dfs(board, x - 1, y + 1, result);
        dfs(board, x + 1, y, result);
        dfs(board, x + 1, y + 1, result);
        dfs(board, x + 1, y - 1, result);
        dfs(board, x, y - 1, result);
        dfs(board, x, y + 1, result);
    }

    private char recur(char[][] board, int x, int y) {
        if (board[x][y] == M) {
            return M;
        }

        char up = 0;
        char down = 0;
        char left = 0;
        char right = 0;
        char upLeft = 0;
        char upRight = 0;
        char downLeft = 0;
        char downRight = 0;
        // 当前位置不处于最上边
        boolean isNotUp = x > 0;
        // 当前位置不处于最左边
        boolean isNotLeft = y > 0;
        // 当前位置不处于最右边
        boolean isNotRight = y < boardYPreBorder;
        // 当前位置不处于最下边
        boolean isNotDown = x < boardXPreBorder;
        if (isNotUp) {
            up = board[x - 1][y];
            if (isNotLeft) {
                upLeft = board[x - 1][y - 1];
            }
            if (isNotRight) {
                upRight = board[x - 1][y + 1];
            }
        }
        if (isNotDown) {
            down = board[x + 1][y];
            if (isNotLeft) {
                downLeft = board[x + 1][y - 1];
            }
            if (isNotRight) {
                downRight = board[x + 1][y + 1];
            }
        }
        if (isNotLeft) {
            left = board[x][y - 1];
        }
        if (isNotRight) {
            right = board[x][y + 1];
        }
        return seek(up, down, left, right, upLeft, upRight, downLeft, downRight);
    }

    private char seek(char... all) {
        // 其四周拥有炸弹的数字
        int i = 0;
        for (char c : all) {
            if (c == M || c == X) {
                ++i;
            }
        }
        return i > 0 ? Character.forDigit(i, 10) : B;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
