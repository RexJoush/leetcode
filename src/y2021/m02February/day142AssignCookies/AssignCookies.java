package y2021.m02February.day142AssignCookies;

import java.util.Arrays;

/**
 * @author Rex Joush
 * @time 2021.02.26
 */

/*
    分发饼干
    https://leetcode-cn.com/problems/assign-cookies/

    假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
    对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
    都有一个尺寸 s[j]。如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
    你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

    示例 1:
    输入: g = [1,2,3], s = [1,1]
    输出: 1
        解释:
        你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
        虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
        所以你应该输出1。
    示例 2:
        输入: g = [1,2], s = [1,2,3]
        输出: 2
        解释:
        你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
        你拥有的饼干数量和尺寸都足以让所有孩子满足。
        所以你应该输出2.

    提示：
        1 <= g.length <= 3 * 10^4
        0 <= s.length <= 3 * 10^4
        1 <= g[i], s[j] <= 2^31 - 1

 */
public class AssignCookies {

    /*
        贪心思想，将饼干数组和胃口数组排序，从最大的开始满足，直到无法满足所有
        结果：
            7 ms, 99.76%
            39 MB, 82.28%
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int m = g.length - 1;
        int n = s.length - 1;
        // 遍历饼干数组
        while (m >= 0 && n >= 0) {
            // 如果当前最大的饼干满足当前最大的胃口，计数加一，饼干往前移一个
            if (s[n] >= g[m]) {
                count++;
                n--;
            }
            // 胃口往前移一个
            m--;
        }
        return count;
    }

}
