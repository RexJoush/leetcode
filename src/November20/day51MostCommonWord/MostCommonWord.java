package November20.day51MostCommonWord;

import java.util.*;

/**
 * @author Joush
 * @time 2020.11.27
 */

/*
    最常见的单词
    https://leetcode-cn.com/problems/most-common-word/

    给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
    题目保证至少有一个词不在禁用列表中，而且答案唯一。
    禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。

    示例：
        输入:
            paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
            banned = ["hit"]
        输出: "ball"
        解释:
            "hit" 出现了3次，但它是一个禁用的单词。
            "ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
            注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
            "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。

    提示：
        1 <= 段落长度 <= 1000
        0 <= 禁用单词个数 <= 100
        1 <= 禁用单词长度 <= 10
        答案是唯一的, 且都是小写字母 (即使在 paragraph 里是大写的，即使是一些特定的名词，答案都是小写的。)
        paragraph 只包含字母、空格和下列标点符号!?',;.
        不存在没有连字符或者带有连字符的单词。
        单词里只包含字母，不会出现省略号或者其他标点符号。


 */
public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {

        // 使用正则表达式进行分割
        String[] words = paragraph.split("[ ,.!;'?]+");

        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        // 统计每个单词出现的次数
        for (String word : words){
            if (map.containsKey(word.toLowerCase())){
                map.put(word.toLowerCase(), map.get(word.toLowerCase()) + 1);
            }
            else {
                map.put(word.toLowerCase(), 1);
            }
        }

        // 禁用列表不为空
        if (banned != null){
            // 将禁用数组转换成 set 集合，方便查找
            set.addAll(Arrays.asList(banned));

            /*
                遍历集合，如果当前单词在禁用列表里出现，就将之移除，此为正常写法，下面为 lambda 表达式写法，吊的一！

                Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, Integer> next = iterator.next();
                    if (set.contains(next.getKey())){
                        iterator.remove();
                    }
                }
             */

            // lambda 表达式写法
            map.entrySet().removeIf(next -> set.contains(next.getKey()));

        }

        // 找出 map 中次数出现最多的，记录结果，返回
        int max = 1;
        String result = "";
        for (String s : map.keySet()){
            if (map.get(s) >= max){
                result = s;
                max = map.get(s);
            }
        }

        return result;
    }
}
