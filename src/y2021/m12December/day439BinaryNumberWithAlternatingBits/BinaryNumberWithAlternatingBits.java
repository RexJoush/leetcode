package y2021.m12December.day439BinaryNumberWithAlternatingBits;

/**
 * @author Rex Joush
 * @time 2021.12.20
 */

/*
    交替位二进制数
    https://leetcode-cn.com/problems/binary-number-with-alternating-bits/

    给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，
    就是二进制表示中相邻两位的数字永不相同。

    示例 1：
        输入：n = 5
        输出：true
        解释：5 的二进制表示是：101
    示例 2：
        输入：n = 7
        输出：false
        解释：7 的二进制表示是：111.
    示例 3：
        输入：n = 11
        输出：false
        解释：11 的二进制表示是：1011.
    示例 4：
        输入：n = 10
        输出：true
        解释：10 的二进制表示是：1010.
    示例 5：
        输入：n = 3
        输出：false

    提示：
        1 <= n <= 2^31 - 1

 */
public class BinaryNumberWithAlternatingBits {

    /*
        位运算，异或即可
        结果：
            0 ms, 100.00%
            35 MB, 85.09%
     */
    public boolean hasAlternatingBits(int n) {
        while (n != 0) {
            // 取得最后一位
            int i = n & 1;
            // 右移一位
            n >>= 1;
            // 取得倒数第二位
            int j = n & 1;
            // 异或结果不对则返回 false
            if ((i ^ j) != 1) {
                return false;
            }
        }
        // 结束则返回 true
        return true;
    }

}
