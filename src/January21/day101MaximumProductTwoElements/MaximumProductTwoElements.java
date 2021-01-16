package January21.day101MaximumProductTwoElements;

/**
 * @author Joush
 * @time 2021.01.16
 */

/*
    数组中两元素的最大乘积
    https://leetcode-cn.com/problems/maximum-product-of-two-elements-in-an-array/

    给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
    请你计算并返回该式的最大值。

    示例 1：
        输入：nums = [3,4,5,2]
        输出：12
        解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
    示例 2：
        输入：nums = [1,5,4,5]
        输出：16
        解释：选择下标 i=1 和 j=3（下标从 0 开始），则可以获得最大值 (5-1)*(5-1) = 16 。
    示例 3：
        输入：nums = [3,7]
        输出：12

    提示：
        2 <= nums.length <= 500
        1 <= nums[i] <= 10^3

 */
public class MaximumProductTwoElements {

    /*
        遍历一遍找到最大值和第二大的值，返回各自-1后的相乘结果即可
        结果：
            0 ms, 100.00%
            38 MB, 69.81%
     */
    public int maxProduct(int[] nums) {
        // 最大值
        int max = Math.max(nums[0], nums[1]);
        // 第二大的值
        int subMax = Math.min(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 如果当前数字大于 max，则将 max 置为当前数字，且 subMax 为 max
            if (nums[i] >= max) {
                subMax = max;
                max = nums[i];
            }
            // 如果当前值小于最大值，但大于第二大的值，就更新第二大的值
            else if (nums[i] >= subMax){
                subMax = nums[i];
            }
        }
        // 返回结果
        return (max - 1) * (subMax - 1);
    }
}
