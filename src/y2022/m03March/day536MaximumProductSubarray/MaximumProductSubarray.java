package y2022.m03March.day536MaximumProductSubarray;

/**
 * @author Rex Joush
 * @time 2022.03.27
 */

/*
    乘积最大子数组
    https://leetcode-cn.com/problems/maximum-product-subarray/

    给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。
    子数组 是数组的连续子序列。

    示例 1:
        输入: nums = [2,3,-2,4]
        输出: 6
        解释: 子数组 [2,3] 有最大乘积 6。
    示例 2:
        输入: nums = [-2,0,-1]
        输出: 0
        解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

    提示:
        1 <= nums.length <= 2 * 104
        -10 <= nums[i] <= 10
        nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数

 */
public class MaximumProductSubarray {

    /*
        动态规划，与最大连续字段和一样，但有个问题，如果当前为负数的话，最大字段和问题就出现错误了
        那么即，当前为负数的话，我们考虑存储一个前面的最小值，那么负负得正，也可能为最大值
        即
            由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值 min
        结果：
            2 ms, 71.32%
            41.4 MB, 43.47%
     */
    public int maxProduct(int[] nums) {
        int dpMax = 1;
        int dpMin = 1;
        int result = Integer.MIN_VALUE;
        for (int num : nums) {
            // 负值，会让最大变为最小
            if (num < 0) {
                int temp = dpMax;
                dpMax = dpMin;
                dpMin = temp;
            }
            // 更新最大值，之前的最大值*自己或自己
            dpMax = Math.max(dpMax * num, num);
            // 更新最小值，之前的最小值*自己或自己
            dpMin = Math.min(dpMin * num, num);
            result = Math.max(dpMax, result);
        }
        return result;
    }

}
