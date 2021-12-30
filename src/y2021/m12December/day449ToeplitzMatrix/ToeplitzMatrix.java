package y2021.m12December.day449ToeplitzMatrix;

/**
 * @author Rex Joush
 * @time 2021.12.30
 */

/*
    托普利茨矩阵
    https://leetcode-cn.com/problems/toeplitz-matrix/

    给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
    如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。

    示例 1：
            1  2  3  4
            5  1  2  3
            9  5  1  2
        输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
        输出：true
        解释：
        在上述矩阵中, 其对角线为:
        "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
        各条对角线上的所有元素均相同, 因此答案是 True 。
    示例 2：
            1  2
            2  2
        输入：matrix = [[1,2],[2,2]]
        输出：false
        解释：
        对角线 "[1, 2]" 上的元素不同。

    提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 20
        0 <= matrix[i][j] <= 99

 */
public class ToeplitzMatrix {

    /*
        遍历即可
        结果：
            0 ms, 100.00%
            38.8 MB, 5.11%
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {

        // 行数
        int row = matrix.length; // 3
        // 列数
        int col = matrix[0].length; // 4

        // 一行一列和一行两列，两行一列直接返回
        if (row + col <= 3) {
            return true;
        }

        // 做下半矩阵
        for (int i = 0; i < row; i++) {
            int base = matrix[i][0];
            int m = i + 1;
            int n = 1;
            while (m < row && n < col) {
                if (matrix[m][n] != base) {
                    return false;
                }
                m++;
                n++;
            }
        }

        // 判断上半矩阵
        for (int i = 1; i < col; i++) {
            int base = matrix[0][i];
            int m = 1;
            int n = i + 1;
            while (m < row && n < col) {
                if (matrix[m][n] != base) {
                    return false;
                }
                m++;
                n++;
            }
        }

        return true;
    }
}
