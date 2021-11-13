package y2021.m02February.day140NumberOfSegmentsInString;

/**
 * @author Rex Joush
 * @time 2021.02.24
 */

/*
    字符串中的单词数
    https://leetcode-cn.com/problems/number-of-segments-in-a-string/

    统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
    请注意，你可以假定字符串里不包括任何不可打印的字符。

    示例:
        输入: "Hello, my name is John"
        输出: 5
        解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。

 */
public class NumberOfSegmentsInString {

    /*
        统计所有单词即可，即，当前字符不是空格，且前一个字符为空格，加一即可
        结果：
            0 ms, 100.00%
            35.9 MB, 91.84%
     */
    public int countSegments(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if ((i == 0 || chars[i - 1] == ' ') && chars[i] != ' ') {
                count++;
            }
        }
        return count;
    }

}
