package y2021.m12December.day448PrimeNumberOfSetBitsInBinaryRepresentation;

/**
 * @author Rex Joush
 * @time 2021.12.29
 */

/*
    二进制表示中质数个计算置位
    https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/

    给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
    （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。
    还有，1 不是质数。）

    示例 1:
        输入: L = 6, R = 10
        输出: 4
        解释:
        6 -> 110 (2 个计算置位，2 是质数)
        7 -> 111 (3 个计算置位，3 是质数)
        9 -> 1001 (2 个计算置位，2 是质数)
        10-> 1010 (2 个计算置位，2 是质数)
    示例 2:
        输入: L = 10, R = 15
        输出: 5
        解释:
        10 -> 1010 (2 个计算置位, 2 是质数)
        11 -> 1011 (3 个计算置位, 3 是质数)
        12 -> 1100 (2 个计算置位, 2 是质数)
        13 -> 1101 (3 个计算置位, 3 是质数)
        14 -> 1110 (3 个计算置位, 3 是质数)
        15 -> 1111 (4 个计算置位, 4 不是质数)

    注意:
        L, R 是 L <= R 且在 [1, 10^6] 中的整数。
        R - L 的最大值为 10000。

 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {

    /*
        int 的长度为 32，所以最大质数到 19
        结果：
            3 ms, 91.28%
            35.5 MB, 11.06%
     */
    public int countPrimeSetBits(int left, int right) {

        int result = 0;

        for (int i = left; i <= right; i++) {
            // 如果使用官方的库会快很多
            if (isPrime(Integer.bitCount(i))) {
                result++;
            }
        }
        return result;
    }

    private boolean isPrime(int bitCount) {
        return bitCount == 2 || bitCount == 3 || bitCount == 5 || bitCount == 7 ||
                bitCount == 11 || bitCount == 13 || bitCount == 17 || bitCount == 19;
    }

    /*
        计算 n 的二进制位有多少个 1
        结果：
            42 ms, 17.87%
            25.3 MB, 30.42%
     */
    public int bitCount(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }
}
