package y2022.m01January.day469RemoveOutermostParentheses;

/**
 * @author Rex Joush
 * @time 2022.01.19
 */

/*
    删除最外层的括号
    https://leetcode-cn.com/problems/remove-outermost-parentheses/

    有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
    例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
    如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
    给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
    对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。

    示例 1：
        输入：s = "(()())(())"
        输出："()()()"
        解释：
        输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
        删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
    示例 2：
        输入：s = "(()())(())(()(()))"
        输出："()()()()(())"
        解释：
        输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
        删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
    示例 3：
        输入：s = "()()"
        输出：""
        解释：
        输入字符串为 "()()"，原语化分解得到 "()" + "()"，
        删除每个部分中的最外层括号后得到 "" + "" = ""。

    提示：
        1 <= s.length <= 10^5
        s[i] 为 '(' 或 ')'
        s 是一个有效括号字符串

 */
public class RemoveOutermostParentheses {

    /*
        计算层数，遍历
        遇到左括号，计数器 +1，遇到右括号，计数器 -1。
        这样的话，一组连续且有效的括号，将不会对计数器的值产生变化。
        遇到左括号，当前计数值 > 0 则属于有效的左括号，即外层有括号，不需要被删除
        遇到右括号，当前计数值 > 0 则属于有效的右括号，拼接加上即可
     */
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int level = 0;
        for (char c : s.toCharArray()) {
            if (c == ')') --level;
            if (level >= 1) sb.append(c);
            if (c == '(') ++level;
        }
        return sb.toString();
    }
}