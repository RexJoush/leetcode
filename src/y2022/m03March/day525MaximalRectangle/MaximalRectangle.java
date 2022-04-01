package y2022.m03March.day525MaximalRectangle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Rex Joush
 * @time 2022.03.16
 */

/*
    最大矩形
    https://leetcode-cn.com/problems/maximal-rectangle/

    给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

    示例 1：
            1  0  1  0  0
            1  0  1  1  1
            1  1  1  1  1
            1  0  0  1  0
        输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
        输出：6
        解释：最大矩形如上图所示。
    示例 2：
        输入：matrix = []
        输出：0
    示例 3：
        输入：matrix = [["0"]]
        输出：0
    示例 4：
        输入：matrix = [["1"]]
        输出：1
    示例 5：
        输入：matrix = [["0","0"]]
        输出：0

    提示：
        rows == matrix.length
        cols == matrix[0].length
        1 <= row, cols <= 200
        matrix[i][j] 为 '0' 或 '1'

 */
public class MaximalRectangle {

    /*
        参考柱状图的最大面积
            从上到下考虑柱状图的高度
                1  0  1  0  0
                1  0  1  1  1
                1  1  1  1  1
                1  0  0  1  0
            第一层的高度 1 0 1 0 0, 最大面积为 1
            第二层的高度 2 0 2 1 1, 最大面积为 3
            第三层的高度 3 1 3 2 2, 最大面积为 6
            第四层的高度 4 0 0 3 0, 最大面积为 4
            因此，最终的最大面积即为 6
        结果：
            4 ms, 95.92%
            44.2 MB, 68.75%
     */
    public int maximalRectangle(char[][] matrix) {

        int[] dp = new int[matrix[0].length];
        int max = 0;
        // 遍历每个行
        for (char[] chars : matrix) {
            // 更新当前行所代表的柱状图
            updateDp(dp, chars);
            // 计算最大面积
            max = Math.max(max, getMaxArea(dp));
        }

        return max;
    }

    // 更新新的柱状图数组
    private void updateDp(int[] dp, char[] matrix) {
        for (int i = 0; i < dp.length; i++) {
            // 当前为 0，直接为 0
            if (matrix[i] == '0') {
                dp[i] = 0;
            }
            // 当前为 1，等于上一行的值 + 1
            else {
                dp[i] = dp[i] + 1;
            }
        }
    }

    // 获取柱状图的最大面积
    public int getMaxArea(int[] heights) {
        int n = heights.length;
        // 两边加个 0，方便计算
        int[] temp = new int[n + 2];
        System.arraycopy(heights, 0, temp, 1, n);

        Deque<Integer> deque = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < temp.length; i++) {
            // 如果当前元素比栈顶元素小，那么就出栈，并计算最大值
            while (!deque.isEmpty() && temp[i] < temp[deque.peek()]) {
                int pop = deque.pop();
                // 宽度
                int wight = i - 1 - deque.peek();
                // 更新面积
                max = Math.max(max, temp[pop] * wight);
            }
            deque.push(i);
        }
        return max;
    }
}
