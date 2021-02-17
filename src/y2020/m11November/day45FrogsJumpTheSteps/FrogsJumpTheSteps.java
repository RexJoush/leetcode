package y2020.m11November.day45FrogsJumpTheSteps;

/**
 * @author Joush
 * @time 2020.11.21
 */

/*
    青蛙跳台阶问题（剑指 Offer 10- II）
    https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/

    一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

    示例 1：
        输入：n = 2
        输出：2
    示例 2：
        输入：n = 7
        输出：21
    示例 3：
        输入：n = 0
        输出：1
    提示：
        0 <= n <= 100

 */
public class FrogsJumpTheSteps {

    /*
        斐波那契数列的非递归写法
     */
    public int numWays2(int n) {

        if (n == 0 || n == 1){
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n;i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        return dp[n];

    }

    /*
        斐波那契数列，递归即可解，但在 n = 44 时即超出时间限制
     */
    public int numWays(int n) {
        if (n == 0){
            return 1;
        }
        if (n == 1){
            return 1;
        }
        return numWays(n - 1) + numWays(n - 2);
    }
}
