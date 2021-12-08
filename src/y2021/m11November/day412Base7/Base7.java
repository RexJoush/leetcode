package y2021.m11November.day412Base7;

/**
 * @author Rex Joush
 * @time 2021.11.23
 */

/*
    七进制数
    https://leetcode-cn.com/problems/base-7/

    给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。

    示例 1:
        输入: num = 100
        输出: "202"
    示例 2:
        输入: num = -7
        输出: "-10"

    提示：
    -10^7 <= num <= 10^7

 */
public class Base7 {

    /*
        调用即可
        结果：
            0 ms, 100.00%
            38.9 MB, 53.67%
     */
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }

}
