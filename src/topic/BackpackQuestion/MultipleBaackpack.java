package topic.BackpackQuestion;

/**
 * @author Rex Joush
 * @time 2022.08.03
 */

/*
    多重背包，每个物品可拿 time 次
 */
public class MultipleBaackpack {

    public static void main(String[] args) {
        int[] value = new int[]{1, 2};
        int[] weight = new int[]{1, 2};
        int[] times = new int[]{2, 1};
        int limit = 5;
        System.out.println(new MultipleBaackpack().getMax2(value, weight, times, limit));
    }

    /*
        多重背包的空间优化
     */
    public int getMax2(int[] value, int[] weight, int[] times, int limit) {
        int[] dp = new int[limit + 1];

        for (int i = 0; i < value.length; i++) {
            for (int j = limit; j >= weight[i]; j--) {
                for (int k = 0; k <= times[i] && j > k * weight[i]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                }
            }
        }
        return dp[limit];
    }
    /*
        多重背包其实和完全背包差不多
        dp[i][j] 定义为前 i 件 物品，在容量为 j 的情况下的最大价值
        那么，对于第 i 件物品，有以下几种情况
            不拿，         dp[i][j] = dp[i-1][j]
            拿 1 件，      dp[i][j] = dp[i-1][j-1*weight[i]] + value[i] * 1
            ....
            拿 time[i] 件  dp[i][j] = dp[i-1][j-times[i]*weight[i]] + value[i] * time[i]
        因此 dp[i][j] = max(dp[i-1][j], dp[i-1][j-times[i] * weight[i]] + value[i] * times[i])
     */
    public int getMax(int[] value, int[] weight, int[] times, int limit) {
        int n = value.length;
        int[][] dp = new int[n][limit + 1];

        // 初始化第 1 件物品
        for (int i = weight[0]; i <= limit; i++) {
            // 防止超过数量
            int v = Math.min(i / weight[0], times[0]);
            dp[0][i] = v * value[0];

        }

        // dp 递推过程
        for (int i = 1; i < n; i++) {
            for (int j = weight[i]; j <= limit; j++) {
                int v = 0;
                for (int k = 0; k <= times[i]; k++) {
                    // 超过容量结束循环
                    if (j < k * weight[i]) {
                        break;
                    }
                    v = Math.max(v, dp[i - 1][j - k * weight[i]] + k * value[i]);
                }
                dp[i][j] = v;
            }
        }
        return dp[n - 1][limit];
    }

}
