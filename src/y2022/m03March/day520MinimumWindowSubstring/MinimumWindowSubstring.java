package y2022.m03March.day520MinimumWindowSubstring;

/**
 * @author Rex Joush
 * @time 2022.03.11
 */

/*
    最小覆盖子串
    https://leetcode-cn.com/problems/minimum-window-substring/

    给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
    如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    注意：
        对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
        如果 s 中存在这样的子串，我们保证它是唯一的答案。

    示例 1：
        输入：s = "ADOBECODEBANC", t = "ABC"
        输出："BANC"
    示例 2：
        输入：s = "a", t = "a"
        输出："a"
    示例 3:
        输入: s = "a", t = "aa"
        输出: ""
        解释: t 中两个字符 'a' 均应包含在 s 的子串中，
        因此没有符合条件的子字符串，返回空字符串。

    提示：
        1 <= s.length, t.length <= 10^5
        s 和 t 由英文字母组成

 */
public class MinimumWindowSubstring {

    /*
        滑动窗口，具体思路如下
        int left = right = 0;
        初始情况下，将 right 向右移动到 s 的某个子串的位置，恰好能够包含所有的 t 中出现的字符
        即，如下情况，此时恰好包含了 t 中的所有字符，此时，将 left往右移动
        直到某一时刻，区间不再包含 t 中的所有字母，就找到了此时的最小区间，如下图所示
            left                right                 left        right
            |                     |                     |           |
            A D O B E C O D E B A N C   -->   A D O B E C O D E B A N C
            B A N C                           B A N C
        此时，我们找到了第一个包含完整 t 串的最短字串

        继续将 left 往后移动一位，此时区间不满足要求
        因此，将 right 往后移动直至满足要求，如下所示
        此时继续移动 left，至恰好满足条件，找到第二个满足 完整 t 的字符串
                       left      right                   left        right                        left  right
                        |         |                       |           |                             |     |
            A D O B E C O D E B A N C   -->   A D O B E C O D E B A N C    -->    A D O B E C O D E B A N C
            B A N C                           B A N C                             B A N C
        重复以上过程，直至 right 到串尾，记录此过程的最短字串的长度，即为所求结果

        此过程中，判断 s 的字串是否完整包含 t 串有多种方法，哈希表或者暴力匹配
        考虑一种简便方式，词频数组
            s:   a b c a b                      t    a a b
              winFreq = [a,2][b,2][c,1]     tFreq = [a,2][b,1]
        使用 distance 表示窗口内包含了 T 中字符的个数，窗口内单个字符个数等于 T 中对应字符个数的时候不再增加
        当 right 向右移动的过程中，且 winFreq[s[right]] < tFreq[s[right]] 时，
            即，s 中的某个字符还不够满足 t 中某个字符的个数，distance + 1，等于时不再增加
        当 left 向右移动时，且 winFreq[s[left] == tFreq[s[left]] 时，
            即，当左边界的字符恰好等于 t 串中的字符个数时，右移时要把它移除，因此 distance - 1
        结果：
            1 ms, 100.00%
            41.1 MB, 66.71%
     */
    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if (sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }

        // 此处 123 指的是 'z'最大为 123
        int[] winFreq = new int[123];
        int[] tFreq = new int[123];

        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        // 统计字符串 T 的词频
        for (char c : charsT) {
            tFreq[c]++;
        }

        int distance = 0; // distance 初始为 0，当 distance = charsT.length 时，认为满足条件
        int minLen = sLen + 1; // 最小值，初始化为较大的数字
        int start = 0; // 记录最短字串的位置，因为需要返回最短字串

        int left = 0;
        int right = 0;

        // right 不到结尾
        while (right < sLen) {
            // 右边界字符不出现在 T 中，直接后移
            if (tFreq[charsS[right]] == 0) {
                right++;
                continue;
            }
            // 当前字符的个数，在字串中出现的次数，小于在 t 中的次数，即距离+1
            if (winFreq[charsS[right]] < tFreq[charsS[right]]) {
                distance++;
            }

            // 出现时，右边界字符++
            winFreq[charsS[right]]++;
            right++;
            // 当 distance = tLen 时，说明窗口内的值正好满足条件，左针右移
            while (distance == tLen) {
                // 满足条件了，更新最短字串
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                // 左边界字符不出现在 T 中，直接后移
                if (tFreq[charsS[left]] == 0) {
                    left++;
                    continue;
                }
                // 当前字符的个数，在字串中出现的次数，等于在 t 中的次数，即距离 - 1
                if (winFreq[charsS[left]] == tFreq[charsS[left]]) {
                    distance--;
                }
                winFreq[charsS[left]]--;
                left++;
            }
        }
        // 即，没有更新，不存在返回空串
        if (minLen == sLen + 1) {
            return "";
        }
        return s.substring(start, start + minLen);
    }
}
