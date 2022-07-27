package y2022.m05May.day576MinimumFallingPathSum;

/**
 * @author Rex Joush
 * @time 2022.05.06
 */

/*
    下降路径最小和
    https://leetcode.cn/problems/minimum-falling-path-sum/
    
    给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
    下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
    在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
    具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
    
    示例 1：
            2 [1] 3         2 [1] 3
            6 [5] 4         6  5 [4]
           [7] 8  9         7 [8] 9
        输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
        输出：13
        解释：如图所示，为和最小的两条下降路径
    示例 2：
            [-19]    57
            [-40]    -5
        输入：matrix = [[-19,57],[-40,-5]]
        输出：-59
        解释：如图所示，为和最小的下降路径
    
    提示：
        n == matrix.length == matrix[i].length
        1 <= n <= 100
        -100 <= matrix[i][j] <= 100

 */
public class MinimumFallingPathSum {

    /*
        二维动态规划
            第一行等于自己 dp[0][i] = matrix[0][i]
            第二行
            当 j == 0 时，即第一列，可以来自上一行同列和上一行后一列
                dp[i][j] = min(dp[i-1][j], dp[i-1][j+1]) + matrix[i][j]
            当 j == n - 1 时，即最后一列，可以来自上一行同列和上一行前一列
                dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + matrix[i][j]
            否则，可以来自上一行前一列，同列，后一列三者较小值
                dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i-1][j+1])
            最后，找到最后一行的最小值即可
        结果：
            4 ms, 67.43%
            42.1 MB, 26.64%
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        // 如果只有一列，返回即可
        if (n == 1) {
            return matrix[0][0];
        }
        int[][] dp = new int[n][n];

        // 第一行等于自己
        System.arraycopy(matrix[0], 0, dp[0], 0, n);

        // 计算 dp
        for (int i = 1; i < n; i++) {
            // 第一列
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + matrix[i][0];
            // 中间的值
            for (int j = 1; j < n - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1])) + matrix[i][j];
            }
            // 最后一列
            dp[i][n - 1] = Math.min(dp[i - 1][n - 1], dp[i - 1][n - 2]) + matrix[i][n - 1];
        }

        // 找到最后一行的最小值即可
        int result = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;

    }

}
