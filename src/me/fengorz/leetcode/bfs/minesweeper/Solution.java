package me.fengorz.leetcode.bfs.minesweeper;
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
    private final static char E = 'E';
    private final static char M = 'M';
    private final static char X = 'X';
    private static int boardYPreBorder;
    private static int boardXPreBorder;
    private int[][] visitRecord;

    /**
     * BFS解法
     * TODO 待修改优化
     *
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        boardYPreBorder = board[0].length - 1;
        boardXPreBorder = board.length - 1;
        visitRecord = new int[board.length][board[0].length];
        if (board[click[0]][click[1]] == M) {
            board[click[0]][click[1]] = X;
        }
        bfs(board, click[0], click[1]);
        return board;
    }

    private void bfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            // -128~127之间读取缓存，速度很快
            return;
        }
        if (visitRecord[x][y] == 1) {
            return;
        }
        visitRecord[x][y] = 1;
        change(board, x, y);
        bfs(board, x - 1, y);
        bfs(board, x + 1, y);
        bfs(board, x, y - 1);
        bfs(board, x, y + 1);
    }

    /**
     * ---- 上，下，左，右，和所有4个对角线都没有地雷的话，返回B
     *
     * @param board
     * @param x
     * @param y
     * @return
     */
    private char change(char[][] board, int x, int y) {
        char up = 0;
        char down = 0;
        char left = 0;
        char right = 0;
        char upLeft = 0;
        char upRight = 0;
        char downLeft = 0;
        char downRight = 0;
        // 当前位置不处于最上边
        if (x > 0) {
            up = board[x - 1][y];
            // 当前位置不处于最左边
            if (y > 0) {
                upLeft = board[x - 1][y - 1];
            }
            // 当前位置不处于最右边
            if (y < boardYPreBorder) {
                upRight = board[x - 1][y + 1];
            }
        }
        // 当前位置不处于最下边
        if (x < boardXPreBorder) {
            down = board[x + 1][y];
            if (y > 0) {
                downLeft = board[x + 1][y - 1];
            }
            if (y < boardYPreBorder) {
                downRight = board[x + 1][y + 1];
            }
        }
        // 当前位置不处于最左边
        if (y > 0) {
            left = board[x][y - 1];
        }
        // 当前位置不处于最右边
        if (y < boardYPreBorder) {
            right = board[x][y + 1];
        }

        board[x][y] = seek(up, down, left, right, upLeft, upRight, downLeft, downRight);
        return board[x][y];
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
