package y2022.m03March.day518Candy;

/**
 * @author Rex Joush
 * @time 2022.03.09
 */

/*
    分发糖果
    https://leetcode-cn.com/problems/candy/

    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
    你需要按照以下要求，给这些孩子分发糖果：
    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。

    示例 1：
        输入：ratings = [1,0,2]
        输出：5
        解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
    示例 2：
        输入：ratings = [1,2,2]
        输出：4
        解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
             第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。

    提示：
        n == ratings.length
        1 <= n <= 2 * 10^4
        0 <= ratings[i] <= 2 * 10^4

 */
public class Candy {

    /*
        贪心思想，从左至右比较两个值，较大者赋较大值，较小者赋较小值
        因为两侧，因此从右至左再来一次，求两者的最大值
        结果：
            0 ms, 98.41%
            41.5 MB, 68.72%
     */
    public int candy(int[] ratings) {

        int n = ratings.length;
        int[] left = new int[n];
        // 从左至右计算
        for (int i = 0; i < n; i++) {
            // 如果大了，就 + 1
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                // 否则就置为 1
                left[i] = 1;
            }
        }

        int[] right = new int[n];
        int result = 0;
        // 从右至左计算
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
            result += Math.max(left[i], right[i]);
        }
        // 返回结果
        return result;
    }
}
