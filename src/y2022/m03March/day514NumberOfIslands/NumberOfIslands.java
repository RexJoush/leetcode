package y2022.m03March.day514NumberOfIslands;

/**
 * @author Rex Joush
 * @time 2022.03.05
 */

/*
    岛屿数量
    https://leetcode-cn.com/problems/number-of-islands/

    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。

    示例 1：
        输入：grid = [
          ["1","1","1","1","0"],
          ["1","1","0","1","0"],
          ["1","1","0","0","0"],
          ["0","0","0","0","0"]
        ]
        输出：1
    示例 2：
        输入：grid = [
          ["1","1","0","0","0"],
          ["1","1","0","0","0"],
          ["0","0","1","0","0"],
          ["0","0","0","1","1"]
        ]
        输出：3

    提示：
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 300
        grid[i][j] 的值为 '0' 或 '1'

 */
public class NumberOfIslands {

    /*
        回溯法，搜索遍历即可
        结果：
            4 ms, 36.19%
            50.1 MB, 6.03%
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] used = new boolean[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果此次搜索到一个点以上，则认为存在一个岛屿
                if (dfs(grid, i, j, used) > 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public int dfs(char[][] grid, int i, int j, boolean[][] used) {
        // 当前为水或者已经搜索过，就返回
        if (grid[i][j] == '0' || used[i][j]) {
            return 0;
        }
        // 此次搜索的范围 + 1，
        int count = 1;
        used[i][j] = true; // 当前节点置为已经搜索过
        // 搜索四周，记录搜索的点
        if (i - 1 >= 0) {
            count += dfs(grid, i - 1, j, used);
        }
        if (i + 1 < grid.length) {
            count += dfs(grid, i + 1, j, used);
        }

        if (j - 1 >= 0) {
            count += dfs(grid, i, j - 1, used);
        }
        if (j + 1 < grid[0].length) {
            count += dfs(grid, i, j + 1, used);
        }
        // 返回此次搜索的点
        return count;
    }
}
