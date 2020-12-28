package December20.day82MatrixDiagonalSum;

/**
 * @author Joush
 * @time 2020.12.28
 */

/*
    矩阵对角线元素的和
    https://leetcode-cn.com/problems/matrix-diagonal-sum/

    给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
    请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。

    示例  1：
        输入：mat = [[1,2,3],
                    [4,5,6],
                    [7,8,9]]
        输出：25
        解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25
        请注意，元素 mat[1][1] = 5 只会被计算一次。
    示例  2：
        输入：mat = [[1,1,1,1],
                    [1,1,1,1],
                    [1,1,1,1],
                    [1,1,1,1]]
        输出：8
    示例 3：
        输入：mat = [[5]]
        输出：5

    提示：
        n == mat.length == mat[i].length
        1 <= n <= 100
        1 <= mat[i][j] <= 100

 */
public class MatrixDiagonalSum {
    /*
        遍历计算即可
        结果：
            0 ms, 100.00%
            38.6 MB, 67.88%
     */
    public int diagonalSum(int[][] mat) {

        int result = 0;

        // 遍历相加即可
        for (int i = 0; i < mat.length; i++) {
            result += mat[i][i];
            result += mat[mat.length - i - 1][i];
        }

        // 奇数，多加了中间的数，减去
        if (mat.length % 2 != 0){
            result -= mat[mat.length / 2][mat.length / 2];
        }

        return result;
    }
}
