package y2021.m11November.day416DetectCapital;

/**
 * @author Rex Joush
 * @time 2021.11.27
 */

/*
    检测大写字母
    https://leetcode-cn.com/problems/detect-capital/

    我们定义，在以下情况时，单词的大写用法是正确的：
    全部字母都是大写，比如 "USA" 。
    单词中所有字母都不是大写，比如 "leetcode" 。
    如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
    给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。

    示例 1：
        输入：word = "USA"
        输出：true
    示例 2：
        输入：word = "FlaG"
        输出：false

    提示：
        1 <= word.length <= 100
        word 由小写和大写英文字母组成

 */
public class DetectCapital {

    /*
        根据规则判断即可
        结果：
            1 ms, 94.53%
            36.2 MB, 98.19%
     */
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }
        char[] chars = word.toCharArray();
        // 如果第一个为大写字母，则考虑，首字母大写，或者全部大写
        if (Character.isUpperCase(chars[0])) {
            // 全部大写情况
            if (Character.isUpperCase(chars[1])) {
                for (int i = 2; i < chars.length; i++) {
                    // 发现大写字母即返回 false
                    if (Character.isLowerCase(chars[i])) {
                        return false;
                    }
                }
            }
            // 全部小写
            else {
                for (int i = 1; i < chars.length; i++) {
                    // 发现大写字母即返回 false
                    if (Character.isUpperCase(chars[i])) {
                        return false;
                    }
                }
            }
        }
        // 否则，只能为全部小写
        else {
            for (int i = 1; i < chars.length; i++) {
                // 发现大写字母即返回 false
                if (Character.isUpperCase(chars[i])) {
                    return false;
                }
            }
        }
        return true;
    }

}
