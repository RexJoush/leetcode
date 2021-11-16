package y2021.m11November.day405NumberComplement;

/**
 * @author Rex Joush
 * @time 2021.11.16
 */

/*
    数字的补数
    https://leetcode-cn.com/problems/number-complement/

    对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。
    例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
    给你一个整数 num ，输出它的补数。

    示例 1：
        输入：num = 5
        输出：2
        解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
    示例 2：
        输入：num = 1
        输出：0
        解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。

    提示：
        1 <= num < 2^31

 */
public class NumberComplement {

    /*
        分别取出每一位数字，如果当前位为 0 的话，按 2 的权加和即可
        结果：
            0 ms, 100.00%
            34.9 MB, 98.18%
     */
    public int findComplement(int num) {
        int index = 0;  // 权
        int sum = 0;    // 总和
        while (num != 0) {
            // 取出最后一位，加上当前的权重
            if ((num & 1) == 0) {
                sum += Math.pow(2, index);
            }
            // 每遍历一位，权 + 1
            index++;
            // num 右移
            num >>= 1;
        }
        return sum;
    }
}
