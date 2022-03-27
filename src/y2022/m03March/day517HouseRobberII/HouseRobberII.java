package y2022.m03March.day517HouseRobberII;

/**
 * @author Rex Joush
 * @time 2022.03.08
 */

/*
    打家劫舍 II
    https://leetcode-cn.com/problems/house-robber-ii/

    你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
    同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
    给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

    示例1：
        输入：nums = [2,3,2]
        输出：3
        解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
    示例 2：
        输入：nums = [1,2,3,1]
        输出：4
        解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
            偷窃到的最高金额 = 1 + 3 = 4 。
    示例 3：
        输入：nums = [1,2,3]
        输出：3

    提示：
        1 <= nums.length <= 100
        0 <= nums[i] <= 1000

 */
public class HouseRobberII {

    /*
        动态规划，当长度只有1，或者2时，取其中较大值即可
        当长度超过三，那么首尾就相邻了，因此考虑偷第一个或者最后一个
        即考虑，[0,n-2] 和 [2,n-1]，使用两次 dp，取两次较大值即可
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int max = 0;
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 考虑，[0, n-1) 区间
        for (int i = 2; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        max = dp[nums.length - 2];

        // 考虑 [1, n) 区间
        dp[1] = nums[1];
        dp[2] = Math.max(nums[1], nums[2]);
        for (int i = 3; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        // 返回两个最大值
        return Math.max(dp[nums.length - 1], max);
    }
}
