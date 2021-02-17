package y2020.m10October.day04ReverseInteger;

/**
 * @author Joush
 * @time 2020.10.11
 */

/*
    整数反转
    https://leetcode-cn.com/problems/reverse-integer/

    给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    
    示例 1:
        输入: 123
        输出: 321
    示例 2:
        输入: -123
        输出: -321
    示例 3:
        输入: 120
        输出: 21
    注意:
        假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
    
 */
public class ReverseInteger {

    // 大佬解法
    public int reverse2(int x) {
        // 定义 long类型，防止溢出
        long n = 0;
        while (x != 0) {
            // 顺序取出最后一位数字
            n = n * 10 + x % 10;
            // 变为第一位
            x = x / 10;
        }

        // 如果强转后结果不同则表示溢出，返回 0，
        return (int) n == n ? (int) n : 0;
    }


    // 自己的解法
    public static int reverse(int x) {

        // 因为负数比正数多了一个，所以最小的负数取绝对值时会出问题，单独考虑
        if (x == Integer.MIN_VALUE) {
            return 0;
        }

        // 标记符号，false 为正，true 为负
        boolean flag = false;

        // 将数字变为正数
        if (x < 0) {
            flag = true;
            x = Math.abs(x);
        }

        // 将数变为字符数组
        char[] chars = Integer.toString(x).toCharArray();

        // 前后交换，进行反转
        for (int i = 0; i < (chars.length / 2); i++) {
            int j = chars.length - i - 1;

            char ch = chars[i];
            chars[i] = chars[j];
            chars[j] = ch;
        }

        // 使用 long 类型来接收转换完成的数字，防止溢出
        long result = Long.parseLong(String.valueOf(chars));

        // 如果溢出，则返回 0
        if (result > (long) Integer.MAX_VALUE) {
            return 0;
        } else {
            // 返回结果，加上符号
            return flag ? (int) result * -1 : (int) result;
        }

    }

}
