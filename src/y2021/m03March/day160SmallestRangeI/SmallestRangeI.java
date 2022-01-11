package y2021.m03March.day160SmallestRangeI;

/**
 * @author Rex Joush
 * @time 2021.03.16
 */

/*
    最小差值 I
    https://leetcode-cn.com/problems/smallest-range-i/

    给你一个整数数组 nums，请你给数组中的每个元素 nums[i] 都加上一个任意数字 x （-k <= x <= k），从而得到一个新数组 result 。
    返回数组 result 的最大值和最小值之间可能存在的最小差值。

    示例 1：
        输入：nums = [1], k = 0
        输出：0
        解释：result = [1]
    示例 2：
        输入：nums = [0,10], k = 2
        输出：6
        解释：result = [2,8]
    示例 3：
        输入：nums = [1,3,6], k = 3
        输出：0
        解释：result = [3,3,3] or result = [4,4,4]

    提示：
        1 <= nums.length <= 10000
        0 <= nums[i] <= 10000
        0 <= k <= 10000

 */
public class SmallestRangeI {

    /*
        找到原数组中的最大差值 n，如果此差值超过 2 * k 那么最小差值为 n - 2 * k, 如果为超过，则差值为 0
        结果：
            3 ms, 71.81%
            38.6 MB, 77.91%
     */
    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0], max = nums[0];
        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return Math.max(0, max - min - 2 * k);
    }
}
