package y2022.m03March.day535WordBreak;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2022.03.26
 */

/*
    单词拆分
    https://leetcode-cn.com/problems/word-break/

    给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
    注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

    示例 1：
        输入: s = "leetcode", wordDict = ["leet", "code"]
        输出: true
        解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
    示例 2：
        输入: s = "applepenapple", wordDict = ["apple", "pen"]
        输出: true
        解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
             注意，你可以重复使用字典中的单词。
    示例 3：
        输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        输出: false

    提示：
        1 <= s.length <= 300
        1 <= wordDict.length <= 1000
        1 <= wordDict[i].length <= 20
        s 和 wordDict[i] 仅有小写英文字母组成
        wordDict 中的所有字符串 互不相同

 */
public class WordBreak {

    /*
        动态规划
            定义 dp[i] 表示字符串前 i 和字符组成的字符串能否被空格拆分成若干个字典中的单词
            从前往后考虑转移方程，每次转移我们需要枚举包含位置 i-1 的最后一个单词，看它是否出现在字典中
            以及除去这部分字符串是否合法
            即，枚举 s[0...i-1] 中的分割点 j，看 s[0...j] 组成的字符串是否合法和 s[j...i-1] 是否合法
            如果两个串均合法，那么按照定义，两个串也合法。
            即，dp[i] = dp[j] && check(s[j...i-1]);
            其中，check(s) 表示 s 是否出现在字典中
        结果：
            6 ms, 74.40%
            41.6 MB, 30.63%
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            // 从 j 到 i 开始枚举每一个分割点
            for (int j = 0; j < i; j++) {
                // 当前满足，即置为 true 并结束
                if (dp[j] && set.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
