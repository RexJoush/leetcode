package y2021.m11November.day395IsSubsequence;

/**
 * @author Rex Joush
 * @time 2021.11.06
 */

/*
    判断子序列
    https://leetcode-cn.com/problems/is-subsequence/

    给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
    （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

    进阶：
    如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

    示例 1：
        输入：s = "abc", t = "ahbgdc"
        输出：true
    示例 2：
        输入：s = "axc", t = "ahbgdc"
        输出：false

    提示：
        0 <= s.length <= 100
        0 <= t.length <= 10^4
        两个字符串都只由小写字符组成。

 */
public class IsSubsequence {

    /*
        双指针法，两个指针从左至右进行判断，碰到字串的字符，就往后移即可
        结果：
            0 ms, 100.00%
            36.6 MB, 25.91%
     */
    public boolean isSubsequence(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        int j = 0;
        for (char c : tArray) {
            if (j >= sArray.length) {
                return true;
            }
            if (sArray[j] == c) {
                j++;
            }
        }
        return j == sArray.length;
    }

}
