package y2021.m11November.day394FindTheDifference;

/**
 * @author Rex Joush
 * @time 2021.11.05
 */

/*
    找不同
    https://leetcode-cn.com/problems/find-the-difference/

    给定两个字符串 s 和 t，它们只包含小写字母。
    字符串t由字符串s随机重排，然后在随机位置添加一个字母。
    请找出在 t 中被添加的字母。

    示例 1：
        输入：s = "abcd", t = "abcde"
        输出："e"
        解释：'e' 是那个被添加的字母。
    示例 2：
        输入：s = "", t = "y"
        输出："y"
    示例 3：
        输入：s = "a", t = "aa"
        输出："a"
    示例 4：
        输入：s = "ae", t = "aea"
        输出："a"

    提示：
        0 <= s.length <= 1000
        t.length == s.length + 1
        s 和 t 只包含小写字母

 */
public class FindTheDifference {

    /*
        方法一：字母计数，当某个字母为 - 时，即为多加的字母
        结果：
            3 ms, 47.97%
            36.6 MB, 79.54%
     */
    public char findTheDifference(String s, String t) {

        int[] arr = new int[26];

        // 记录每个字母出现的次数
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            arr[c - 'a']--;
            if (arr[c - 'a'] < 0) {
                return t.charAt(i);
            }
        }
        return ' ';
    }

    /*
        方法二：计算 ascii 之和，然后做差，即可得到不一样的字母
        结果：
            2 ms, 77.36%
            36.7 MB, 69.43%
    */
    public char findTheDifference2(String s, String t) {

        int tASCII = 0;
        // 计算 t 字符串的 ascii 码之和
        for (int i = 0; i < t.length(); i++) {
            tASCII += t.charAt(i);
        }
        // 减去 s 串的所有字母
        for (int i = 0; i < s.length(); i++) {
            tASCII -= s.charAt(i);
        }

        // 剩余字母即为多出来的字母
        return (char) tASCII;
    }

}
