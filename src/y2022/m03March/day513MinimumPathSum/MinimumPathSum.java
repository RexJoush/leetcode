package y2022.m03March.day513MinimumPathSum;

/**
 * @author Rex Joush
 * @time 2022.03.04
 */

/*
    最小路径和
    https://leetcode-cn.com/problems/minimum-path-sum/

    给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。

    示例 1：
            1  3  1
            1  5  1
            4  2  1
        输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
        输出：7
        解释：因为路径 1→3→1→1→1 的总和最小。
    示例 2：
        输入：grid = [[1,2,3],[4,5,6]]
        输出：12

 */
public class MinimumPathSum {

    /*
        动态规划
            转移方程：dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        结果：
            2 ms, 95.02%
            43.7 MB 55.75%
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        // 填充第一行
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        // 初始化第一列
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }

        // 计算 dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        // 返回
        return dp[m - 1][n - 1];
    }
}
