package y2020.m11November.day28LongestContinuousIncreasingSubsequence;

/**
 * @author Joush
 * @time 2020.11.04
 */

/*
    最长连续递增序列
    https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/

    给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
    连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1]
    那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。

    示例 1：
        输入：nums = [1,3,5,4,7]
        输出：3
        解释：最长连续递增序列是 [1,3,5], 长度为3。
             尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
    示例 2：
        输入：nums = [2,2,2,2,2]
        输出：1
        解释：最长连续递增序列是 [2], 长度为1。
    提示：
        0 <= nums.length <= 10000
        -1000000000 <= nums[i] <= 1000000000

 */
public class LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {

        // 如果空数组，返回0
        if (nums.length == 0) {
            return 0;
        }

        // 定义长度计数
        int length = 0;

        // 定义最长的计数
        int max = 0;

        // 循环开始
        for (int i = 0; i < nums.length - 1; i++) {
            // 如果当前是在递增，length 自增
            if (nums[i + 1] > nums[i]) {
                length++;
            }
            // 否则，把 length 和 max 较大者赋值给 max，并把当前的 length 置零
            else {
                max = Math.max(max, length);
                length = 0;
            }
        }
        // 防止出现单调数列，所以加一句比较
        max = Math.max(max, length);
        return max + 1;
    }
}
