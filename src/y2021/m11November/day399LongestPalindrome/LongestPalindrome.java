package y2021.m11November.day399LongestPalindrome;

/**
 * @author Rex Joush
 * @time 2021.11.10
 */

/*
    最长回文串
    https://leetcode-cn.com/problems/longest-palindrome/

    给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
    在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
    注意:
    假设字符串的长度不会超过 1010。

    示例 1:
        输入: "abccccdd"
        输出: 7
        解释: 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。

 */
public class LongestPalindrome {

    /*
        统计字符串每个字符的个数，偶数的就取，奇数就不取，结果即是所求最长串
        结果：
            1 ms, 100.00%
            36.5 MB, 68.03%
     */
    public int longestPalindrome(String s) {

        int result = 0;

        char[] chars = s.toCharArray();
        int[] upperCase = new int[26];
        int[] lowerCase = new int[26];

        // 统计字符个数
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                lowerCase[c - 'a']++;
            } else {
                upperCase[c - 'A']++;
            }
        }

        // 如果当前字符的个数大于二，那就折半加入到结果中
        for (int i = 0; i < 26; i++) {
            if (lowerCase[i] >= 2) {
                result += lowerCase[i] / 2;
            }
            if (upperCase[i] >= 2) {
                result += upperCase[i] / 2;
            }
        }

        // 如果最后的结果少于字符串的总长度，说明有剩余的单个字符，放在回文串的中间加一即可
        return result * 2 < s.length() ? result * 2 : result * 2 + 1;

    }

}
