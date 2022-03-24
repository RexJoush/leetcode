package y2022.m03March.day511LongestValidParentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Rex Joush
 * @time 2022.03.02
 */

/*
    最长有效括号
    https://leetcode-cn.com/problems/longest-valid-parentheses/

    给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

    示例 1：
        输入：s = "(()"
        输出：2
        解释：最长有效括号子串是 "()"
    示例 2：
        输入：s = ")()())"
        输出：4
        解释：最长有效括号子串是 "()()"
    示例 3：
        输入：s = ""
        输出：0

    提示：
        0 <= s.length <= 3 * 104
        s[i] 为 '(' 或 ')'

 */
public class LongestValidParentheses {

    /*
        把所有的合法括号序列按照右括号来分类
        对于每一个右括号，求出以这个右括号为右端点的最长的合法括号序列的左端点在什么位置。
        把每个右括号都枚举一遍，记录最大长度即可
        过程如下：
            1.用栈维护当前匹配的左括号的位置，用 start 记录一个可能的合法字串的起始位置
            2.如果当前为 (, 将下标入栈
            3.如果 ), 弹出栈顶元素
                此时，如果栈空，说明当前右括号的左端点为 start，更新答案
                    length = i - start + 1
                如果栈不空，说明当前右括号的合法序列为栈顶元素的下一个元素，即
                    length = i - s.peek();
            4.遇到右括号且栈空，则 start 开始的字串不可能为合法字串了，更新 start = i + 1
        结果：
            2 ms, 55.66%
            41.2 MB, 40.00%
     */
    public int longestValidParentheses(String s) {
        // 初始化栈
        Deque<Integer> deque = new ArrayDeque<>();

        char[] chars = s.toCharArray();
        int max = 0;
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                // 将当前下标入栈
                deque.push(i);
            } else {
                // 栈空，则单独右括号无法匹配，起始位置往后移动一位
                if (deque.isEmpty()) {
                    start = i + 1;
                    continue;
                }
                // 非空，左括号出栈
                deque.pop();
                // 当前栈空了，说明正好匹配，length = i - start + 1
                if (deque.isEmpty()) {
                    max = Math.max(max, i - start + 1);
                } else {
                    // 不空，说明，当前右括号匹配了之前的起始位置
                    max = Math.max(max, i - deque.peek());
                }
            }
        }
        // 返回最大值
        return max;
    }
}
