package y2022.m04April.day542CountSquareSubMatricesWithAllOnes;

/**
 * @author Rex Joush
 * @time 2022.04.02
 */

/*
    统计全为 1 的正方形子矩阵
    https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/

    给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。

    示例 1：
        输入：matrix =
        [
          [0,1,1,1],
          [1,1,1,1],
          [0,1,1,1]
        ]
        输出：15
        解释：
        边长为 1 的正方形有 10 个。
        边长为 2 的正方形有 4 个。
        边长为 3 的正方形有 1 个。
        正方形的总数 = 10 + 4 + 1 = 15.
    示例 2：
        输入：matrix =
        [
          [1,0,1],
          [1,1,0],
          [1,1,0]
        ]
        输出：7
        解释：
        边长为 1 的正方形有 6 个。
        边长为 2 的正方形有 1 个。
        正方形的总数 = 6 + 1 = 7.

    提示：
        1 <= arr.length <= 300
        1 <= arr[0].length <= 300
        0 <= arr[i][j] <= 1

 */
public class CountSquareSubMatricesWithAllOnes {

    /*
        动态规划
            经典二维 dp, dp[i][j] 表示以 i,j 为右下角能构成的最大正方形
            if(matrix == '1')
                dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
            else
                dp[i][j] = 0
            最后，将所有 dp 值求和即可
        结果：
            5 ms, 97.84%
            49.9% 77.36%
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int result = 0;
        int[][] dp = new int[m][n];

        // dp 初始化
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
            result += dp[0][i];

        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0];
            result += dp[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    result += dp[i][j];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return result;
    }
}
