package y2021.m11November.day402MinimumMovesToEqualArrayElements;

/**
 * @author Rex Joush
 * @time 2021.11.13
 */

/*
    最小操作次数使数组元素相等
    https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/

    给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1。
    返回让数组所有元素相等的最小操作次数。

    示例 1：
        输入：nums = [1,2,3]
        输出：3
        解释： 只需要 3 次操作（注意每次操作会增加两个元素的值）：
              [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
    示例 2：
        输入：nums = [1,1,1]
        输出：0

    提示：
        n == nums.length
        1 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9
        答案保证符合 32-bit 整数

 */
public class MinimumMovesToEqualArrayElements {

    /*
        n - 1 个元素增加 1，相当于某个元素减 1，所以计算所有元素和最小值差的和即可
        结果：
            1 ms, 100.00%
            38.9 MB, 63.54%
     */
    public int minMoves(int[] nums) {

        int result = 0;

        int min = Integer.MAX_VALUE;
        // 找到最小值
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        // 计算每个元素与最小值的差的和
        for (int num : nums) {
            result += (num - min);
        }
        return result;
    }

}
