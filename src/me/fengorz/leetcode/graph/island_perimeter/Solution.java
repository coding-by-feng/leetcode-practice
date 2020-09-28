package me.fengorz.leetcode.graph.island_perimeter;
//给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
//
// 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。 
//
// 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿
//的周长。 
//
// 
//
// 示例 : 
//
// 输入:
//[[0,1,0,0],
// [1,1,1,0],
// [0,1,0,0],
// [1,1,0,0]]
//
//输出: 16
//
//解释: 它的周长是下面图片中的 16 个黄色的边：
//
//
// 
// Related Topics 哈希表 
// 👍 254 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sum += DFS(grid, i, j);
            }
        }
        return sum;
    }

    private int DFS(int[][] grid, int x, int y) {
        // 只计算陆地方块
        if (grid[x][y] == 0) {
            return 0;
        }

        int sum = 0;
        if (x - 1 > -1) {// 如果不是最顶边
            if (grid[x - 1][y] == 0) {// 如果其上面一块是水
                ++sum;
            }
        } else {// 如果是最顶边
            ++sum;
        }
        if (x + 1 < grid.length) {// 如果不是最底边
            if (grid[x + 1][y] == 0) {// 如果其下面一块是水
                ++sum;
            }
        } else {// 如果是最底边
            ++sum;
        }
        if (y - 1 > -1) {
            if (grid[x][y - 1] == 0) {
                ++sum;
            }
        } else {
            ++sum;
        }
        if (y + 1 < grid[x].length) {
            if (grid[x][y + 1] == 0) {
                ++sum;
            }
        } else {
            ++sum;
        }

        return sum;
    }


    // private int DFS(int[][] grid, int[][] gridVisit, int x, int y) {
    //     if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length) {
    //         return 0;
    //     }
    //     // 如果被访问过略过
    //     if (gridVisit[x][y] == 1) {
    //         return 0;
    //     }
    //
    //     int sum = 0;
    //     // 如果是陆地就计算其面积
    //     if (grid[x][y] == 1) {
    //         sum += countOnePerimeter(grid, x, y);
    //     }
    //     gridVisit[x][y] = 1;// 标记访问过
    //
    //     sum += DFS(grid, gridVisit, x - 1, y);
    //     sum += DFS(grid, gridVisit, x + 1, y);
    //     sum += DFS(grid, gridVisit, x, y - 1);
    //     sum += DFS(grid, gridVisit, x, y + 1);
    //     return sum;
    // }

    /**
     * [[0,1,0,0],
     *  [1,1,1,0],
     *  [0,1,0,0],
     *  [1,1,0,0]]
     */
}
//leetcode submit region end(Prohibit modification and deletion)
