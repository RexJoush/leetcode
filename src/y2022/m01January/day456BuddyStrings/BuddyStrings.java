package y2022.m01January.day456BuddyStrings;

/**
 * @author Rex Joush
 * @time 2022.01.06
 */

/*
    亲密字符串
    https://leetcode-cn.com/problems/buddy-strings/

    给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
    交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
    例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。

    示例 1：
        输入：s = "ab", goal = "ba"
        输出：true
        解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
    示例 2：
        输入：s = "ab", goal = "ab"
        输出：false
        解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
    示例 3：
        输入：s = "aa", goal = "aa"
        输出：true
        解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
    示例 4：
        输入：s = "aaaaaaabc", goal = "aaaaaaacb"
        输出：true

    提示：
        1 <= s.length, goal.length <= 2 * 10^4
        s 和 goal 由小写英文字母组成

 */
public class BuddyStrings {

    /*
        顺序遍历即可
        结果：
            1 ms, 99.04%
            38.3 MB, 51.81%
     */
    public boolean buddyStrings(String s, String goal) {

        // 字符串长度不等，返回 false
        if (s.length() != goal.length()) {
            return false;
        }

        // 字符串一样，如果有重复字符，则返回true
        if (s.equals(goal)) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i) - 'a';
                count[ch]++;
                if (count[ch] > 1) {
                    return true;
                }
            }
            return false;
        }

        int first = -1;
        int second = -1;
        int noEqual = 0;

        for (int i = 0; i < s.length(); i++) {
            // 如果不等，则
            if (s.charAt(i) != goal.charAt(i)) {
                // 如果当前没有不同字符，则更新 first 为当前字符的位置
                if (noEqual == 0) {
                    first = i;
                } else if (noEqual == 1) {
                    // 如果当前有一个不同字符，则更新 second 为当前字符的位置
                    second = i;
                } else {
                    // 当前已经有两个不同了，直接返回 false
                    return false;
                }
                noEqual++;
            }
        }

        // 交换位置同时确保必须有两个字符不同
        return second != -1 && s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first);

    }
}
