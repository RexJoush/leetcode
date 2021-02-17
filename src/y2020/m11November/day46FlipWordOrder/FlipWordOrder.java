package y2020.m11November.day46FlipWordOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2020.11.22
 */

/*
    翻转单词顺序（剑指 Offer 58 - I）
    https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
    
    输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
    例如输入字符串"I am a student. "，则输出"student. a am I"。

    示例 1：
        输入: "the sky is blue"
        输出:"blue is sky the"
    示例 2：
        输入: " hello world! "
        输出:"world! hello"
        解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
    示例 3：
        输入: "a good  example"
        输出:"example good a"
        解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

    说明：
        1.无空格字符构成一个单词。
        2.输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
        3.如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

    
 */
public class FlipWordOrder {

    /*
        进行单词切割，此法效率更高一些，同时使用 StringBuilder 比 String 的效率高很多
     */
    public String reverseWords2(String s){
        // 空串返回空
        if(s.trim().length() == 0){
            return "";
        }

        StringBuilder result = new StringBuilder();
        List<String> list = new ArrayList<>();

        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == ' '){
                if (!result.toString().equals("")){
                    list.add(result.toString());
                    result = new StringBuilder();
                }
            }
            else {
                result.append(ch);
            }
        }
        list.add(result.toString());

        result = new StringBuilder();

        for (int i = list.size() - 1; i > 0; i--){
            result.append(list.get(i)).append(" ");
        }
        result.append(list.get(0));

        return result.toString().trim();
    }

    /*
        按照空格切分，因为可能有多个空格，所以使用正则匹配一个或多个空格
        但效率不高，应该是正则匹配效率较低
     */
    public String reverseWords(String s) {

        // 空串返回空
        if(s.trim().length() == 0){
            return "";
        }
        String result = "";

        // 正则切分字符串，按空格，一个或多个切分
        String[] strings = s.split(" +");

        // 将结果字符串进行拼接
        for (int i = strings.length - 1; i > 0; i--){
            result += strings[i] + " ";
        }
        result += strings[0];

        // 返回结果
        return result.trim();
    }

}
