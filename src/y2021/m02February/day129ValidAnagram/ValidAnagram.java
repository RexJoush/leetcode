package y2021.m02February.day129ValidAnagram;

import java.util.*;

/**
 * @author Joush
 * @time 2021.02.13
 */

/*
    有效的字母异位词
    https://leetcode-cn.com/problems/valid-anagram/

    给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

    示例 1:
        输入: s = "anagram", t = "nagaram"
        输出: true
    示例 2:
        输入: s = "rat", t = "car"
        输出: false

    说明:
        你可以假设字符串只包含小写字母。

 */
public class ValidAnagram {

    /*
        字母异位词，表示单词所含字母相同，但次序不同,，即两个字符串排序后相等
        将两个字符串排序比较即可
        结果：
            3 ms, 86.23%
            38.7 MB, 55.07%
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
