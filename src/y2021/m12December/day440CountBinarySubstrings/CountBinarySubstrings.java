package y2021.m12December.day440CountBinarySubstrings;

/**
 * @author Rex Joush
 * @time 2021.12.21
 */

/*
    计数二进制子串
    https://leetcode-cn.com/problems/count-binary-substrings/

    给定一个字符串 s，统计并返回具有相同数量 0 和 1 的非空（连续）子字符串的数量，
    并且这些子字符串中的所有 0 和所有 1 都是成组连续的。
    重复出现（不同位置）的子串也要统计它们出现的次数。
     
    示例 1：
        输入：s = "00110011"
        输出：6
        解释：6 个子串满足具有相同数量的连续 1 和 0 ："0011"、"01"、"1100"、"10"、"0011" 和 "01" 。
        注意，一些重复出现的子串（不同位置）要统计它们出现的次数。
        另外，"00110011" 不是有效的子串，因为所有的 0（还有 1 ）没有组合在一起。
    示例 2：
        输入：s = "10101"
        输出：4
        解释：有 4 个子串："10"、"01"、"10"、"01" ，具有相同数量的连续 1 和 0 。
    
    提示：
        1 <= s.length <= 10^5
        s[i] 为 '0' 或 '1'

 */
public class CountBinarySubstrings {

    /*
        遍历数组即可，查到每次 0 1 交替时的个数，计算即可
        结果：
            11 ms, 56.00%
            39.8 MB, 69.22%
     */
    public int countBinarySubstrings(String s) {

        int last = 0;
        int result = 0;
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            int count = 0;
            // 当前字符未改变，就一直计数
            while (index < s.length() && s.charAt(index) == c) {
                count++;
                index++;
            }
            // 结果加上字符串的个数
            /*
                此处的意思是，当前 1 的个数为 3, 前面 0 的个数为 4, 即 0000111
                那么可得，符合条件的字串为 000111,0011,01
                即 min(count, last);
             */
            result += Math.min(count, last);
            // 更新当前的个数
            last = count;
        }
        return result;
    }
}
