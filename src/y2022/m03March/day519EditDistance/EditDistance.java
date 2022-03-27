package y2022.m03March.day519EditDistance;

/**
 * @author Rex Joush
 * @time 2022.03.10
 */

/*
    编辑距离
    https://leetcode-cn.com/problems/edit-distance/

    给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。
    你可以对一个单词进行如下三种操作：
        插入一个字符
        删除一个字符
        替换一个字符

    示例 1：
        输入：word1 = "horse", word2 = "ros"
        输出：3
        解释：
        horse -> rorse (将 'h' 替换为 'r')
        rorse -> rose (删除 'r')
        rose -> ros (删除 'e')
    示例 2：
        输入：word1 = "intention", word2 = "execution"
        输出：5
        解释：
        intention -> inention (删除 't')
        inention -> enention (将 'i' 替换为 'e')
        enention -> exention (将 'n' 替换为 'x')
        exention -> exection (将 'n' 替换为 'c')
        exection -> execution (插入 'u')

    提示：
        0 <= word1.length, word2.length <= 500
        word1 和 word2 由小写英文字母组成

 */
public class EditDistance {

    /*
        动态规划
        考虑，两个单词
            word1 = "abcc" 和 word2 = "cds"
        从后向前考虑，如果想要 word1 's' 变为 word2 'c'
        那么有以下三种操作
            1.执行插入，将 word2 插入一个 'c' 变为 "cdsc"，继续比较 word[0...i] 和 word[0...j-1]
            2.执行删除，将 word1 的 'c' 删除，那么比较 word1[0...i-1] 和 word2[0...j]
            3.执行替换，将 word1 的 'c' 变为 's'，继续比较 word1[0...i-1] 和 word2[0...j-1]
        选择上述三个选项中最小的那个 + 1即可
        即，定义，dp[i][j] 表示字符串 word1[i] 和字符串 word2[j] 之前的最小操作次数
            if (word1[i] == word2[j]) {
                dp[i][j] = dp[i-1][j-1];
            } else {
                dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])
            }
        结果：
            4 ms, 91.76%
            41.6  MB, 18.23%
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 有一个空串
        if (m * n == 0) {
            return m + n;
        }

        int[][] dp = new int[m+1][n+1];

        // 边界初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // 边界初始化
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[m][n];
    }
}
