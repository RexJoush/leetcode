package y2022.m04April.day548DecodeString;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Rex Joush
 * @time 2022.04.08
 */

/*
    字符串解码
    https://leetcode-cn.com/problems/decode-string/

    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

    示例 1：
    输入：s = "3[a]2[bc]"
    输出："aaabcbc"
    示例 2：
    输入：s = "3[a2[c]]"
    输出："accaccacc"
    示例 3：
    输入：s = "2[abc]3[cd]ef"
    输出："abcabccdcdcdef"
    示例 4：
    输入：s = "abc3[cd]xyz"
    输出："abccdcdcdxyz"

    提示：
        1 <= s.length <= 30
        s 由小写英文字母、数字和方括号 '[]' 组成
        s 保证是一个 有效 的输入。
        s 中所有整数的取值范围为 [1, 300]

 */
public class DecodeString {

    /*
        使用栈
        结果：
            2 ms, 27.15%
            39.6 MB, 24.96%
     */
    public String decodeString(String s) {

        char[] chars = s.toCharArray();

        Deque<Character> deque = new ArrayDeque<>();
        for (char ch : chars) {
            // 遇到右括号进行处理，否则就入栈即可
            if (ch == ']') {
                // 需要重复的字符串
                StringBuilder value = new StringBuilder();
                // 把 [] 之间的字符串拼接起来
                while (!deque.isEmpty() && deque.peek() != '[') {
                    value.insert(0, deque.pop());
                }
                // 把 [ 出栈
                deque.pop();
                // 获取重复字符串的遍数
                StringBuilder numS = new StringBuilder();
                while (!deque.isEmpty() && Character.isDigit(deque.peek())) {
                    numS.insert(0, deque.pop());
                }
                int number = Integer.parseInt(numS.toString());
                // 重复 number 遍，再次 push 到栈中
                while (number > 0) {
                    for (int k = 0; k < value.length(); k++) {
                        deque.push(value.charAt(k));
                    }
                    number--;
                }
            } else {
                deque.push(ch);
            }

        }
        // 将栈中的字符整个拼成字符串，返回即可
        StringBuilder result = new StringBuilder();
        while (!deque.isEmpty()) {
            result.append(deque.pollLast());
        }
        return result.toString();
    }
}
