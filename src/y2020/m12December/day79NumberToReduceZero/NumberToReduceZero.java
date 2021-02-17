package y2020.m12December.day79NumberToReduceZero;

/**
 * @author Joush
 * @time 2020.12.25
 */

/*
    将数字变成 0 的操作次数
    https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/

    给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。

    示例 1：
        输入：num = 14
        输出：6
        解释：
        步骤 1) 14 是偶数，除以 2 得到 7 。
        步骤 2） 7 是奇数，减 1 得到 6 。
        步骤 3） 6 是偶数，除以 2 得到 3 。
        步骤 4） 3 是奇数，减 1 得到 2 。
        步骤 5） 2 是偶数，除以 2 得到 1 。
        步骤 6） 1 是奇数，减 1 得到 0 。
    示例 2：
        输入：num = 8
        输出：4
        解释：
        步骤 1） 8 是偶数，除以 2 得到 4 。
        步骤 2） 4 是偶数，除以 2 得到 2 。
        步骤 3） 2 是偶数，除以 2 得到 1 。
        步骤 4） 1 是奇数，减 1 得到 0 。
    示例 3：
        输入：num = 123
        输出：12

    提示：
        0 <= num <= 10^6

 */
public class NumberToReduceZero {

    /*
        向下削减即可。奇数就减一，偶数就折半
        结果：
            0 ms, 100.00%
            35.1 MB, 87.35%
     */
    public int numberOfSteps (int num) {
        int result = 0;

        while (num != 0){
            // 偶数折半
            if (num % 2 == 0){
                num /= 2;
            }
            // 奇数减一
            else {
                num--;
            }
            result++;
        }
        // 返回结果
        return result;
    }
}
