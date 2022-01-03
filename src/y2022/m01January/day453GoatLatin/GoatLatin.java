package y2022.m01January.day453GoatLatin;

/**
 * @author Rex Joush
 * @time 2022.01.03
 */

/*
    山羊拉丁文
    https://leetcode-cn.com/problems/goat-latin/

    给定一个由空格分割单词的句子 S。每个单词只包含大写或小写字母。
    我们要将句子转换为 “Goat Latin”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。
    山羊拉丁文的规则如下：
    如果单词以元音开头（a, e, i, o, u），在单词后添加"ma"。
    例如，单词"apple"变为"applema"。
    如果单词以辅音字母开头（即非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
    例如，单词"goat"变为"oatgma"。
    根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从1开始。
    例如，在第一个单词后添加"a"，在第二个单词后添加"aa"，以此类推。
    返回将 S 转换为山羊拉丁文后的句子。

    示例 1:
        输入: "I speak Goat Latin"
        输出: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
    示例 2:
        输入: "The quick brown fox jumped over the lazy dog"
        输出: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"

    说明:
        S 中仅包含大小写字母和空格。单词间有且仅有一个空格。
        1 <= S.length <= 150。

 */
public class GoatLatin {

    /*
        按规则操作即可
        结果：
            1 ms, 100.00%
            36.6 MB, 91.00%
     */
    public String toGoatLatin(String sentence) {
        // 切割单词
        String[] words = sentence.split(" ");

        // 结果字符串
        StringBuilder result = new StringBuilder();

        // 结尾的 a 串
        StringBuilder endString = new StringBuilder("a");
        for (int i = 0; i < words.length - 1; i++) {
            // 读取第一个字符
            char c = words[i].charAt(0);
            // 如果以元音开头
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                    c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                // 拼接 单词本身 + "ma" + 结尾 a 串 + " "
                result.append(words[i]).append("ma").append(endString).append(" ");
            } else {
                // 非元音开头 拼接 单词本身除首字母 + 首字母 + "ma" + 结尾 a 串 + " "
                result.append(words[i].substring(1)).append(c).append("ma").append(endString).append(" ");
            }
            // 结尾串添加 a
            endString.append("a");
        }
        // 判断最后一个单词，不需要加最后的空格
        char c = words[words.length - 1].charAt(0);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            result.append(words[words.length - 1]).append("ma").append(endString);
        } else {
            result.append(words[words.length - 1].substring(1)).append(c).append("ma").append(endString);
        }
        // 返回结果
        return result.toString();
    }
}
