package y2022.m05May.day577MinimumFallingPathSumII;

/**
 * @author Rex Joush
 * @time 2022.05.07
 */

/*
    下降路径最小和  II
    https://leetcode.cn/problems/minimum-falling-path-sum-ii/
    
    给你一个 n x n 整数矩阵 arr ，请你返回 非零偏移下降路径 数字和的最小值。
    非零偏移下降路径 定义为：
        从 arr 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
    
    示例 1：
            [1]  2  3
             4  [5] 6
            [7]  8  9
        输入：arr = [[1,2,3],[4,5,6],[7,8,9]]
        输出：13
        解释： 所有非零偏移下降路径包括：
            [1,5,9], [1,5,7], [1,6,7], [1,6,8],
            [2,4,8], [2,4,9], [2,6,7], [2,6,8],
            [3,4,8], [3,4,9], [3,5,7], [3,5,9]
            下降路径中数字和最小的是 [1,5,7] ，所以答案是 13 。
    示例 2：
        输入：grid = [[7]]
        输出：7
    
    提示：
        n == grid.length == grid[i].length
        1 <= n <= 200
        -99 <= grid[i][j] <= 99

 */
public class MinimumFallingPathSumII {

    /*
        二维动态规划
        第一行等于本身 dp[0][i] = grid[0][i]
        第二行之后
            dp[i][j] = otherMin(dp[i-1][j])
        最后，找到最后一行的最小值即可

        [[-73, 61, 43, -48, -36],
         [  3, 30, 27,  57,  10],
         [ 96,-76, 84,  59, -15],
         [  5,-49, 76,  31,  -7],
         [ 97, 91, 61, -46,  67]]
     */
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 1) {
            return grid[0][0];
        }

        int[][] dp = new int[n][n];

        // 第一行等于自己
        System.arraycopy(grid[0], 0, dp[0], 0, n);

        // 第二行之后 dp 即可
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][1] + grid[i][0];
            for (int j = 1; j < n - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]) + grid[i][j];
            }
            dp[i][n - 1] = dp[i - 1][n - 2] + grid[i][n - 1];
        }

        // 找到最后一行的最小值即可
        int result = dp[n - 1][0];

        for (int i = 1; i < n; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;
    }

}
