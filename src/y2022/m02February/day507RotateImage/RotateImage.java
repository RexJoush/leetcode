package y2022.m02February.day507RotateImage;

/**
 * @author Rex Joush
 * @time 2022.02.26
 */

/*
    旋转图像
    https://leetcode-cn.com/problems/rotate-image/

    给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

    示例 1：
            1 2 3       7 4 1
            4 5 6 -->   8 5 2
            7 8 9       9 6 3
        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[[7,4,1],[8,5,2],[9,6,3]]
    示例 2：
             5  1  9 11         15 13  2  5
             2  4  8 10  -->    14  3  4  1
            13  3  6  7         12  6  8  9
            15 14 12 16         16  7 10 11
        输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
        输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

    提示：
        n == matrix.length == matrix[i].length
        1 <= n <= 20
        -1000 <= matrix[i][j] <= 1000

 */
public class RotateImage {

    /*
        由示例 2 的轮转关系可得
        (0, 0) -> (0, 3)    (1, 0) -> (0, 2)    (2, 0) -> (0, 1)    (3, 0) -> (0, 0)
        (0, 1) -> (1, 3)    (1, 1) -> (1, 2)    (2, 1) -> (1, 1)    (3, 1) -> (1, 0)
        (0, 2) -> (2, 3)    (1, 2) -> (2, 2)    (2, 2) -> (2, 1)    (3, 2) -> (2, 0)
        (0, 3) -> (3, 3)    (1, 3) -> (3, 2)    (2, 3) -> (3, 1)    (3, 3) -> (3, 0)

        matrix[i][j] = matrix[n-j-1][i]

        又因为，如果我们直接赋值的话，matrix[i][j] 的位置就会被覆盖掉，考虑使用临时变量存储
            temp = matrix[i][j];
            matrix[i][j] = matrix[n-j-1][i];
        而 matrix[n-j-1][i] 的值就被覆盖掉了，此时考虑，matrix[n-j-1][i] 应该去哪
            matrix[n-j-1][i] = matrix[n-i-1][n-j-1]
        继续考虑
            matrix[n-i-1][n-j-1] = matrix[n-1-(n-1-j)][n-1-i] = matrix[j][n-i-1]
        继续考虑
            matrix[j][n-i-1]] = matrix[n-1-(n-1-i)][j] = matrix[i][j] = temp;
        此时，循环转回，即四次一循环，只需循环四分之一即可

     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}
