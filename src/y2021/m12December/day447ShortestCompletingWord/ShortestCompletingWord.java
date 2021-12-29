package y2021.m12December.day447ShortestCompletingWord;

/**
 * @author Rex Joush
 * @time 2021.12.28
 */

/*
    最短补全词
    https://leetcode-cn.com/problems/shortest-completing-word/

    给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词。
    补全词 是一个包含 licensePlate 中所有字母的单词。
    在匹配 licensePlate 中的字母时：
    忽略 licensePlate 中的 数字和空格。区分大小写。
    如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
    例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。
    可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。
    请你找出并返回 words 中的 最短补全词。
    题目数据保证一定存在一个最短补全词。
    当有多个单词都符合最短补全词的匹配条件时取 words 中 第一个 出现的那个。

    示例 1：
        输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
        输出："steps"
        解释：最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
        "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
        "steps" 包含 "t"、"p" 和两个 "s"。
        "stripe" 缺一个 "s"。
        "stepple" 缺一个 "s"。
        因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。
    示例 2：
        输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
        输出："pest"
        解释：licensePlate 只包含字母 "s" 。所有的单词都包含字母 "s" ，其中 "pest"、"stew"、和 "show" 三者最短。答案是 "pest" ，因为它是三个单词中在 words 里最靠前的那个。
    示例 3：
        输入：licensePlate = "Ah71752", words = ["suggest","letter","of","husband","easy","education","drug","prevent","writer","old"]
        输出："husband"
    示例 4：
        输入：licensePlate = "OgEu755", words = ["enough","these","play","wide","wonder","box","arrive","money","tax","thus"]
        输出："enough"
    示例 5：
        输入：licensePlate = "iMSlpe4", words = ["claim","consumer","student","camera","public","never","wonder","simple","thought","use"]
        输出："simple"
    
    提示：
        1 <= licensePlate.length <= 7
        licensePlate 由数字、大小写字母或空格 ' ' 组成
        1 <= words.length <= 1000
        1 <= words[i].length <= 15
        words[i] 由小写英文字母组成

 */
public class ShortestCompletingWord {

    /*
        暴力遍历即可
        结果：
            4 ms 60.40%
            39.3 MB, 5.11%
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        // 统计 license 中出现的字符
        int[] cnt = new int[26];
        for (int i = 0; i < licensePlate.length(); ++i) {
            char ch = licensePlate.charAt(i);
            if (Character.isLetter(ch)) {
                ++cnt[Character.toLowerCase(ch) - 'a'];
            }
        }
        int idx = -1;
        // 遍历 word 中 的每个单词，记录一下符合条的单词
        for (int i = 0; i < words.length; ++i) {
            int[] c = new int[26];
            for (int j = 0; j < words[i].length(); ++j) {
                char ch = words[i].charAt(j);
                ++c[ch - 'a'];
            }
            boolean ok = true;
            for (int j = 0; j < 26; ++j) {
                if (c[j] < cnt[j]) {
                    ok = false;
                    break;
                }
            }
            // 记录第一个
            if (ok && (idx < 0 || words[i].length() < words[idx].length())) {
                idx = i;
            }
        }
        // 返回即可
        return words[idx];
    }
}
