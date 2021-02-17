package y2020.m11November.day33RearrangeSpacesBetweenWords;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2020.11.09
 */

/*
    重新排列单词间的空格
    https://leetcode-cn.com/problems/rearrange-spaces-between-words/

    给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证 text 至少包含一个单词 。
    请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
    返回 重新排列空格后的字符串 。

    示例 1：
        输入：text = "  this   is  a sentence "
        输出："this   is   a   sentence"
        解释：总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
    示例 2：
        输入：text = " practice   makes   perfect"
        输出："practice   makes   perfect "
        解释：总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
    示例 3：
        输入：text = "hello   world"
        输出："hello   world"
    示例 4：
        输入：text = "  walks  udp package   into  bar a"
        输出："walks  udp  package  into  bar  a "
    示例 5：
        输入：text = "a"
        输出："a"

    提示：
        1 <= text.length <= 100
        text 由小写英文字母和 ' ' 组成
        text 中至少包含一个单词

 */
public class RearrangeSpacesBetweenWords {

    /*
        暴力法，数出空格数，获取所有单词，在进行拼接，返回拼接结果
     */
    public String reorderSpaces(String text) {
        // 空格数
        int space = 0;
        // 拼接的单词
        String word = "";

        // 单词集合
        List<String> list = new ArrayList<>();

        // 遍历字符串
        for (char ch : text.toCharArray()){
            // 遇到空格就进行判断是否事单词断点
            if (ch == ' '){
                // 如果 word 不为空，则表示有单词，将单词加入集合中，并置空 word
                if (word != ""){
                    list.add(word);
                    word = "";
                }
                // 否则空格数加1，计算空格数
                space++;
            }
            // 如果不是空格，那就把字符拼接成单词
            else {
                word += ch;
            }

        }
        // 判断是否有最后的独立单词
        if (word != ""){
            list.add(word);
        }

        // 如果没有空格，则直接返回单词本身
        if (space == 0){
            return text;
        }

        // 只有一个单词，但有空格，返回拼接字符串
        if (list.size() == 1){
            return getSplicing(list.get(0), space);
        }

        // 计算分割的空格数
        int separator = space / (list.size() - 1);

        // 获取剩余未除尽的空格数
        int remainder = space % (list.size() - 1);

        // 定义返回字符串
        String result = "";

        // 从集合中拿出单词，在后面拼接相应个数的空格
        for (int i = 0; i < list.size() - 1; i++){
            result += getSplicing(list.get(i), separator);
        }

        // 最后一个单词单独处理，将余数的空格拼接在后面
        result += getSplicing(list.get(list.size() - 1), remainder);

        // 返回结果
        return result;
    }

    /**
     * 字符串拼接
     * @param text 要拼接的单词
     * @param spaceAmount 在单词后拼接的空格数
     * @return
     */
    public String getSplicing(String text, int spaceAmount){

        for (int i = 0; i < spaceAmount; i++){
            text += " ";
        }
        return text;
    }

}
