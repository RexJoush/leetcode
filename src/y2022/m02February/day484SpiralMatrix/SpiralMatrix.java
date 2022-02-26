package y2022.m02February.day484SpiralMatrix;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.02.03
 */

/*
    螺旋矩阵
    https://leetcode-cn.com/problems/spiral-matrix/

    给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序，返回矩阵中的所有元素。

    示例 1：
                1  2  3
                4  5  6
                7  8  9
        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[1,2,3,6,9,8,7,4,5]
    示例 2：
                1   2   3   4
                5   6   7   8
                9  10  11  12
        输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        输出：[1,2,3,4,8,12,11,10,9,5,6,7]

    提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 10
        -100 <= matrix[i][j] <= 100

 */
public class SpiralMatrix {

    /*
        模拟顺序遍历即可
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        // 定义上下左右四个边界
        int left = 0;
        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;

        List<Integer> res = new LinkedList<>();

        while (true) {
            // 从左移动到最右
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            // 上边界下移 超过了下边界，就结束循环
            if (++top > bottom) {
                break;
            }

            // 右侧从上往下
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }

            // 右边界左移如果超过左边界，结束
            if (--right < left) {
                break;
            }

            // 底边，从右往左移动
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }

            // 下边界上移如果小于上边界，结束
            if (--bottom < top) {
                break;
            }

            // 左边，从下往上移动
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            // 左边界右移超过有边界，结束
            if (++left > right) {
                break;
            }
        }
        return res;
    }
}
