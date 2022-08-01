package y2022.m05May.day581CoinChange2;

/**
 * @author Rex Joush
 * @time 2022.05.11
 */

/*
    零钱兑换 II
    https://leetcode.cn/problems/coin-change-2/

    给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
    请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
    假设每一种面额的硬币有无限个。题目数据保证结果符合 32 位带符号整数。

    示例 1：
        输入：amount = 5, coins = [1, 2, 5]
        输出：4
        解释：有四种方式可以凑成总金额：
        5=5
        5=2+2+1
        5=2+1+1+1
        5=1+1+1+1+1
    示例 2：
        输入：amount = 3, coins = [2]
        输出：0
        解释：只用面额 2 的硬币不能凑成总金额 3 。
    示例 3：
        输入：amount = 10, coins = [10]
        输出：1

    提示：
        1 <= coins.length <= 300
        1 <= coins[i] <= 5000
        coins 中的所有值 互不相同
        0 <= amount <= 5000

 */
public class CoinChange2 {

    /*
        完全背包，空间优化
        结果：
            2 ms, 99.97%
            38.8 MB, 80.38%
     */
    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[j] += dp[j - coins[i - 1]];
            }
        }
        return dp[amount];
    }

    /*
        完全背包
            将 dp[i][j] 定义为第 i 枚硬币之前，总和为 j 的方案个数
            那么显然初始情况
                dp[0][0] = 1; dp[0][i] = 0; 1 <= i <= n;
            考虑状态转移方程，对于 第 i 枚硬币，两种情况
                不拿当前硬币
                    dp[i][j] = dp[i-1][j]
                当前硬币可以拿多次，因此，等于拿 1 次，2 次，到 k 次的总和
                    dp[i][j] = dp[i-1][j-k*coins[i - 1]]  1 <= k <= j / coins[i-1]
            结果：
                85 ms, 5.06%
                47.7 MB, 18.28%
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                for (int k = 0; k * coins[i - 1] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

}
