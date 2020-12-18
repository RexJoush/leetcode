package December20.day72StringArraysAreEquivalent;

/**
 * @author Joush
 * @time 2020.12.18
 */

/*
    检查两个字符串数组是否相等
    https://leetcode-cn.com/problems/check-if-two-string-arrays-are-equivalent/

    给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
    数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。

    示例 1：
        输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
        输出：true
        解释：
        word1 表示的字符串为 "ab" + "c" -> "abc"
        word2 表示的字符串为 "a" + "bc" -> "abc"
        两个字符串相同，返回 true
    示例 2：
        输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
        输出：false
    示例 3：
        输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
        输出：true

    提示：
        1 <= word1.length, word2.length <= 103
        1 <= word1[i].length, word2[i].length <= 103
        1 <= sum(word1[i].length), sum(word2[i].length) <= 103
        word1[i] 和 word2[i] 由小写字母组成

 */
public class StringArraysAreEquivalent {

    /*
        使用 StringBuilder 效率比 String 要更高
        结果
            0 ms, 100.00%
            36.3 MB, 90.44;
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {

        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        for (String s : word1) {
            string1.append(s);
        }
        for (String s : word2) {
            string2.append(s);
        }

        return string1.toString().equals(string2.toString());
    }

}
