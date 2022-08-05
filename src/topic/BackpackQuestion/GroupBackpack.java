package topic.BackpackQuestion;

/**
 * @author Rex Joush
 * @time 2022.08.05
 */

/*
    分组背包
    给定 N 个物品和容量为 limit 的背包
    第 i 个物品组共有 s[i] 个物品，其中 第 i 组 第 j 件物品的成本为 weight[i][j], 价值为 value[i][j]
    每组有若干个物品，同一组内的物品最多选一个，求最大收益
 */
public class GroupBackpack {

    public static void main(String[] args) {
        int limit = 9;
        int[][] value = new int[][]{{2, 4, -1}, {1, 3, 6}};
        int[][] weight = new int[][]{{1, 2, -1}, {1, 2, 3}};
        int[] s = new int[]{2, 3};
        System.out.println(new GroupBackpack().getMax2(limit, value, weight, s));
    }

    /*
        空间优化
     */
    public int getMax2(int limit, int[][] value, int[][] weight, int[] s) {
        int[] dp = new int[limit + 1];
        for (int i = 1; i <= value.length; i++) {
            // 0-1 背包从后往前算
            for (int j = limit; j >= 0; j--) {
                for (int k = 0; k < s[i - 1]; k++) {
                    if (j >= weight[i - 1][k]) {
                        dp[j] = Math.max(dp[j], dp[j - weight[i - 1][k]] + value[i - 1][k]);
                    }
                }
            }
        }
        return dp[limit];
    }

    /*
        其实和 0-1 背包一样，定义 dp[i][j] 为 前 i 组容量为 j 时的最大收益
        第 i 组背包有以下几种情况
            不拿，       dp[i][j] = dp[i-1][j]
            拿第一件物品  dp[i][j] = dp[i-1][j-weight[i][1]] + value[i][1]
            拿第二件物品  dp[i][j] = dp[i-1][j-weight[i][2]] + value[i][2]
            ...
            拿第 k 件物品 dp[i][j] = dp[i-1][j-weight[i][k]] + value[i][k]
        因此 dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i][k]] + value[i][k]) 0 < k < value[0].length
     */
    public int getMax(int limit, int[][] value, int[][] weight, int[] s) {
        int n = value.length;
        int[][] dp = new int[n + 1][limit + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= limit; j++) {
                dp[i][j] = dp[i - 1][j];
                // 遍历第 i 组的所有物品，找到最大值
                for (int k = 0; k < s[i - 1]; k++) {
                    if (j >= weight[i - 1][k]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i - 1][k]] + value[i - 1][k]);
                    }
                }
            }
        }
        return dp[n][limit];
    }
}
