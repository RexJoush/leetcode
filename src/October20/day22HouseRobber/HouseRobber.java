package October20.day22HouseRobber;

/**
 * @author Joush
 * @time 2020.10.29
 */

/*

    打家劫舍
    https://leetcode-cn.com/problems/house-robber/
    
    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

    示例 1：
        输入：[1,2,3,1]
        输出：4
        解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
             偷窃到的最高金额 = 1 + 3 = 4 。
    示例 2：
        输入：[2,7,9,3,1]
        输出：12
        解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
             偷窃到的最高金额 = 2 + 9 + 1 = 12 。

 */
public class HouseRobber {

    public int rob2(int[] nums) {
        // 空数组返回0
        if (nums.length == 0) {
            return 0;
        }
        // 一个数就返回
        if (nums.length == 1) {
            return nums[0];
        }

        int[] resultArr = new int[nums.length];
        // 第一天为第一个值
        resultArr[0] = nums[0];

        // 第二天为 前两个的大值
        resultArr[1] = Math.max(nums[0], nums[1]);

        /*
            动态规划思想，第 i 天的值，等于 i-2 天的值加上今天得值 和 第 i-1 天的值 两个之间的最大值
            dp[i] = max( dp[i-2] + nums[i], dp[i-1] );
         */
        for (int i = 2; i < nums.length; i++) {
            resultArr[i] = Math.max(resultArr[i - 2] + nums[i], resultArr[i - 1]);
        }

        // 将最后一个值返回
        return resultArr[nums.length - 1];

    }


    // dp[i] = max(dp[i - 2] + nums[i], dp[i - 1]);
    /*
        递归解法，会超时
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return dp(nums.length - 1, nums);
    }


    // 递归，第 i 天的最大值，但会超时
    public int dp(int i, int[] nums) {

        // 只有一天
        if (i == 0) {
            return nums[0];
        }
        // 只有两天
        else if (i == 1) {
            return Math.max(nums[0], nums[1]);
        }
        // 返回前两天 + 当前天和前一天之前的最大值
        else {
            return Math.max(dp(i - 2, nums) + nums[i], dp(i - 1, nums));
        }
    }

}
