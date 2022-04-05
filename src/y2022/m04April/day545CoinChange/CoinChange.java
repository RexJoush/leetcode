package y2022.m04April.day545CoinChange;

/**
 * @author Rex Joush
 * @time 2022.04.05
 */

/*
    零钱兑换
    https://leetcode-cn.com/problems/coin-change/

    给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。

    示例 1：
        输入：coins = [1, 2, 5], amount = 11
        输出：3
        解释：11 = 5 + 5 + 1
    示例 2：
        输入：coins = [2], amount = 3
        输出：-1
    示例 3：
        输入：coins = [1], amount = 0
        输出：0

    提示：
        1 <= coins.length <= 12
        1 <= coins[i] <= 2^31 - 1
        0 <= amount <= 104

 */
public class CoinChange {

    /*
        完全背包，恰好装满问题
        结果：
            12 ms, 76.76%
            40.7 MB, 68.34%
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        // 初始化，恰好装满，则初始化为 Integer.MIN_VAlUE
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = Integer.MAX_VALUE / 2;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                // 延续前一个，或者是 j-coins[i-1] 的较小者
                if (j >= coins[i - 1] && dp[j - coins[i - 1]] != Integer.MIN_VALUE / 2) {
                    // +1 表示价值为 1，重量为金币的值
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                }
            }
        }
        // 如果没更新，说明不满足，返回 -1
        return dp[amount] == Integer.MAX_VALUE / 2 ? -1 : dp[amount];
    }
}
