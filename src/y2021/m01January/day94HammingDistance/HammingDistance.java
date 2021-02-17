package y2021.m01January.day94HammingDistance;

/**
 * @author Joush
 * @time 2021.01.09
 */

/*
    汉明距离
    https://leetcode-cn.com/problems/hamming-distance/

    两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
    给出两个整数 x 和 y，计算它们之间的汉明距离。
    注意：
    0 ≤ x, y < 2^31.

    示例:
        输入: x = 1, y = 4
        输出: 2
        解释:
        1   (0 0 0 1)
        4   (0 1 0 0)
               ↑   ↑
    上面的箭头指出了对应二进制位不同的位置。

 */
public class HammingDistance {

    /*
        布赖恩·克尼根算法
        和移位类似，但移位是逐位比较最右侧位置是否为1，寻找一种更快方法
        可以跳过两个 1 之间的 0
            如 10001000，直接找到两个 1 即可，不用进行 8 次比较，而进行 2 次
        这就是布赖恩·克尼根算法的基本思想。该算法使用特定的位运算移除等于 1 的最优比特位
        当我们在 number 和 number - 1 上做 与 运算时，原数字的最右边等于 1 的比特会被移除
            如           x = 1000 1000
                     x - 1 = 1000 0111
               x & (x - 1) = 1000 0000
     */
    public int hammingDistance4(int x, int y) {
        int xor = x ^ y;
        int result = 0;
        while (xor != 0){
            result++;
            // 将最右边的 1 去除，所以迭代两次即可
            xor = xor & (xor - 1);
        }
        return result;
    }
    /*
        移位，两数异或完后移位计数
        结果：
            0 ms, 100.00%
            34.9 MB, 98.24%
     */
    public int hammingDistance3(int x, int y) {
        int xor = x ^ y;
        int result = 0;
        while (xor != 0){
            if (xor % 2 == 1){
                result++;
            }
            // 右移一位
            xor = xor >> 1;
        }
        return result;
    }

    /*
        两数异或操作，1 的个数即为汉明距离
        结果：
            1 ms,10.55%
            34.9 MB, 97.78%
     */
    public int hammingDistance2(int x, int y) {
        // bitCount 函数计算数字中包含 1 的位数
        return Integer.bitCount(x ^ y);
    }
    public int hammingDistance(int x, int y) {
        int result = 0;
        char[] chars = Integer.toBinaryString(x ^ y).toCharArray();

        for (char c : chars) {
            if (c == '1') {
                result++;
            }
        }

        return result;

    }
}
