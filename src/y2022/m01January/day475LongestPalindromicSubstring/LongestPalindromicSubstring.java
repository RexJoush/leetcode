package y2022.m01January.day475LongestPalindromicSubstring;

/**
 * @author Rex Joush
 * @time 2022.01.25
 */

/*
    最长回文子串
    https://leetcode-cn.com/problems/longest-palindromic-substring/

    给你一个字符串 s，找到 s 中最长的回文子串。

    示例 1：
        输入：s = "babad"
        输出："bab"
        解释："aba" 同样是符合题意的答案。
    示例 2：
        输入：s = "cbbd"
        输出："bb"

    提示：
        1 <= s.length <= 1000
        s 仅由数字和英文字母组成

 */
public class LongestPalindromicSubstring {

    /*
        中心扩散法
        以每个字符为中心点，向两边扩散，查找最长的回文串即可。
        结果：
            8 ms, 94.14%
            41.4 MB, 50.70%
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }

        int maxLength = 0;
        int maxStart = 0;

        char[] chars = s.toCharArray();

        for (int i = 0; i < length - 1; i++) {
            // 以当前字符为奇数字符串的中心向两边扩散得到的最长回文串长度
            int oddLen = expandAroundCenter(chars, i, i);

            // 以当前字符和当前字符下一个字符为偶数字符串的中心向两边扩散得到的最长回文串长度
            int evenLen = expandAroundCenter(chars, i, i + 1);

            // 取两个中较长的为当前字符的长度
            int currentLength = Math.max(oddLen, evenLen);
            // 记录 max length，以及当前最长字符串的起始位置。
            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxStart = i - (maxLength - 1) / 2;
            }
        }
        // 返回最长回文串
        return s.substring(maxStart, maxStart + maxLength);
    }

    /**
     * 以所给位置的字符进行中心扩散，得到最长的回文串的长度
     * @param chars 原始数组
     * @param left 字符的左边界位置
     * @param right 字符的有边界位置
     * @return 回文串的长度
     */
    private int expandAroundCenter(char[] chars, int left, int right) {
        // 当 left = right 时，回文中心是一个字符，回文串长度时奇数
        // 当 right = left + 1 时，回文中心两个字符，回文串长度是偶数
        int len = chars.length;
        int i = left;
        int j = right;

        // 判断
        while (i >= 0 && j < len) {
            // 如果相同，那么向两边继续扩散，否则结束循环
            if (chars[i] == chars[j]) {
                i--;
                j++;
            } else {
                break;
            }
        }
        /*
            当前字符串的长度为 (j - i) + 1 - 2
            即不包括 j 和 i 两个位置的字符，所以须 - 2
         */
        return j - i - 1;
    }
}
