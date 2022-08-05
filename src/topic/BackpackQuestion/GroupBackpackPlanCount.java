package topic.BackpackQuestion;

/**
 * @author Rex Joush
 * @time 2022.08.05
 */

/*
    分组背包求方案数
 */
public class GroupBackpackPlanCount {

    /*
        leetcode 1155
     */
    public int numRollsToTarget2(int n, int k, int target) {
        // 做一个判断，如果 n * k 都无法满足，返回 0，此时可以大幅提高时间
        if (n * k < target) {
            return 0;
        }

        int mod = 1000000007;

        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= 0; j--) {
                dp[j] = 0;
                for (int m = 1; m <= k && j >= m; m++) {
                    dp[j] += dp[j - m];
                    dp[j] %= mod;
                }
            }
        }
        return dp[target];
    }

    /*
        分组背包求方案数
        定义 dp[i][j] 表示前 i 个骰子的和为 j 的方案数
        那么初始情况
            dp[0][0] = 1, dp[i][0] = 0
        递推过程,当前骰子取 1, 2, ..., k
            dp[i][j] = dp[i][j] + dp[i-1][j-k] 1 < k < n; k 为骰子的大小
     */
    public int numRollsToTarget(int n, int k, int target) {
        if (n * k < target) {
            return 0;
        }
        int mod = 1000000007;

        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                for (int m = 1; m <= k && j >= m; m++) {
                    dp[i][j] += dp[i - 1][j - m];
                    dp[i][j] %= mod;
                }
            }
        }
        return dp[n][target];
    }

}
