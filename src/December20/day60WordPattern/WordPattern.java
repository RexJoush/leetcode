package December20.day60WordPattern;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Joush
 * @time 2020.12.06
 */

/*
    单词规律
    https://leetcode-cn.com/problems/word-pattern/

    给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
    这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

    示例1:
        输入: pattern = "abba", str = "dog cat cat dog"
        输出: true
    示例 2:
        输入:pattern = "abba", str = "dog cat cat fish"
        输出: false
    示例 3:
        输入: pattern = "aaaa", str = "dog cat cat dog"
        输出: false
    示例 4:
        输入: pattern = "abba", str = "dog dog dog dog"
        输出: false

    说明:
        你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。

 */
public class WordPattern {

    /*
        将字母组的每个字母建立与单词的映射，并放入集合中，如果每个字母的映关系都符合，就返回 true
        其中，如果某两个字母映射了同一个单词，则直接返回 false，解决题目 示例三 的情况
     */
    public boolean wordPattern(String pattern, String s) {

        // 得到单词的数组
        String[] strings = s.split(" ");

        // 建立映射关系的集合
        Map<Character, String> map = new LinkedHashMap<>();

        // 字母数组
        char[] chars = pattern.toCharArray();

        // 如果两个长度不一样，则直接返回 false
        if (strings.length != chars.length){
            return false;
        }

        // 遍历字母数组和单词数组
        for (int i = 0; i < chars.length; i++){
            // 如果当前字母和单词的映射关系已经建立，则查看当前的单词是不是映射的值
            if (map.containsKey(chars[i])){
                // 不是已经建立的映射单词，直接返回 false
                if (!map.get(chars[i]).equals(strings[i])){
                    return false;
                }
            }
            else {
                // 查看当前想要建立的映射关系是不是存在重复映射
                for (char ch : map.keySet()){
                    // 存在重复映射，返回 false
                    if (map.get(ch).equals(strings[i])){
                        return false;
                    }
                }
                // 不存在重复映射，建立映射关系
                map.put(chars[i], strings[i]);
            }
        }

        // 返回 true
        return true;
    }
}
