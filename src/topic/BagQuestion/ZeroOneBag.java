package topic.BagQuestion;

/**
 * @author Rex Joush
 * @time 2022.07.31
 */

/*
    0-1 基础背包

 */
public class ZeroOneBag {

    public static void main(String[] args) {
        int[] value = new int[]{4, 2, 3};
        int[] weight = new int[]{4, 2, 3};

        int limit = 4;

        System.out.println(new ZeroOneBag().maxValue2(value, weight, limit));
    }

    /*
        0-1 背包，dp[N][limit+1]
        即，dp[i][j] 表示，第 i 件物品剩余 j 容量时最大的价值
        而转移方程，dp[i][j] 有两种情况，即 选 与 不选
        不选
            dp[i][j] = dp[i-1][j]
        选即表示，前面的 n-1件物品的最大空间就只有 dp[i-1][c-v[i]]了
        那么
            dp[i][j] = dp[i-1][c-weight[i]]] + value[i]
        因此 dp[i][j] 等于前面两者的较大值
    */
    public int maxValue(int[] value, int[] weight, int limit) {

        int n = value.length;
        int[][] dp = new int[n][limit + 1];
        // 处理第一件物品，当容量大于第一件物品的重量时，价值为第一件物品价值，否则为 0
        for (int i = weight[0]; i <= limit; i++) {
            dp[0][i] = value[0];
        }

        // 处理剩余的物品
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= limit; j++) {
                // 如果当前物品可以选
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                } else {
                    // 不能选
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][limit];
    }

    /*
        空间优化，dp[limit+1]
        实际上我们发现，当前物品只与前一个物品不取，和前一个物品的前 j-weight[i] 两个值有关
        即
                limit-weight[i]    limit
                       \             |
                                 nextLimit
        因此，我们计算的时候，将 第 i 行格子的顺序从 0-limit 改为 limit-0 即可
     */
    public int maxValue2(int[] value, int[] weight, int limit) {
        int[] dp = new int[limit + 1];

        for (int i = 0; i < value.length; i++) {
            // j 从 limit 开始，到 weight 为止
            for (int j = limit; j >= weight[i]; j--) {
                // 能装下就装，否则就不装，还是自己
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[limit];
    }
}
