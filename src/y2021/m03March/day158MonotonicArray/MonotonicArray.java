package y2021.m03March.day158MonotonicArray;

/**
 * @author Rex Joush
 * @time 2021.03.14
 */

/*
    单调数列
    https://leetcode-cn.com/problems/monotonic-array/

    如果数组是单调递增或单调递减的，那么它是单调的。
    如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
    当给定的数组 A 是单调数组时返回 true，否则返回 false。

    示例 1：
        输入：[1,2,2,3]
        输出：true
    示例 2：
        输入：[6,5,4,4]
        输出：true
    示例 3：
        输入：[1,3,2]
        输出：false
    示例 4：
        输入：[1,2,4,5]
        输出：true
    示例 5：
        输入：[1,1,1]
        输出：true

    提示：
        1 <= A.length <= 50000
        -100000 <= A[i] <= 100000

 */
public class MonotonicArray {

    /*
        遍历一次判断即可
        结果：
            1 ms, 100.00%
            50.5 MB, 84.59%
     */
    public boolean isMonotonic(int[] nums) {

        int length = nums.length;

        // 头尾判定，单调递增
        if (nums[0] < nums[length - 1]) {
            for (int i = 0; i < length - 1; i++) {
                if (nums[i + 1] < nums[i]) {
                    return false;
                }
            }
        }
        // 单调递减
        else {
            for (int i = 0; i < length - 1; i++) {
                if (nums[i + 1] > nums[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
