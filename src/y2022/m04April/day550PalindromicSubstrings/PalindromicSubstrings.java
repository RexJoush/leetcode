package y2022.m04April.day550PalindromicSubstrings;

/**
 * @author Rex Joush
 * @time 2022.04.10
 */

/*
    回文子串
    https://leetcode-cn.com/problems/palindromic-substrings/

    给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
    回文字符串 是正着读和倒过来读一样的字符串。
    子字符串 是字符串中的由连续字符组成的一个序列。
    具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

    示例 1：
        输入：s = "abc"
        输出：3
        解释：三个回文子串: "a", "b", "c"
    示例 2：
        输入：s = "aaa"
        输出：6
        解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"

    提示：
        1 <= s.length <= 1000
        s 由小写英文字母组成

 */
public class PalindromicSubstrings {

    /*
        动态规划
            定义 dp[i][j] 表示 s[i...j] 能否构成回文子串
            那么 dp[i][j] = dp[i+1][j-1] && (s[i] == s[j])
        结果：
            5 ms, 55.70%
            41.8 MB, 9.38%
     */
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // 当 i == j 或 (s[i] == s[j] && (j == i + 1 || dp[i + 1][j - 1])
                if (chars[i] == chars[j] && (j <= i + 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    result++;
                }
            }
        }
        return result;
    }
}
