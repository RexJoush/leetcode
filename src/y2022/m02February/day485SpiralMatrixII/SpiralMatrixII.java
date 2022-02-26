package y2022.m02February.day485SpiralMatrixII;

/**
 * @author Rex Joush
 * @time 2022.02.04
 */

/*
    螺旋矩阵 II
    https://leetcode-cn.com/problems/spiral-matrix-ii/

    给你一个正整数n ，生成一个包含 1 到 n^2 所有元素，
    且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。

    示例 1：
            1 2 3
            8 9 4
            7 6 5
    输入：n = 3
    输出：[[1,2,3],[8,9,4],[7,6,5]]

    示例 2：
        输入：n = 1
        输出：[[1]]

    提示：
        1 <= n <= 20

 */
public class SpiralMatrixII {

    /*
        顺序模拟即可
        结果：
            0 ms, 100.00%
            39.2 MB, 23.59%
     */
    public int[][] generateMatrix(int n) {
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        int index = 1;
        int[][] result = new int[n][n];

        while (index <= n * n) {
            // 填充顶部
            for (int i = left; i <= right; i++) {
                result[top][i] = index++;
            }
            top++; // 顶下移

            // 填充右侧
            for (int i = top; i <= bottom; i++) {
                result[i][right] = index++;
            }
            right--; // 有边界左移

            // 填充底部
            for (int i = right; i >= left; i--) {
                result[bottom][i] = index++;
            }
            bottom--; // 底上移

            // 填充左侧
            for (int i = bottom; i >= top; i--) {
                result[i][left] = index++;
            }
            left++; // 左边界右移
        }
        return result;
    }
}
