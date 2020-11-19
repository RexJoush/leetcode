package November20.day42ReverseBitsLcci;

import java.util.Arrays;

/**
 * @author Joush
 * @time 2020.11.18
 */

/*
    翻转数位
    https://leetcode-cn.com/problems/reverse-bits-lcci/

    给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。

    示例 1：
        输入: num = 1775(11011101111)2
        输出: 8
    示例 2：
        输入: num = 7(0111)2
        输出: 4

 */
public class ReverseBitsLcci {

    /*
        计算所有的 1 子段的长度，求出最大的相邻子段和 + 1，返回即可
     */
    public int reverseBits(int num) {

        // -1的二进制 11111111111111111111111111111111，直接返回 32
        if (num == -1){
            return 32;
        }

        // 0 的二进制，返回 0
        if (num == 0){
            return 1;
        }

        int result = 0;

        char[] chars = Integer.toBinaryString(num).toCharArray();

        int[] lengths = new int[chars.length];
        int index = 0;
        int length = 0;
        // 计算 1 子段的个数并存储
        for (char ch : chars){
            if (ch == '0'){
                lengths[index++] = length;
                length = 0;
            }
            else {
                length++;
            }
        }
        // 将最后一个字段存入
        if (length != 0){
            lengths[index++] = length;
        }

        // 防止仅有一个子段
        if (index == 1){
            return lengths[0] + 1;
        }

        // 计算最大子段
        for (int i = 0; i < index; i++){
            result = Math.max(lengths[i] + lengths[i + 1] + 1, result);
        }

        // 返回结果
        return result;
    }

}
