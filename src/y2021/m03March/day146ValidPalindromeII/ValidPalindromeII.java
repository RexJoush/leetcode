package y2021.m03March.day146ValidPalindromeII;

/**
 * @author Rex Joush
 * @time 2021.03.02
 */

/*
    验证回文字符串 Ⅱ
    https://leetcode-cn.com/problems/valid-palindrome-ii/

    给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

    示例 1:
        输入: s = "aba"
        输出: true
    示例 2:
        输入: s = "abca"
        输出: true
        解释: 你可以删除c字符。
    示例 3:
        输入: s = "abc"
        输出: false

    提示:
        1 <= s.length <= 10^5
        s 由小写英文字母组成

 */
public class ValidPalindromeII {

    /*
        按顺序匹配即可
        当出现某个字符不一样时，两个子串如果某个为回文，则母串为回文
        结果：
            6 ms, 73.22%
            39 MB, 43.66%
     */
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return validPalindrome(s, left, right - 1) || validPalindrome(s, left + 1, right);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
