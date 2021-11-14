package y2021.m11November.day403RepeatedSubstringPattern;

/**
 * @author Rex Joush
 * @time 2021.11.14
 */

/*
    重复的子字符串
    https://leetcode-cn.com/problems/repeated-substring-pattern/

    给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。

    示例 1:
        输入: "abab"
        输出: True
        解释: 可由子字符串 "ab" 重复两次构成。

    示例 2:
        输入: "aba"
        输出: False
    示例 3:
        输入: "abcabcabcabc"
        输出: True
        解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)

 */
public class RepeatedSubstringPattern {

    /*
        首先想到，若 S 由 s 重复 N 次构成，那么，必有 S + S = 2Ns.(N >= 2)
        即，掐头去尾， (S + S)[1, length - 2], s 必存在于此区间中
     */
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }

}
