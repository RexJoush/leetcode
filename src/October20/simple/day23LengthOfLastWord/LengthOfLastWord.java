package October20.simple.day23LengthOfLastWord;

/**
 * @author Joush
 * @time 2020.10.30
 */

/*

    最后一个单词的长度
    https://leetcode-cn.com/problems/length-of-last-word/

    给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。

    如果不存在最后一个单词，请返回 0 。
    说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。

    示例:
        输入: "Hello World"
        输出: 5

 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        // 将字符串尾部的空格去掉，测试用例 "a "
        s = s.trim();

        // 如果不包含空格，即只有一个单词，返回字符串长度即可，包含空串
        if (!s.contains(" ")){
            return s.length();
        }

        // 截取从后数 最后一个空格 到 字符串结尾 的字符串长度即可
        return s.substring(s.lastIndexOf(' ') + 1, s.length()).length();

    }
}
