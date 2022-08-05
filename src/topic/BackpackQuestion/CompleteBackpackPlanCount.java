package topic.BackpackQuestion;

/**
 * @author Rex Joush
 * @time 2022.08.05
 */

/*
    完全背包求方案数
 */
public class CompleteBackpackPlanCount {

    /*
        leetcode 518
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
