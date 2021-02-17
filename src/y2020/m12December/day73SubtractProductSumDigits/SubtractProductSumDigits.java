package y2020.m12December.day73SubtractProductSumDigits;

/**
 * @author Joush
 * @time 2020.12.19
 */

/*
    整数的各位积和之差
    https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/

    给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。

    示例 1：
        输入：n = 234
        输出：15
        解释：
        各位数之积 = 2 * 3 * 4 = 24
        各位数之和 = 2 + 3 + 4 = 9
        结果 = 24 - 9 = 15
    示例 2：
        输入：n = 4421
        输出：21
        解释：
        各位数之积 = 4 * 4 * 2 * 1 = 32
        各位数之和 = 4 + 4 + 2 + 1 = 11
        结果 = 32 - 11 = 21

    提示：
        1 <= n <= 10^5

 */
public class SubtractProductSumDigits {

    /*
        从最后一位开始取余，在除 10 即可拿到所有位数字，计算和与积即可
        结果
            0 ms, 100.00%
            35 MB, 93.25%
     */
    public int subtractProductAndSum(int n) {

        int sum = 0;
        int multi = 1;

        while (n != 0){
            sum += n % 10;
            multi *= n % 10;
            n /= 10;
        }

        return multi - sum;
    }
}
