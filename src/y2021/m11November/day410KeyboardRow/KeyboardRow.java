package y2021.m11November.day410KeyboardRow;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2021.11.21
 */

/*
    键盘行
    https://leetcode-cn.com/problems/keyboard-row/

    给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
    美式键盘 中：
    第一行由字符 "qwertyuiop" 组成。
    第二行由字符 "asdfghjkl" 组成。
    第三行由字符 "zxcvbnm" 组成。

    示例 1：
        输入：words = ["Hello","Alaska","Dad","Peace"]
        输出：["Alaska","Dad"]
    示例 2：
        输入：words = ["omk"]
        输出：[]
    示例 3：
        输入：words = ["adsdf","sfd"]
        输出：["adsdf","sfd"]

    提示：
    1 <= words.length <= 20
    1 <= words[i].length <= 100
    words[i] 由英文字母（小写和大写字母）组成

 */
public class KeyboardRow {

    /*
        遍历即可
        结果：
            0 ms, 100.00%
            36.2 MB, 80.27%
     */
    public String[] findWords(String[] words) {

        ArrayList<String> result = new ArrayList<>();

        // 第一行的字母 set 集合
        Set<Character> setRow1 = Set.of('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p');
        // 第二行的字母 set 集合
        Set<Character> setRow2 = Set.of('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l');
        // 第三行的字母 set 集合
        Set<Character> setRow3 = Set.of('Z', 'X', 'C', 'V', 'B', 'N', 'M', 'z', 'x', 'c', 'v', 'b', 'n', 'm');

        // 遍历所有字符串
        for (String word : words) {
            // 将每个字符串转换成字符数组，遍历字符数组
            char[] chars = word.toCharArray();
            boolean ok = true;
            // 第一个字母在第一行
            if (setRow1.contains(chars[0])) {
                // 遍历剩下的字符，如果出现不在第一行，则结束循环，ok 置为 false
                for (int i = 1; i < chars.length; i++) {
                    if (!setRow1.contains(chars[i])) {
                        ok = false;
                        break;
                    }
                }
            }
            // 第一个字母在第二行
            else if (setRow2.contains(chars[0])) {
                for (int i = 1; i < chars.length; i++) {
                    if (!setRow2.contains(chars[i])) {
                        ok = false;
                        break;
                    }
                }
            }
            // 第一个字母在第三行
            else {
                for (int i = 1; i < chars.length; i++) {
                    if (!setRow3.contains(chars[i])) {
                        ok = false;
                        break;
                    }
                }
            }
            // 如果满足，放入结果集合
            if (ok) {
                result.add(word);
            }
        }
        // 将 List 转为字符串数组
        String[] strings = new String[result.size()];
        return result.toArray(strings);
    }

}
