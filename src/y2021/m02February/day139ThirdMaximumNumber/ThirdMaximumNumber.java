package y2021.m02February.day139ThirdMaximumNumber;

/**
 * @author Rex Joush
 * @time 2021.02.23
 */

/*
    第三大的数
    https://leetcode-cn.com/problems/third-maximum-number/

    给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。

    示例 1：
        输入：[3, 2, 1]
        输出：1
        解释：第三大的数是 1 。
    示例 2：
        输入：[1, 2]
        输出：2
        解释：第三大的数不存在, 所以返回最大的数 2 。
    示例 3：
        输入：[2, 2, 3, 1]
        输出：1
        解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
             此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。

    提示：
        1 <= nums.length <= 10^4
        -2^31 <= nums[i] <= 2^31 - 1

 */
public class ThirdMaximumNumber {

    /*
        遍历一遍，维护最大的三个值即可，
        需注意，最开始初始化时，需将值初始化为 long 的最小值，
        integer 的最小值过不去第 27/31 个测试用例 [1, 2, -2147483648]
        结果：
            0 ms, 100.00%
            38.2 MB, 60.31%
     */
    public int thirdMax(int[] nums) {

        long max = Long.MIN_VALUE;
        long secondMax = Long.MIN_VALUE;
        long thirdMax = Long.MIN_VALUE;

        for (int num : nums) {
            if (num > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = num;
            } else if (num > secondMax) {
                if (num != max) {
                    thirdMax = secondMax;
                    secondMax = num;
                }
            } else if (num > thirdMax) {
                if (num != secondMax) {
                    thirdMax = num;
                }
            } else if (num == thirdMax) {
                thirdMax = num;
            }
        }
        return thirdMax == Long.MIN_VALUE ? (int) max : (int) thirdMax;
    }

}
