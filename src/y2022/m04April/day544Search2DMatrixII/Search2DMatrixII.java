package y2022.m04April.day544Search2DMatrixII;

/**
 * @author Rex Joush
 * @time 2022.04.04
 */

/*
    搜索二维矩阵 II
    https://leetcode-cn.com/problems/search-a-2d-matrix-ii/

    编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。

    示例 1：
                1  4  7 11 15
                2 [5] 8 12 19
                3  6  9 16 22
               10 13 14 17 24
               18 21 23 26 30
        输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
        输出：true
    示例 2：
                1  4  7 11 15
                2  5  8 12 19
                3  6  9 16 22
               10 13 14 17 24
               18 21 23 26 30
        输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
        输出：false

    提示：
        m == matrix.length
        n == matrix[i].length
        1 <= n, m <= 300
        -10^9 <= matrix[i][j] <= 10^9
        每行的所有元素从左到右升序排列
        每列的所有元素从上到下升序排列
        -10^9 <= target <= 10^9

 */
public class Search2DMatrixII {

    /*
        从右上角往左下角走，即比当前值大，就往下走，比当前之小，就往左走，即二叉搜索树
        结果：
            5 ms, 95.04%
            47.1 MB, 43.13%
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;

        while (j >= 0 && i < matrix.length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                // 往下走
                i++;
            } else {
                // 往左走
                j--;
            }
        }
        return false;
    }
}
