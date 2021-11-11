package y2021.m10October.day388CountingBits;

/**
 * @author Rex Joush
 * @time 2021.10.30
 */

/*
    比特位计数
    https://leetcode-cn.com/problems/counting-bits/

    给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，
    返回一个长度为 n + 1 的数组 ans 作为答案。

    示例 1：
        输入：n = 2
        输出：[0,1,1]
        解释：
        0 --> 0
        1 --> 1
        2 --> 10
    示例 2：
        输入：n = 5
        输出：[0,1,1,2,1,2]
        解释：
        0 --> 0
        1 --> 1
        2 --> 10
        3 --> 11
        4 --> 100
        5 --> 101

    提示：
        0 <= n <= 10^5
 */
public class CountingBits {

    /*
        方法一：遍历计数即可
        结果：
            6 ms, 11.94%
            42.4 MB, 56.27%
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        // 遍历计算有几位 1，返回结果
        for (int i = 0; i < n + 1; i++) {
            res[i] = getBits(i);
        }
        return res;
    }

    // 计算每个数字的二进制位有多少个 1
    public int getBits(int n) {
        int count = 0;
        while (n != 0) {
            // 与 1 相与，如果值为 1，则此位为 1，count + 1，
            if ((n & 1) == 1) {
                count++;
            }
            // n 右移一位
            n = n >> 1;
        }
        return count;
    }

    /*
        方法二：Brian Kernighan 算法
        根据 Brian Kernighan 算法思想
            x & (x - 1) 可以将该数的最后一位 1 置为 0，因此对次数重复操作，则可以得到 1 的位数
            此算法的时间复杂度不会超过 O(log(n)), 所以整个过程的时间复杂度为 0(nlog(n))
        结果：
            2 ms, 43.00%
            42.4 MB, 56.27%
     */
    public int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }

    /*
        方法三：动态规划-最高有效位
        已知 2 的整数次幂的二进制均只有 1 位为 1，那么可得，介于两个整数次幂之间的数，均可有之前的值 +1 得到
        举例 8(1000) 有 1 位，10(1010) 则可表示为 1 + bit((10-8)(1010-1000) = 0010) 得到，即 bit(2)
        所以可得转移方程
        bit(i) = bit(i - x) + 1得到，x 为小于 i 的最大2的整数次幂的值

        结果：
            1 ms, 99.88%
            42.2 MB, 79.71%
     */
    public int[] countBits2(int n) {
        int[] result = new int[n + 1];

        int x = 0;
        for (int i = 1; i <= n; i++) {
            // 当前值为 2 的整数次幂
            if ((i & (i - 1)) == 0) {
                x = i;
            }
            result[i] = result[i - x] + 1;
        }
        return result;
    }
}
