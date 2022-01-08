package y2021.m03March.day157UncommonWordsFromTwoSentences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2021.03.13
 */

/*
    两句话中的不常见单词
    https://leetcode-cn.com/problems/uncommon-words-from-two-sentences/

    句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
    如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
    给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。

    示例 1：
        输入：s1 = "this apple is sweet", s2 = "this apple is sour"
        输出：["sweet","sour"]
    示例 2：
        输入：s1 = "apple apple", s2 = "banana"
        输出：["banana"]

    提示：
        1 <= s1.length, s2.length <= 200
        s1 和 s2 由小写英文字母和空格组成
        s1 和 s2 都不含前导或尾随空格
        s1 和 s2 中的所有单词间均由单个空格分隔

 */
public class UncommonWordsFromTwoSentences {

    /*
        统计两个句子所有的单词个数，筛选出个数为 1 的单词，返回即可
        结果：
            2 ms, 99.81%
            38.5 MB, 45.82%
     */
    public String[] uncommonFromSentences(String s1, String s2) {

        Map<String, Integer> map = new HashMap<>();
        String[] words1 = s1.split(" ");
        String[] words2 = s2.split(" ");

        // 遍历 words1 统计单词个数
        for (String s : words1) {
            int count = map.getOrDefault(s, 0) + 1;
            map.put(s, count);
        }

        // 遍历 word2 统计单词个数
        for (String s : words2) {
            int count = map.getOrDefault(s, 0) + 1;
            map.put(s, count);
        }

        List<String> strings = new ArrayList<>();

        // 遍历 map 集合，找出所有单词个数为 1 的放入结果中
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            if (stringIntegerEntry.getValue() == 1) {
                strings.add(stringIntegerEntry.getKey());
            }
        }
        // 返回结果
        return strings.toArray(new String[]{});
    }

    /*
        简便写法，将 s1,s2 拼起来，则只用切割一次
     */
    public String[] uncommonFromSentences2(String s1, String s2) {

        Map<String, Integer> map = new HashMap<>();

        // 遍历 words1 统计单词个数
        for (String s : s1.concat(" ").concat(s2).split(" ")) {
            int count = map.getOrDefault(s, 0) + 1;
            map.put(s, count);
        }

        List<String> strings = new ArrayList<>();

        // 遍历 map 集合，找出所有单词个数为 1 的放入结果中
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            if (stringIntegerEntry.getValue() == 1) {
                strings.add(stringIntegerEntry.getKey());
            }
        }
        // 返回结果
        return strings.toArray(new String[]{});
    }
}
