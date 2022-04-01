package y2022.m03March.day528BestTimeToBuyAndSellStockIV;

/**
 * @author Rex Joush
 * @time 2022.03.19
 */

/*
    买卖股票的最佳时机 IV
    https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/

    给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1：
        输入：k = 2, prices = [2,4,1]
        输出：2
        解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
    示例 2：
        输入：k = 2, prices = [3,2,6,5,0,3]
        输出：7
        解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
             随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。

    提示：
        0 <= k <= 100
        0 <= prices.length <= 1000
        0 <= prices[i] <= 1000

 */
public class BestTimeToBuyAndSellStockIV {

    /*
        动态规划
        维护数组
            dp[i][j][0] 表示第 i 天交易完，j 次购入股票时，手上 没有 股票时的最大收益
            dp[i][j][1] 表示第 i 天交易完，j 次购入股票时，手上 持有 股票时的最大收益
        如果，第 i 天不持有股票时，
            i-1 天也不持有股票，那就状态不变
            如果 i-1 天持有股票，在 i 天把股票卖了，计算多的利润
            即 dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
        如果，第 i 天持有股票
            i-1天也持有股票，即没有买入卖出，保持持有
            i-1天不持有股票，即第 i 天买入，花钱，交易次数 + 1
            即：dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
        结果：
            4 ms, 45.43%
            41.4 MB, 15.48%
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // 定义 dp 数组
        int[][][] dp = new int[n][k+1][2];
        k = Math.min(k, n / 2); // 多余的交易次数没有意义
        // 初始化
        for (int i = 0; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        // 返回最后一天不持有股票的交易值即可
        return dp[n - 1][k][0];
    }
}
