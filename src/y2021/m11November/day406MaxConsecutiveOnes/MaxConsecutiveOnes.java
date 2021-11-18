package y2021.m11November.day406MaxConsecutiveOnes;

/**
 * @author Rex Joush
 * @time 2021.11.17
 */

/*
    最大连续 1 的个数
    https://leetcode-cn.com/problems/max-consecutive-ones/

    给定一个二进制数组， 计算其中最大连续 1 的个数。

    示例：
        输入：[1,1,0,1,1,1]
        输出：3
        解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.

    提示：
        输入的数组只包含 0 和 1 。
        输入数组的长度是正整数，且不超过 10,000。

 */
public class MaxConsecutiveOnes {

    /*
        遍历计数即可
        结果：
            2 ms, 57.33%
            39.9 MB, 16.64%
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int max = 0;
        for (int num : nums) {
            // 如果当前是 1，则 count++，如果当前 count > max 则更换
            if (num == 1) {
                count++;
                if (count > max) {
                    max = count;
                }
            }
            // 如果遇到 0，则 count 置为 0
            else {
                count = 0;
            }
        }

        return max;
    }

}
