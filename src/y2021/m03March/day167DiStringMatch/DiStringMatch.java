package y2021.m03March.day167DiStringMatch;

/**
 * @author Rex Joush
 * @time 2021.03.23
 */

/*
    增减字符串匹配
    https://leetcode-cn.com/problems/di-string-match/

    给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
    返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
    如果 S[i] == "I"，那么 A[i] < A[i+1]
    如果 S[i] == "D"，那么 A[i] > A[i+1]

    示例 1：
        输入："IDID"
        输出：[0,4,1,3,2]
    示例 2：
        输入："III"
        输出：[0,1,2,3]
    示例 3：
        输入："DDI"
        输出：[3,2,0,1]

    提示：
        1 <= S.length <= 10000
        S 只包含字符 "I" 或 "D"。

 */
public class DiStringMatch {

    /*
        定义一个最大值，定义一个最小值，遇见 D 就取最大值，并将最大值 - 1，遇见 I 就去最小值，并将最小值 + 1
        结果：
            1 ms, 100.00%
            39.5 MB, 71.35%
     */
    public int[] diStringMatch(String s) {

        int length = s.length();

        char[] chars = s.toCharArray();

        int low = 0;
        int top = length;

        int[] res = new int[length + 1];

        for (int i = 0; i < length; i++) {
            // 遇到 I 就取最小值，并将最小值自增
            if (chars[i] == 'I') {
                res[i] = low++;
            } else {
                // 取最大值，最大值减小
                res[i] = top--;
            }
        }
        // 取 low 或 high 均可
        res[length] = low;
        return res;
    }
}
