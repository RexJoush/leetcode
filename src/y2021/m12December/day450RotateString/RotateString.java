package y2021.m12December.day450RotateString;

/**
 * @author Rex Joush
 * @time 2021.12.31
 */

/*
    旋转字符串
    https://leetcode-cn.com/problems/rotate-string/

    给定两个字符串, A 和 B。
    A 的旋转操作就是将 A 最左边的字符移动到最右边。
    例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。
    如果在若干次旋转操作之后，A 能变成B，那么返回True。
    
    示例 1:
        输入: A = 'abcde', B = 'cdeab'
        输出: true
    示例 2:
        输入: A = 'abcde', B = 'abced'
        输出: false

    注意：
        A 和 B 长度不超过 100。

 */
public class RotateString {

    /*
        将字符串与自身拼接，则目标串一定在新串中，返回即可
        结果：
            0 ms, 100.00%
            36.2 MB, 64.23%
     */
    public boolean rotateString(String s, String goal) {
        // 需保证串长度相同
        return s.length() == goal.length() && (s + s).contains(goal);
    }

}
