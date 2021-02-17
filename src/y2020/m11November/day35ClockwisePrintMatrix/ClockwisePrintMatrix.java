package y2020.m11November.day35ClockwisePrintMatrix;

/**
 * @author Joush
 * @time 2020.11.11
 */

/*
    顺时针打印矩阵（剑指 Offer 29）
    https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
    
    输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

    示例 1：
        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[1,2,3,6,9,8,7,4,5]
    示例 2：
        输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        输出：[1,2,3,4,8,12,11,10,9,5,6,7]

    限制：
        0 <= matrix.length <= 100
        0 <= matrix[i].length <= 100

 */
public class ClockwisePrintMatrix {

    // 根据顺时针的顺序打印即可，注意边界的判断
    public int[] spiralOrder(int[][] matrix) {

        if (matrix.length == 0){
            return new int[0];
        }

        // 定义结果数组
        int res[] = new int[matrix.length * matrix[0].length];

        // 初始上边界
        int u = 0;
        // 初始下边界
        int d = matrix.length - 1;
        // 初始左边界
        int l = 0;
        // 初始右边界
        int r = matrix[0].length - 1;

        // 结果数组索引
        int index = 0;

        while (true){

            // 打印最上面一行，从左到右
            for (int i = l; i <= r; i++){
                res[index++] = matrix[u][i];
            }

            // 上边界下移，如果到达下边界，就退出循环
            if (++u > d){
                break;
            }

            // 打印右边一列，从上到下
            for (int i = u; i <= d; i++){
                res[index++] = matrix[i][r];
            }

            // 右边界左移，如果到达左边界，就退出循环
            if (--r < l){
                break;
            }

            // 打印下边一行，从右到左
            for (int i = r; i >= l; i--){
                res[index++] = matrix[d][i];
            }

            // 下边界上移，如果到达上边界，就退出循环
            if (--d < u){
                break;
            }

            // 打印左边一列，从下到上
            for (int i = d; i >= u; i--){
                res[index++] = matrix[i][l];
            }

            // 左边界右移，如果到达右边界，就退出循环
            if (++l > r){
                break;
            }

        }

        // 返回结果
        return res;
    }
}
