package y2022.m03March.day527BestTimeToBuyAndSellStockIII;

/**
 * @author Rex Joush
 * @time 2022.03.18
 */

/*
    买卖股票的最佳时机 III
    https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/

    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1:
        输入：prices = [3,3,5,0,0,3,1,4]
        输出：6
        解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
             随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
    示例 2：
        输入：prices = [1,2,3,4,5]
        输出：4
        解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
             注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
             因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
    示例 3：
        输入：prices = [7,6,4,3,1]
        输出：0
        解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
    示例 4：
        输入：prices = [1]
        输出：0

    提示：
        1 <= prices.length <= 10^5
        0 <= prices[i] <= 10^5

 */
public class BestTimeToBuyAndSellStockIII {

    /*
        动态规划，最多可以完成两次交易，因此在任意一天结束时，会有以下五种状态
            1.未进行过任何操作
            2.只进行过一次买操作
            3.进行了一次买和一次卖
            4.进行看第二次买
            5.进行了第二次卖
            第一种状态的收益显然为 0 我们不考虑
            剩余 4 种状态我们记为 buy1,sell1,buy2,sell2
                buy1[i] = Max(buy1[i-1], -prices[i])   // 即，买入股票，收入为当前股价的负值
                sell1[i] = Max(sell1[i-1], buy1 + prices[i]) // 卖出股票，等于当前当前值 - 购买值

                buy2[i] = Max(buy2, sell1-prices[i]) // 需要将第一次的收益加上
                sell2[i] = Max(sell2[i-1], buy2 + prices[i])
            因此最终的sell2 即为最大收益
            初始第 0 天时
                buy1 = -prices[0];
                buy2 = -prices[0];
                sell1 = 0;
                sell2 = 0;
     */
    public int maxProfit(int[] prices) {
        int buy1 = -prices[0];
        int buy2 = -prices[0];
        int sell1 = 0;
        int sell2 = 0;

        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, prices[i] + buy1);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, prices[i] + buy2);
        }
        return sell2;
    }
}
