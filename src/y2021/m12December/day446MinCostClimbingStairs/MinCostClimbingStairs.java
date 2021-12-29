package y2021.m12December.day446MinCostClimbingStairs;

/**
 * @author Rex Joush
 * @time 2021.12.27
 */

/*
    使用最小花费爬楼梯
    https://leetcode-cn.com/problems/min-cost-climbing-stairs/

    给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
    一旦你支付此费用，即可选择向上爬一个或者两个台阶。
    你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
    请你计算并返回达到楼梯顶部的最低花费。

    示例 1：
        输入：cost = [10,15,20]
        输出：15
        解释：你将从下标为 1 的台阶开始。
        - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
        总花费为 15 。
    示例 2：
        输入：cost = [1,100,1,1,1,100,1,1,100,1]
        输出：6
        解释：你将从下标为 0 的台阶开始。
        - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
        - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
        - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
        - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
        - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
        - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
        总花费为 6 。

    提示：
        2 <= cost.length <= 1000
        0 <= cost[i] <= 999

 */
public class MinCostClimbingStairs {

    /*
        动态规划
        到达当前台阶 n 的花费，可由台阶 n-1 的花费 + 到达台阶 n-1 的花费构成
        或 台阶 n-2 的花费 + 到达台阶 n-2 的花费构成
        即
            dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
            其中，dp[0] = dp[1] = 0;
        结果：
            1 ms, 86.26%
            38.3 MB, 5.10%
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];

    }

    /*
        优化空间，不用存储整个花费数组，仅需存储前两个即可
        结果：
            0 ms, 100.00%
            38.4 MB, 5.10%
     */
    public int minCostClimbingStairs2(int[] cost) {
        // n - 2 的花费
        int pre = 0;
        // n - 1 的花费
        int current = 0;

        for (int i = 2; i <= cost.length; i++) {
            int next = Math.min(current + cost[i - 1], pre + cost[i - 2]);
            pre = current;
            current = next;
        }
        return current;

    }
}
