package January21.day89SplitStringInBalanced;

/**
 * @author Joush
 * @time 2021.01.04
 */

/*
    分割平衡字符串
    https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/

    在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
    给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
    返回可以通过分割得到的平衡字符串的最大数量。

    示例 1：
        输入：s = "RLRRLLRLRL"
        输出：4
        解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
    示例 2：
        输入：s = "RLLLLRRRLR"
        输出：3
        解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
    示例 3：
        输入：s = "LLLLRRRR"
        输出：1
        解释：s 只能保持原样 "LLLLRRRR".

    提示：
        1 <= s.length <= 1000
        s[i] = 'L' 或 'R'
        分割得到的每个字符串都必须是平衡字符串。

 */
public class SplitStringInBalanced {

    /*
        遇见R就将R++，L就将L++，如果两个相等且不为0，则结果自增
        结果：
            0 ms, 100.00%
            36.2 MB, 73.86%
     */
    public int balancedStringSplit(String s) {

        char[] arr = s.toCharArray();
        int numR = 0;
        int numL = 0;
        int result = 0;
        for(char ar :arr){
            if(ar == 'R'){
                numR++;
            }else if(ar == 'L'){
                numL++;
            }
            if(numR == numL && numR != 0){
                result++;
            }
        }
        return result;

    }

}
