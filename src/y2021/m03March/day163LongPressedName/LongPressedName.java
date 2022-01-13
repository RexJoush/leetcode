package y2021.m03March.day163LongPressedName;

/**
 * @author Rex Joush
 * @time 2021.03.19
 */

/*
    长按键入
    https://leetcode-cn.com/problems/long-pressed-name/

    你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
    你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。

    示例 1：
        输入：name = "alex", typed = "aaleex"
        输出：true
        解释：'alex' 中的 'a' 和 'e' 被长按。
    示例 2：
        输入：name = "saeed", typed = "ssaaedd"
        输出：false
        解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
    示例 3：
        输入：name = "leelee", typed = "lleeelee"
        输出：true
    示例 4：
        输入：name = "laiden", typed = "laiden"
        输出：true
        解释：长按名字中的字符并不是必要的。

    提示：
        name.length <= 1000
        typed.length <= 1000
        name 和 typed 的字符都是小写字母。

 */
public class LongPressedName {

    /*
        双指针，比较判断即可
        结果：
            0 ms, 100.00%
            36.4 MB, 70.81%
     */
    public boolean isLongPressedName(String name, String typed) {
        char[] nameChars = name.toCharArray();
        char[] typedChars = typed.toCharArray();

        int i = 0;
        int j = 0;

        while (j < typedChars.length) {
            // 如果两个字符相等，就均往后移一个字符
            if (i < nameChars.length && nameChars[i] == typedChars[j]) {
                i++;
                j++;
            }
            // 如果 typed 当前字符和前一个字符相同，则表示长按了，j++
            else if (j > 0 && typedChars[j] == typedChars[j - 1]) {
                j++;
            } else {
                // 此时不满足条件，直接 false
                return false;
            }
        }
        // 如果最后遍历到结尾，返回 true
        return i == nameChars.length;
    }
}
