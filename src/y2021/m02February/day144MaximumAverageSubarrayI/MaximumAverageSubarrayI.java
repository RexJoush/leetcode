package y2021.m02February.day144MaximumAverageSubarrayI;

/**
 * @author Rex Joush
 * @time 2021.02.28
 */

/*
    子数组最大平均数 I
    https://leetcode-cn.com/problems/maximum-average-subarray-i/

    给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
    请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
    任何误差小于 10^-5 的答案都将被视为正确答案。

    示例 1：
        输入：nums = [1,12,-5,-6,50,3], k = 4
        输出：12.75
        解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
    示例 2：
        输入：nums = [5], k = 1
        输出：5.00000

    提示：
        n == nums.length
        1 <= k <= n <= 10^5
        -10^4 <= nums[i] <= 10^4

 */
public class MaximumAverageSubarrayI {

    /*
        滑动窗口，找到所有窗口中和的最大值，返回 max / k 即可
        结果：
            2 ms, 100.00%
            52.2 MB, 29.82%
     */
    public double findMaxAverage(int[] nums, int k) {

        int init = 0;
        // 计算初始窗口大小
        for (int i = 0; i < k; i++) {
            init += nums[i];
        }
        int max = init;
        // 计算每个窗口大小，并更新最大值
        for (int i = k; i < nums.length; i++) {
            // 减去队头元素
            init -= nums[i - k];
            // 加上新的元素
            init += nums[i];
            // 判断最大值
            max = Math.max(init, max);
        }
        // 返回最大的平均值
        return max * 1.0 / k;
    }

}
