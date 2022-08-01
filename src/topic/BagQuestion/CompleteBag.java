package topic.BagQuestion;

/**
 * @author Rex Joush
 * @time 2022.08.01
 */

/*
    完全背包，即一件物品可拿多次
 */
public class CompleteBag {

    public static void main(String[] args) {
        int[] value = new int[]{1, 2};
        int[] weight = new int[]{1, 2};
        int limit = 5;
        System.out.println(new CompleteBag().maxValue2(limit, value, weight));
    }

    /*
        定义 dp[i][j] 表示截止第 i 件物品，容量为 j 所能达到的最大值
        因为每件物品可以被拿多次，因此对于每个 dp[i][j] 来说，可能的情况如下
            1.选择 0 件物品 i 的价值最大，即 dp[i][j] = dp[i-1][j]
            2.选择 1 件物品 i 的价值最大，即 dp[i][j] = dp[i-1][j - w[i] * 1] + 1 * v[i]
            3.选择 2 件物品 i 的价值最大，即 dp[i][j] = dp[i-1][j - w[i] * 2] + 2 * v[i]
            ...
              选择 k 件物品 i 的价值最大，即 dp[i][j] = dp[i-1][j- w[i] * k] + k * v[i]
        因此，即可得到状态转移方程
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i]*k] + k * v[i]) (0 < k * v[i] <= j)
     */
    public int maxValue(int limit, int[] value, int[] weight) {
        int n = value.length;
        int[][] dp = new int[n][limit + 1];

        // 第一件物品
        for (int j = 0; j <= limit; j++) {
            int v = j / weight[0]; // 能够拿几个
            dp[0][j] = value[0] * v;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= limit; j++) {
                int k = 0;
                int max = 0;
                while (k * weight[i] <= j) {
                    max = Math.max(dp[i - 1][j - k * weight[i]], max);
                    k++;
                }
                dp[i][j] = Math.max(dp[i - 1][j], max);
            }
        }
        return dp[n - 1][limit];
    }

    /*
        空间优化后的 dp，总结的结论就是，0-1 背包从后往前算，完全背包从前往后算
     */
    public int maxValue2(int limit, int[] value, int[] weight) {
        int n = value.length;
        int[] dp = new int[limit + 1];

        for (int i = 0; i < n; i++) {
            for (int j = weight[i]; j <= limit; j++) {
                dp[j] = Math.max(dp[j], dp[j-weight[i]] + value[i]);
            }
        }
        return dp[limit];
    }


}
