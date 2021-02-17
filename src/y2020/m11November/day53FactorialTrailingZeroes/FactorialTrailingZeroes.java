package y2020.m11November.day53FactorialTrailingZeroes;

import java.math.BigInteger;

/**
 * @author Joush
 * @time 2020.11.29
 */

/*
    阶乘后的零
    https://leetcode-cn.com/problems/factorial-trailing-zeroes/

    给定一个整数 n，返回 n! 结果尾数中零的数量。

    示例 1:
        输入: 3
        输出: 0
        解释: 3! = 6, 尾数中没有零。
    示例 2:
        输入: 5
        输出: 1
        解释: 5! = 120, 尾数中有 1 个零.

    说明: 你算法的时间复杂度应为 O(log n) 。


 */
public class FactorialTrailingZeroes {

    /*
        计算阶乘，然后通过不断的除以 10 来计算 0 的个数, 但此方法不能通过，255 / 500 时超出时间限制
     */
    public int trailingZeroes(int n) {

        // 使用 BigInteger 防止阶乘计算溢出
        java.math.BigInteger bg = java.math.BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            bg = bg.multiply(BigInteger.valueOf(i));
        }

        int count = 0;
        // 计算 0 的个数
        while (bg.mod(BigInteger.valueOf(10)).equals(BigInteger.ZERO)) {
            bg = bg.divide(BigInteger.valueOf(10));
            count++;
        }

        // 返回结果
        return count;

    }

    /*
        因为计算 0 的个数，只有 2 * 5 才能出现 0
        所以我们只需计算因子中出现的 2，5 的个数
        又因为 2 肯定比 5 多，所以计算5的个数即可
    */
    public int trailingZeroes2(int n) {

        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    /*
        同样的第二个思路，大佬的递归解法
     */

    public int trailingZeroes3(int n) {
        return n < 5 ? 0 : (n / 5 + trailingZeroes(n / 5));
    }
}
