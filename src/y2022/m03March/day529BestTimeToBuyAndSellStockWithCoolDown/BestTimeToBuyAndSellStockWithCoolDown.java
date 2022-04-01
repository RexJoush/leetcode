package y2022.m03March.day529BestTimeToBuyAndSellStockWithCoolDown;

/**
 * @author Rex Joush
 * @time 2022.03.20
 */

/*
    最佳买卖股票时机含冷冻期
    https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

    给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
    设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
    卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1:
        输入: prices = [1,2,3,0,2]
        输出: 3
        解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
    示例 2:
        输入: prices = [1]
        输出: 0

    提示：
        1 <= prices.length <= 5000
        0 <= prices[i] <= 1000

 */
public class BestTimeToBuyAndSellStockWithCoolDown {

    /*
        动态规划
        考虑，每天有以下几种情况
            1.持有一只股票
            2.卖出一只股票
            3.冷静期
        定义 dp[i][0] 为第 i 天没有股票的最大利润
            dp[i][1] 为第 i 天持有股票的最大利润
            dp[i][2] 为第 i 天为冷静期的最大利润
        第 i 天没有股票的，未处于冷静期，即为，前一天没有 dp[i-1][0]，或者前一天处于冷静期
            即 dp[i][0] = max(dp[i-1][0], dp[i-1][2]);
        第 i 天持有股票的最大收益，即为，前一天持有 dp[i-1][1]，或者今天买入的 dp[i-1][0] - prices[i]
            即 dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i]);
        第 i 天没有股票，处于冷静期，前一天卖出了股票，即前一天持有股票，卖出了 dp[i-1][1] + price[i]
            即 dp[i][2] = dp[i-1][1] + prices[i];
        结果：
            1 ms, 77.90%
            40 MB, 5.23%
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 0; // 第一天未持有的收益为0
        dp[0][1] = -prices[0]; // 第一天买入的收益
        dp[0][2] = 0; // 第一天不可能为冷静期
        // 计算过程
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            dp[i][2] = dp[i-1][1] + prices[i];
        }
        // 返回最后一天处于冷静期或者未持有股票的最大值
        return Math.max(dp[n-1][0], dp[n-1][2]);
    }
}
