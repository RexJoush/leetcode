package y2022.m01January.day455BackspaceStringCompare;

import java.util.Stack;

/**
 * @author Rex Joush
 * @time 2022.01.05
 */

/*
    比较含退格的字符串
    https://leetcode-cn.com/problems/backspace-string-compare/

    给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，请你判断二者是否相等。# 代表退格字符。
    如果相等，返回 true ；否则，返回 false 。
    注意：如果对空文本输入退格字符，文本继续为空。

    示例 1：
        输入：s = "ab#c", t = "ad#c"
        输出：true
        解释：S 和 T 都会变成 “ac”。
    示例 2：
        输入：s = "ab##", t = "c#d#"
        输出：true
        解释：s 和 t 都会变成 “”。
    示例 3：
        输入：s = "a##c", t = "#a#c"
        输出：true
        解释：s 和 t 都会变成 “c”。
    示例 4：
        输入：s = "a#c", t = "b"
        输出：false
        解释：s 会变成 “c”，但 t 仍然是 “b”。

    提示：
        1 <= s.length, t.length <= 200
        s 和 t 只含有小写字母以及字符 '#'

 */
public class BackspaceStringCompare {

    /*
        方法一：双指针，从后向前比较即可
        结果：
            0 ms, 100.00%
            36.5 MB, 69.66%
     */
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;  // s 的 # 数量
        int skipT = 0;  // t 的 # 数量

        while (i >= 0 || j >= 0) {
            // 保证 i 不越界
            while (i >= 0) {
                // 如果遇到 #，则跳过的字符数 + 1，指针向左移一位
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    // 不是 #，判断当前字符是否需要跳过
                    skipS--;    // 跳过 - 1
                    i--;        // 指针左移
                } else {
                    // 常规字符，则跳出循环
                    break;
                }
            }

            // 与 S 同样的操作
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            // 两边都遇到了常规字符，则进行判断
            if (i >= 0 && j >= 0) {
                // 不相等则直接返回 false
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else {
                // 此时，仅有一个串已经遍历完毕
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            // 相等，指针左移
            i--;
            j--;
        }
        // 比较完毕
        return true;
    }

    /*
        方法二：比较即可，使用栈，遇见 # 出栈，普通字符就进栈
        结果：
            2 ms, 21.56%
            36.8 MB, 11.97%
     */
    public boolean backspaceCompare2(String s, String t) {
        // 使用栈
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();

        // 将第一个字符串进栈
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 非 # 就进栈
            if (c != '#') {
                stackS.push(c);
            } else {
                // # 则出栈
                if (!stackS.empty()) {
                    stackS.pop();
                }
            }
        }

        /*
            同样的流程，第二个字符进栈
         */
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c != '#') {
                stackT.push(c);
            } else {
                if (!stackT.empty()) {
                    stackT.pop();
                }
            }
        }

        // 判断两个栈是否相同
        while (!stackS.empty() && !stackT.empty()) {
            if (stackS.peek() != stackT.peek()) {
                return false;
            }
            stackS.pop();
            stackT.pop();
        }
        return stackS.empty() && stackT.empty();
    }
}
