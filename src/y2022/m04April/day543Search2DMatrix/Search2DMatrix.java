package y2022.m04April.day543Search2DMatrix;

/**
 * @author Rex Joush
 * @time 2022.04.03
 */

/*
    搜索二维矩阵
    https://leetcode-cn.com/problems/search-a-2d-matrix/

    编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
    每行中的整数从左到右按升序排列。
    每行的第一个整数大于前一行的最后一个整数。

    示例 1：
                1  3  5  7
               10 11 16 20
               23 30 34 60
        输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
        输出：true
    示例 2：
                1  3  5  7
               10 11 16 20
               23 30 34 60
        输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
        输出：false

    提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 100
        -104 <= matrix[i][j], target <= 10^4

 */
public class Search2DMatrix {

    /*
        二分法，先按第一列二分，找到最佳的行，在当前行进行二分查找，找到目标值
        结果：
            0 ms, 100.00%
            41.1 MB, 33.41%
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        int i = binSearch(matrix, target);
        int left = 0;
        int right = matrix[i].length - 1;
        if (matrix[i][0] == target) {
            return true;
        }
        // 查找指定值，常规二分，left <= true
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[i][mid] == target) {
                return true;
            } else if (matrix[i][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }


    /*
        查找行数，此处，如果二分查找不到就返回较小的值
        因此，mid = (right + left + 1) / 2;
        每次缩减区间 left = mid;
     */
    public int binSearch(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;

        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (matrix[mid][0] == target) {
                return mid;
            } else if (matrix[mid][0] < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
