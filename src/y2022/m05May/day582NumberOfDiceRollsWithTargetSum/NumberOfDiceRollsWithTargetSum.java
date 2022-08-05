package y2022.m05May.day582NumberOfDiceRollsWithTargetSum;

/**
 * @author Rex Joush
 * @time 2022.05.12
 */

/*
    掷骰子的N种方法
    https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/
    
    这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
    给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
    答案可能很大，你需要对 10^9 + 7 取模 。

    示例 1：
        输入：n = 1, k = 6, target = 3
        输出：1
        解释：你扔一个有 6 张脸的骰子。
        得到 3 的和只有一种方法。
    示例 2：
        输入：n = 2, k = 6, target = 7
        输出：6
        解释：你扔两个骰子，每个骰子有6个面。
        得到 7 的和有 6 种方法 1+6 2+5 3+4 4+3 5+2 6+1。
    示例 3：
        输入：n = 30, k = 30, target = 500
        输出：222616187
        解释：返回的结果必须是对 10^9 + 7 取模。

    提示：
        1 <= n, k <= 30
        1 <= target <= 1000

 */
public class NumberOfDiceRollsWithTargetSum {

    /*
        空间优化
        结果：
            6 ms, 96.28%
            38.1 MB, 95.17%
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
        结果：
            9 ms, 89.09%
            38.9 MB, 68.35%
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
