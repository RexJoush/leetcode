package y2022.m02February.day509LongestCommonSubsequence;

/**
 * @author Rex Joush
 * @time 2022.02.28
 */

/*
    最长公共子序列
    https://leetcode-cn.com/problems/longest-common-subsequence/

    给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

    示例 1：
        输入：text1 = "abcde", text2 = "ace"
        输出：3
        解释：最长公共子序列是 "ace" ，它的长度为 3 。
    示例 2：
        输入：text1 = "abc", text2 = "abc"
        输出：3
        解释：最长公共子序列是 "abc" ，它的长度为 3 。
    示例 3：
        输入：text1 = "abc", text2 = "def"
        输出：0
        解释：两个字符串没有公共子序列，返回 0 。

    提示：
        1 <= text1.length, text2.length <= 1000
        text1 和 text2 仅由小写英文字符组成。

 */
public class LongestCommonSubsequence {

    /*
        二维动态规划
        dp[i][j] 表示字符串 text1 的 [0, i] 区间和字符串的 text2 的 [0, j] 区间的最长公共子序列长度
        当 text1[i] == text2[j] 时，也就是说，字符串的最后一位相等
            那么问题就转化成了字符串 text1 的 [0, i-1] 区间和
                            字符串 text2 的 [0, j-1] 区间的，最长公共子序列长度+1
            即 dp[i][j] = dp[i-1][j-1] + 1
        当 text1[i] != text2[j] 时，两个字符串最后一位不相等，那么最长公共子序列就无法延长
            而是取两边的较长者
            即 dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
        因此，初始化情况下即，dp[i][0] = 0,  0 <= i <= n
                            dp[0][j] = 0,  0 <= j <= m
        结果：
            10 ms, 65.49%
            45.2 MB, 17.98%
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
