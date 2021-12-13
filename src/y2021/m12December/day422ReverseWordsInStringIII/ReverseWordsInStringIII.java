package y2021.m12December.day422ReverseWordsInStringIII;

/**
 * @author Rex Joush
 * @time 2021.12.03
 */

/*
    反转字符串中的单词 III
    https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/

    给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

    示例：
        输入："Let's take LeetCode contest"
        输出："s'teL ekat edoCteeL tsetnoc"

    提示：
        在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

 */
public class ReverseWordsInStringIII {

    /*
        分割反转即可
        结果：
            4 ms, 83.19%
            39 MB, 45.02%
     */
    public String reverseWords(String s) {
        // 按空格切分字符串
        String[] strings = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        // 遍历每个单词，分别反转即可
        for (int i = 0; i < strings.length - 1; i++) {
            stringBuilder.append(reverse(strings[i])).append(" ");
        }
        stringBuilder.append(reverse(strings[strings.length - 1]));
        return stringBuilder.toString();
    }

    // 反转函数
    public StringBuilder reverse(String reverse) {
        StringBuilder stringBuilder = new StringBuilder(reverse);
        return stringBuilder.reverse();
    }

}
