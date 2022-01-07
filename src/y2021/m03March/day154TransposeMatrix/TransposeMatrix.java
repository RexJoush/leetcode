package y2021.m03March.day154TransposeMatrix;

/**
 * @author Rex Joush
 * @time 2021.03.10
 */

/*
    转置矩阵
    https://leetcode-cn.com/problems/transpose-matrix/

    给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
    矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。

    示例 1：
        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[[1,4,7],[2,5,8],[3,6,9]]
    示例 2：
        输入：matrix = [[1,2,3],[4,5,6]]
        输出：[[1,4],[2,5],[3,6]]

    提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 1000
        1 <= m * n <= 10^5
        -10^9 <= matrix[i][j] <= 10^9

 */
public class TransposeMatrix {

    /*
        转置即可
        结果：
             0 ms, 100.00%
             39.1 MB, 80.77%
     */
    public int[][] transpose(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;

    }
}
