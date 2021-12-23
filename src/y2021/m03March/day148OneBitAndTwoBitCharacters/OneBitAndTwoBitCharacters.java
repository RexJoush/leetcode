package y2021.m03March.day148OneBitAndTwoBitCharacters;

/**
 * @author Rex Joush
 * @time 2021.03.04
 */

/*
    1比特与2比特字符
    https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/

    有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
    现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。

    示例 1:
        输入: bits = [1, 0, 0]
        输出: True
        解释: 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
    示例 2:
        输入: bits = [1, 1, 1, 0]
        输出: False
        解释: 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。

    注意:
        1 <= len(bits) <= 1000.
        bits[i] 总是 0 或 1.

 */
public class OneBitAndTwoBitCharacters {

    /*
        如果当前位为 1，那么下一位不管是什么则一定会和 1 组成 2 比特字符
        如果当前为 0，那么 0 为单独一个 1 比特字符
        如果来到最后一位，则说明符合条件
        结果：
            0 ms, 100.00%
            37.9 MB, 15.33%
     */
    public boolean isOneBitCharacter(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            if (i == bits.length - 1) {
                return true;
            }
            if (bits[i] == 1) {
                i++;
            }
        }

        return false;
    }

}
