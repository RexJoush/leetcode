package y2021.m03March.day175PerfectSquares;

/**
 * @author Rex Joush
 * @time 2021.03.31
 */

/*
    完全平方数
    https://leetcode-cn.com/problems/perfect-squares/

    给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
    完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
    例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

    示例 1：
        输入：n = 12
        输出：3
        解释：12 = 4 + 4 + 4
    示例 2：
        输入：n = 13
        输出：2
        解释：13 = 4 + 9

    提示：
        1 <= n <= 104

 */
public class PerfectSquares {

    public int numSquares2(int n) {
        int[] weight = new int[100];

        for (int i = 1; i <= 100; i++) {
            weight[i - 1] = i * i;
        }

        int m = weight.length;
        int[] dp = new int[n + 1];

        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE / 2;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = weight[i - 1]; j * j < n + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - weight[i - 1]] + 1);
            }
        }

        return dp[n];
    }
    /*
        动态规划，背包问题
     */
    public int numSquares(int n) {
        int[] weight = new int[100];

        for (int i = 1; i <= 100; i++) {
            weight[i - 1] = i * i;
        }

        int m = weight.length;
        int[][] dp = new int[m + 1][n + 1];

        dp[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[0][i] = Integer.MAX_VALUE / 2;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (j >= weight[i - 1] && dp[i][j - weight[i - 1]] != Integer.MAX_VALUE / 2) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - weight[i - 1]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }


    /*
        关于背包问题的几种解法
        https://zhuanlan.zhihu.com/p/93857890
     */
    // 多重背包，恰好拿满，每个物品可拿 time[] 次
    public void benefitFullCompleteMultipleBag(int[] weight, int[] value, int[] times, int capacity) {

        // dp[i][j] 代表第 i 个物品，j 重量能够获得的最大价值
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        dp[0][0] = 0;

        for (int i = 1; i < capacity + 1; i++) {
            dp[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= weight[i - 1]) {
                    int k = 1;
                    while (j >= weight[i - 1] * k && k <= times[i - 1]) {
                        if (dp[i - 1][j - weight[i - 1] * k] != Integer.MIN_VALUE / 2) {
                            dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1] * k] + value[i - 1] * k));
                        } else {
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                        }
                        k++;
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
        printDp(dp, capacity);
    }

    // 基础多重背包，每个物品可拿 time[] 次
    public void maxValueMultipleBag(int[] weight, int[] value, int[] times, int capacity) {

        // dp[i][j] 代表第 i 个物品，j 重量能够获得的最大价值
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= weight[i - 1]) {
                    int k = 1;
                    while (j >= weight[i - 1] * k && k <= times[i - 1]) {
                        dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1] * k] + value[i - 1] * k));
                        k++;
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
        printDp(dp, capacity);
    }

    // 完全背包，可重复拿，恰好装满
    public void benefitFullCompleteBag(int[] weight, int[] value, int capacity) {
        // dp[i][j] 代表第 i 个物品，j 重量能够获得的最大价值
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        dp[0][0] = 0;
        for (int i = 1; i < capacity + 1; i++) {
            dp[0][i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= weight[i - 1] && dp[i][j - weight[i - 1]] != Integer.MIN_VALUE / 2) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        printDp(dp, capacity);
    }

    // 完全基础背包，物品可拿无限个，求最大价值
    public void maxValueCompleteBag(int[] weight, int[] value, int capacity) {
        // dp[i][j] 代表第 i 个物品，j 重量能够获得的最大价值
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= weight[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        printDp(dp, capacity);
    }

    // 基础背包，恰好装满
    public void benefitFull(int[] weight, int[] value, int capacity) {

        // dp[i][j] 代表第 i 个物品，j 重量能够获得的最大价值
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        dp[0][0] = 0;

        for (int i = 1; i < capacity + 1; i++) {
            dp[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= weight[i - 1] && dp[i - 1][j - weight[i - 1]] != Integer.MIN_VALUE / 2) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        printDp(dp, capacity);
    }

    // 基础背包，求最大价值
    public void maxValueBag(int[] weight, int[] value, int capacity) {

        // dp[i][j] 代表第 i 个物品，j 重量能够获得的最大价值
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= weight[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        printDp(dp, capacity);
    }

    public void printDp(int[][] dp, int capacity) {
        System.out.print("  ");
        for (int i = 0; i < capacity + 1; i++) {
            System.out.printf("%-12s", i + " ");
        }
        System.out.println();
        System.out.println();
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                if (j == 0) {
                    System.out.printf("%d ", i);
                }
                System.out.printf("%-12s", dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
