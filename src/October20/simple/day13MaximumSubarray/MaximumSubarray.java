package October20.simple.day13MaximumSubarray;

/**
 * @author Joush
 * @time 2020.10.20
 */

/*

    最大子序和
    https://leetcode-cn.com/problems/maximum-subarray/

    给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    示例:
        输入: [-2,1,-3,4,-1,2,1,-5,4]
        输出: 6
        解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
    进阶:
        如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

 */
public class MaximumSubarray {

    // 每次往后加，加入当前序列之和为负，则不能为后续序列提供正因素，因此重新确定序列首元素
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }

}
