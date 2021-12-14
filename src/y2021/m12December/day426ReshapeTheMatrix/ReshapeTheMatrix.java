package y2021.m12December.day426ReshapeTheMatrix;

/**
 * @author Rex Joush
 * @time 2021.12.07
 */

/*
    重塑矩阵
    https://leetcode-cn.com/problems/reshape-the-matrix/

    在 MATLAB 中，有一个非常有用的函数 reshape,
    它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
    给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c,
    分别表示想要的重构的矩阵的行数和列数。
    重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
    如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。

    示例 1：
        输入：mat = [[1,2],[3,4]], r = 1, c = 4
        输出：[[1,2,3,4]]
    示例 2：
        输入：mat = [[1,2],[3,4]], r = 2, c = 4
        输出：[[1,2],[3,4]]

    提示：
        m == mat.length
        n == mat[i].length
        1 <= m, n <= 100
        -1000 <= mat[i][j] <= 1000
        1 <= r, c <= 300

 */
public class ReshapeTheMatrix {

    /*
        遍历一遍，赋值即可
        结果：
            1 ms, 32.08%
            38.8 MB, 95.85%
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {

        // 不满足返回原矩阵
        if (mat.length * mat[0].length != r * c) {
            return mat;
        }

        int[][] res = new int[r][c];
        int m = 0;
        int n = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                // n 控制列数
                if (n == c) {
                    m++;
                    n = 0;
                }
                res[m][n] = mat[i][j];
                n++;
            }
        }

        return res;
    }

    /*
        简便写法
        结果：
            0 ms, 100.00%
            38.8 MB, 95.85%
     */
    public int[][] matrixReshape2(int[][] mat, int r, int c) {

        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }

        int[][] ans = new int[r][c];
        for (int i = 0; i < m * n; i++) {
            ans[i / c][i % c] = mat[i / n][i % n];
        }
        return ans;
    }

}
