package y2021.m02February.day132UglyNumber;

/**
 * @author Rex Joush
 * @time 2021.02.16
 */

/*
    丑数
    https://leetcode-cn.com/problems/ugly-number/

    编写一个程序判断给定的数是否为丑数。
    丑数就是只包含质因数 2, 3, 5 的正整数。

    示例 1:
        输入: 6
        输出: true
        解释: 6 = 2 × 3
    示例 2:
        输入: 8
        输出: true
        解释: 8 = 2 × 2 × 2
    示例 3:
        输入: 14
        输出: false
        解释: 14 不是丑数，因为它包含了另外一个质因数 7。

    说明：
        1 是丑数。
        输入不会超过 32 位有符号整数的范围: [−2^31,  2^31 − 1]

 */
public class UglyNumber {

    /*
        不断除以 2，3，5 即可，某次除不尽时，即不是丑数，否则是丑数
        结果：
            1 ms, 100.00
            35.5 MB, 37.59%
     */
    public boolean isUgly(int n) {
        // 小于等于 0 直接返回不是丑数
        if (n <= 0) {
            return false;
        }
        // 等于 1 表示除尽了，即是丑数
        while (n != 1) {
            // 能除尽 2 就除以 2
            if (n % 2 == 0) {
                n /= 2;
            }
            // 能除尽 3 就除以 3
            else if (n % 3 == 0){
                n /= 3;
            }
            // 能除尽 5 就除以 5
            else if (n % 5 == 0) {
                n /= 5;
            }
            // 均除不尽且不为 1，返回 false
            else {
                return false;
            }
        }
        return true;
    }
}
