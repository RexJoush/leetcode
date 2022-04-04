package y2022.m04April.day541MaximalSquare;

/**
 * @author Rex Joush
 * @time 2022.04.01
 */

/*
    最大正方形
    https://leetcode-cn.com/problems/maximal-square/

    在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。

    示例 1：
            1  0  1  0  0
            1  0 {1 [1} 1]      {} 或 [] 均为 4
            1  1 {1 [1} 1]
            1  0  0  1  0
    输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    输出：4
    示例 2：
            0 [1]
           [1] 0
        输入：matrix = [["0","1"],["1","0"]]
        输出：1
    示例 3：
        输入：matrix = [["0"]]
        输出：0

    提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 300
        matrix[i][j] 为 '0' 或 '1'

 */
public class MaximalSquare {

    /*
        动态规划
            经典二维 dp, dp[i][j] 表示以 i,j 为右下角能构成的最大正方形
            if(matrix == '1')
                dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
            else
                dp[i][j] = 0
            记录此过程的最大值即可
        结果：
            6 ms, 83.92%
            52.6 MB, 55.10%
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 当前为 1，那么考虑取左上、左、上的较小值 + 1
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max * max;
    }
}
