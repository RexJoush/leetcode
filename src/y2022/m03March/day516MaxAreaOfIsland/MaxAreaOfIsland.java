package y2022.m03March.day516MaxAreaOfIsland;

/**
 * @author Rex Joush
 * @time 2022.03.07
 */

/*
    岛屿的最大面积
    https://leetcode-cn.com/problems/max-area-of-island/

    给你一个大小为 m x n 的二进制矩阵 grid 。
    岛屿 是由一些相邻的'1'(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
    你可以假设 grid 的四个边缘都被 0（代表水）包围着。
    岛屿的面积是岛上值为 1 的单元格的数目。
    计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。

    示例 1：

    输入：grid = [
        [0,0,1,0,0,0,0,1,0,0,0,0,0],
            [0,0,0,0,0,0,0,1,1,1,0,0,0],
            [0,1,1,0,1,0,0,0,0,0,0,0,0],
            [0,1,0,0,1,1,0,0,1,0,1,0,0],
            [0,1,0,0,1,1,0,0,1,1,1,0,0],
            [0,0,0,0,0,0,0,0,0,0,1,0,0],
            [0,0,0,0,0,0,0,1,1,1,0,0,0],
            [0,0,0,0,0,0,0,1,1,0,0,0,0]]
        输出：6
        解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
    示例 2：
        输入：grid = [[0,0,0,0,0,0,0,0]]
        输出：0

    提示：
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 50
        grid[i][j] 为 0 或 1

 */
public class MaxAreaOfIsland {

    /*
        深度优先搜索
        结果：
            2 ms, 74.37%
            41.4 MB, 39.98%
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid[0].length;
        int n = grid.length;
        int max = 0;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(grid, i, j, used));
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int i, int j, boolean[][] used) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] == 0 || used[i][j]) {
            return 0;
        }
        int count = 1;
        used[i][j] = true;
        count += dfs(grid, i - 1, j, used);
        count += dfs(grid, i + 1, j, used);
        count += dfs(grid, i, j - 1, used);
        count += dfs(grid, i, j + 1, used);
        return count;
    }
}
