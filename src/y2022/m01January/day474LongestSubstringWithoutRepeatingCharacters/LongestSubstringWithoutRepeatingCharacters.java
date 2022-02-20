package y2022.m01January.day474LongestSubstringWithoutRepeatingCharacters;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.01.24
 */

/*
    无重复字符的最长子串
    https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/

    给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。

    示例1:
        输入: s = "abcabcbb"
        输出: 3
        解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    示例 2:
        输入: s = "bbbbb"
        输出: 1
        解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    示例 3:
        输入: s = "pwwkew"
        输出: 3
        解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
            请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。

    提示：
        0 <= s.length <= 5 * 10^4
        s 由英文字母、数字、符号和空格组成

 */
public class LongestSubstringWithoutRepeatingCharacters {

    /*
        使用队列，始终保持队列中的字符串为无重复的字串
        遍历完毕后，在此过程中，队列的最长长度，即为最长不重复字串的长度
        结果：
            4 ms, 86.71%
            41.7 MB, 7.04%
     */
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();

        // 定义一个队列
        Queue<Character> queue = new ArrayDeque<>();

        int max = 0;
        for (char ch : chars) {

            /*
                如果遇到了集合中有的字符，则将队列中，此字符之前的字符串全部删除
                即，
                    当队列中已经存储的字符串为 a b c d e
                    当前字符为 c, 队列中已经存在，那么将 c 及之前的所有字符串均取出
                    此时，队列中为 d e
                    将新的 c 再放入队列
                    队列变为 d e c
             */
            if (queue.contains(ch)) {
                while (!queue.isEmpty() && queue.remove() != ch) ;
            }
            queue.offer(ch);
            // 每次更新最大值即可
            max = Math.max(max, queue.size());
        }
        // 返回最大值
        return max;
    }
}
