package y2021.m11November.day393FirstUniqueCharacterInAString;

/**
 * @author Rex Joush
 * @time 2021.11.04
 */

/*
    字符串中的第一个唯一字符
    https://leetcode-cn.com/problems/first-unique-character-in-a-string/

    给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

    示例：
        s = "leetcode"
        返回 0

        s = "loveleetcode"
        返回 2

    提示：你可以假定该字符串只包含小写字母。

 */
public class FirstUniqueCharacterInAString {

    /*
        遍历第一遍字符串，记录每个字母的出现次数
        遍历第二遍字符串，计算每个次数的次数
        结果：
            5 ms, 82.88%
            38.8 MB, 71.90%
     */
    public int firstUniqChar(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (arr[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

}
