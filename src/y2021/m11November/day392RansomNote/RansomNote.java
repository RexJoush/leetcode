package y2021.m11November.day392RansomNote;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2021.11.03
 */

/*
    赎金信
    https://leetcode-cn.com/problems/ransom-note/

    给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
    判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。
    如果可以构成，返回 true ；否则返回 false。

    (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。
    杂志字符串中的每个字符只能在赎金信字符串中使用一次。)

    示例 1：
        输入：ransomNote = "a", magazine = "b"
        输出：false
    示例 2：
        输入：ransomNote = "aa", magazine = "ab"
        输出：false
    示例 3：
        输入：ransomNote = "aa", magazine = "aab"
        输出：true

    提示：
        你可以假设两个字符串均只含有小写字母。

 */
public class RansomNote {

    /*
        方法一：将杂志字符串存入哈希表中，计数即可
        结果：
            9 ms, 32.33%
            39.2 MB, 5.17%
     */
    public boolean canConstruct(String ransomNote, String magazine) {

        Map<Character, Integer> map = new HashMap<>();

        // 将 magazine 的所有字母统计并放入哈希表中
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = magazine.charAt(i);
            int count = map.getOrDefault(c, 0);
            if (count <= 0) {
                return false;
            }
            map.put(c, --count);
        }
        return true;
    }

    /*
        方法二，与方法一类似，但无需用到哈希表，因字母只有 26 个，所以使用数组即可
        结果：
            1 ms, 99.92%
            38.5 MB, 81.58%
     */
    public boolean canConstruct2(String ransomNote, String magazine) {

        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            arr[c - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (--arr[c - 'a'] < 0){
                return false;
            }
        }
        return true;
    }

}
