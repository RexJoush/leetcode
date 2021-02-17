package y2020.m12December.day84SumOfAllOddLengthSubarray;

/**
 * @author Joush
 * @time 2020.12.30
 */

/*
    所有奇数长度子数组的和
    https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays/

    给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
    子数组 定义为原数组中的一个连续子序列。
    请你返回 arr 中 所有奇数长度子数组的和 。

    示例 1：
        输入：arr = [1,4,2,5,3]
        输出：58
        解释：所有奇数长度子数组和它们的和为：
        [1] = 1
        [4] = 4
        [2] = 2
        [5] = 5
        [3] = 3
        [1,4,2] = 7
        [4,2,5] = 11
        [2,5,3] = 10
        [1,4,2,5,3] = 15
        我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
    示例 2：
        输入：arr = [1,2]
        输出：3
        解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
    示例 3：
        输入：arr = [10,11,12]
        输出：66

    提示：
        1 <= arr.length <= 100
        1 <= arr[i] <= 1000

 */
public class SumOfAllOddLengthSubarray {

    /*
        计算每个数字出现的次数
        将每个数字分为左右两侧，左侧和右侧分别取对应长度个数字，即该数字出现的次数
        举例，3个数字时，左侧取0个，右侧取2个。左侧取 1 个，右侧取 1 个，左侧取 2 个，右侧取 0 个。
            即，left = i+1 种情况。右边元素个数为 len - (i + 1) 个同理可得， 右边为 right = len - (i + 1) + 1
        左边取得元素个数 0,1,2，则只有1为奇数，左边取奇数个元素的方式 left_odd = left / 2;
        偶数为 0 或 2，可得左边取偶数个元素的方式 left_even = (left + 1) / 2;

        同理右边取奇数个元素的方式 right_odd = right / 2;
        右边取偶数个元素的方式 right_even = (right + 1) / 2;

        左右各取奇数或左右各取偶数时加上自身构成奇数，注意，包含左右各取 0 个
        结果：
            0 ms, 100.00%
            36 MB, 81.53%

    */
    public int sumOddLengthSubarrays(int[] arr) {
        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            // 左侧可取的元素个数
            int left = i + 1;
            // 右侧可取的元素个数
            int right = arr.length - (i + 1) + 1;

            // 左侧的奇偶数可取的个数
            int left_odd = left / 2;
            int left_even = (left + 1) / 2;

            // 右侧的奇偶数可取个数
            int right_odd = right / 2;
            int right_even = (right + 1) / 2;

            // 计算值，分别计算左右奇数次和偶数次的乘积
            result += arr[i] * (left_odd * right_odd + left_even * right_even);

        }

        return result;
    }

}
