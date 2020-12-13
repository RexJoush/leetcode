package December20.day67CountTheNumberOfConsistentStrings;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joush
 * @time 2020.12.13
 */

/*
    统计一致字符串的数目
    https://leetcode-cn.com/problems/count-the-number-of-consistent-strings/

    给你一个由不同字符组成的字符串  allowed  和一个字符串数组  words  。如果一个字符串的每一个字符都在 allowed  中，就称这个字符串是 一致  字符串。
    请你返回  words  数组中  一致  字符串的数目。

    示例 1：
        输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
        输出：2
        解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
    示例 2：
        输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
        输出：7
        解释：所有字符串都是一致的。
    示例 3：
        输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
        输出：4
        解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。

    提示：
        1 <= words.length <= 104
        1 <= allowed.length <= 26
        1 <= words[i].length <= 10
        allowed  中的字符 互不相同  。
        words[i] 和  allowed  只包含小写英文字母。

 */
public class CountTheNumberOfConsistentStrings {
    public int countConsistentStrings(String allowed, String[] words) {
        int result = 0;

        // 定义 allowed 集合，进行排重
        Set<Character> set = new HashSet<>();

        char[] chars = allowed.toCharArray();
        // 将 allowed 的字符放入集合中
        for (int i = 0; i < allowed.length(); i++) {
            set.add(chars[i]);
        }

        for (String word : words) {
            // 挨个字符串进行判断
            char[] array = word.toCharArray();
            // 记录当前字符串的一致字符个数
            int temp = 0;
            // 判断当前字符串的所有字母在不在集合中
            for (char c : array) {
                if (!set.contains(c)) {
                    break;
                }
                else {
                    temp++;
                }
            }
            // 如果所有字母都在，result 自增
            if (temp == word.length()){
                result++;
            }
        }

        // 返回结果
        return result;
    }
}
