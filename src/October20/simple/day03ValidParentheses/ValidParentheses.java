package October20.simple.day03ValidParentheses;

import java.util.Stack;

/**
 * @author Joush
 * @time 2020.10.10
 */

 
/*
    有效的括号
    https://leetcode-cn.com/problems/valid-parentheses/submissions/

    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串。
    示例 1:
        输入: "()"
        输出: true
    示例 2:
        输入: "()[]{}"
        输出: true
    示例 3:
        输入: "(]"
        输出: false
    示例 4:
        输入: "([)]"
        输出: false
    示例 5:
        输入: "{[]}"
        输出: true
 */
public class ValidParentheses {
    public boolean isValid(String s) {

        // 将字符串变为字符数组
        char[] chars = s.toCharArray();

        // 初始化一个栈
        Stack<Character> stack = new Stack<>();

        // 遍历字符串
        for (int i = 0; i < chars.length; i++){

            // 遇到左括号即入栈
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{'){
                stack.push(chars[i]);
            }
            // 否则进行判断
            else {
                // 如果遇到右括号，但栈空，直接返回false
                if (stack.empty()){
                    return false;
                }

                // 弹出栈顶字符
                char ch = stack.pop();

                // 如果当前字符是 ')'
                if (chars[i] == ')'){
                    if (ch != '('){
                        return false;
                    }
                }
                // 如果当前字符是 ']'
                else if (ch == ']'){
                    if (stack.pop() != '['){
                        return false;
                    }
                }
                // 如果当前字符是 '}'
                else {
                    if (ch != '{'){
                        return false;
                    }
                }
            }
        }

        // 如果最后左括号多余，则栈非空，返回 false, 否则返回 true
        return stack.empty();

    }
}
