package y2021.m04April.day177LongestIncreasingSubsequence;

/**
 * @author Rex Joush
 * @time 2021.04.02
 */

/*
    最长递增子序列
    https://leetcode-cn.com/problems/longest-increasing-subsequence/

    给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
    例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

    示例 1：
        输入：nums = [10,9,2,5,3,7,101,18]
        输出：4
        解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
    示例 2：
        输入：nums = [0,1,0,3,2,3]
        输出：4
    示例 3：
        输入：nums = [7,7,7,7,7,7,7]
        输出：1

    提示：
        1 <= nums.length <= 2500
        -10^4 <= nums[i] <= 10^4

 */
public class LongestIncreasingSubsequence {

    /*
        动态规划
            dp[i] 表示 nums[0...i] 的最长递增子序列
            存在 dp[j], [0...j] 使得 nums[i] > nums[j], 则 dp[i] = dp[j] + 1
            即，遍历 dp[0...j] 找到最大的满足 nums[i] > nums[j], dp[j]，记录此过程的最大值即可
        结果：
            61 ms, 28.81%
            40.6 MB, 67.91%
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            // 默认为 1
            dp[i] = 1;
            // 遍历 0-i, 找到最大的满足 nums[i] > nums[j] 的 dp[j]，赋值即可
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 记录此过程的最大值
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
