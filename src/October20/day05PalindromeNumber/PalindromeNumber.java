package October20.day05PalindromeNumber;

/**
 * @author Joush
 * @time 2020.10.12
 */

/*
    回文数
    https://leetcode-cn.com/problems/palindrome-number/

    判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    示例 1:
        输入: 121
        输出: true
    示例 2:
        输入: -121
        输出: false
        解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    示例 3:
        输入: 10
        输出: false
        解释: 从右向左读, 为 01 。因此它不是一个回文数。

 */
public class PalindromeNumber {

    public boolean isPalindrome(int x) {

        // 负数直接返回false
        if (x < 0) {
            return false;
        }

        // 将x变为字符串
        char[] chars = String.valueOf(x).toCharArray();

        int i = 0;
        int j = chars.length - 1;

        // 判断字符
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

}
