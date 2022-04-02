package y2022.m03March.day540RegularExpressionMatching;

/**
 * @author Rex Joush
 * @time 2022.03.31
 */

/*
    正则表达式匹配
    https://leetcode-cn.com/problems/regular-expression-matching/

    给你一个字符串s和一个字符规律 p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
    '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素
    所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。

    示例 1：
        输入：s = "aa", p = "a"
        输出：false
        解释："a" 无法匹配 "aa" 整个字符串。
    示例 2:
        输入：s = "aa", p = "a*"
        输出：true
        解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
    示例 3：
        输入：s = "ab", p = ".*"
        输出：true
        解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

    提示：
        1 <= s.length<= 20
        1 <= p.length<= 30
        s只包含从a-z的小写字母。
        p只包含从a-z的小写字母，以及字符.和*。
        保证每次出现字符 * 时，前面都匹配到有效的字符

 */
public class RegularExpressionMatching {

    /*
        模拟正则匹配的过程
        动态规划：
            dp[i][j] 定义为，s 的前 i 个字符与 p 的前 j 个字符能否匹配
            考虑以下情况
            情况一: s = eeebb[c]
                   p = eeebbc[a*]
                因为，a* 的存在，a 可以为多个或 0 个，那么 dp[0][j] = dp[0][j-2]，即，将 a* 两个字符直接删除
            情况二： s = eeebb[c]
                    p = eeebb[.]      s[i] == p[i] || p[i] == '.'
                即，当前位置的字符相等或者 p 为通配符 . 那么 dp[i][j] = dp[i-1][j-1]
            情况三： s = eebbc[a]
                    p = eebb[a]*     dp[i][j] = dp[i][j-2] || dp[i][j-1] || dp[i-1][j]
                即，当前情况共有三种
                    1.dp[i][j] = dp[i][j-2], p 向前看两个字符，相当于 * 重复了 0 次
                    2.dp[i][j] = dp[i][j-1], p 向前看一个字符，相当于 * 重复了 1 次
                    3.dp[i][j] = dp[i-1][j], s 向前看一个字符，相当于 * 重复了 n 次
            情况四： s = eebb[a]
                    p = eebb[c*]      dp[i][j] = dp[i][j-1]
                即，s[i] != p[j-2] 的字符，那么 * 就只能匹配 0 次
        结果：
            1 ms, 100.00%
            40 MB, 32.53%
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        if (m == 0 || n == 0) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] pattern = p.toCharArray();
        // 初始化 dp
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 初始化 dp 的第一列，此时 s 为 0
        for (int j = 1; j <= n; j++) {
            // 情况 1，如果 p 的第 j-1 的位置时 *，则 j 的状态等于 j-2 的状态
            if (pattern[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 情况2，即 s 和 j 的当前字符匹配，那么 dp[i][j] = dp[i-1][j-1]
                if (sChars[i - 1] == pattern[j - 1] || pattern[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    // 当前字符不匹配，则分两种情况，即情况3和 4

                    // 情况3 此时表示，s 的当前字符和 p 的前一个字符匹配，即 s= xxa p = xxa*
                    if (sChars[i - 1] == pattern[j - 2] || pattern[j - 2] == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];
                    } else {
                        // 情况 4，当前位置和 p的前两个位置不匹配，相当于 * 重复了 0 次
                        // 即 s = xxx[a] p = xxx[a]b*
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
