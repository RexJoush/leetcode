package y2022.m05May.day575Triangle;

import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.05.05
 */

/*
    三角形最小路径和
    https://leetcode.cn/problems/triangle/
    
    给定一个三角形 triangle ，找出自顶向下的最小路径和。
    每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
    也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

    示例 1：
        输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        输出：11
        解释：如下面简图所示：
           2
          3 4
         6 5 7
        4 1 8 3
        自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
    示例 2：
        输入：triangle = [[-10]]
        输出：-10

    提示：
    1 <= triangle.length <= 200
    triangle[0].length == 1
    triangle[i].length == triangle[i - 1].length + 1
    -10^4 <= triangle[i][j] <= 10^4

 */
public class Triangle {

    /*
        动态规划，首先考虑二维 dp
        示例一的三角形实际上如下
            2
            3 4
            6 5 7
            4 1 8 3
        可以观察到，只要不是第一列的数，都可以由上方的值得来，最后一列的数可以有左上方得来
        即
            dp[i][j] = dp[i-1][j] (j == 0)
            dp[i][j] = dp[i-1][j-1] (j == i + 1)
            dp[i][j] = min(dp[i-1][j], dp[i-1][j-1])
        这样，只要找到最后一行中最小的值即可
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        // dp 求解
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                int val = triangle.get(i).get(j);
                // 第一列直接获取上一个
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + val;
                } else if (j == i) {
                    // 最后一列取左上角的值
                    dp[i][j] = dp[i - 1][j - 1] + val;
                } else {
                    // 其余位置取两个较小的值
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + val;
                }

            }
        }
        // 找到最后一行的最小值即可
        int result = dp[n - 1][0];
        for (int i = 0; i < n; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;
    }

}
