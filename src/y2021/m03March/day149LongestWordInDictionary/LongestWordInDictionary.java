package y2021.m03March.day149LongestWordInDictionary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2021.03.05
 */

/*
    词典中最长的单词
    https://leetcode-cn.com/problems/longest-word-in-dictionary/

    给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，
    该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，
    则返回答案中字典序最小的单词。若无答案，则返回空字符串。
    
    示例 1：
        输入：words = ["w","wo","wor","worl", "world"]
        输出："world"
        解释：单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
    示例 2：
        输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
        输出："apple"
        解释："apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。
    
    提示：
        所有输入的字符串都只包含小写字母。
        words数组长度范围为[1,1000]。
        words[i]的长度范围为[1,30]。
 */
public class LongestWordInDictionary {

    /*
        哈希表
        结果：
            17 ms, 52.85%
            38.6 MB, 60.73%
     */
    public String longestWord(String[] words) {

        // 排序
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String maxString = "";
        set.add("");
        for (String word : words) {
            // 如果当前字符串减一个字符在集合中，那么就看情况替换最大长度的字符串，并放入集合中
            if (set.contains(word.substring(0, word.length() - 1))) {
                if (maxString.length() < word.length()) {
                    maxString = word;
                }
                set.add(word);
            }
        }
        // 返回即可
        return maxString;
    }
}
