package y2022.m02February.day492KthLargestElementInAnArray;

import java.util.Arrays;

/**
 * @author Rex Joush
 * @time 2022.02.11
 */

/*
    数组中的第K个最大元素
    https://leetcode-cn.com/problems/kth-largest-element-in-an-array/

    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

    示例 1:
        输入: [3,2,1,5,6,4] 和 k = 2
        输出: 5
    示例 2:
        输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
        输出: 4

    提示：
        1 <= k <= nums.length <= 10^4
        -10^4 <= nums[i] <= 10^4

 */
public class KthLargestElementInAnArray {

    /*
        排序，排完序后寻找即可
     */
    public int findKthLargest(int[] nums, int k) {

        Arrays.sort(nums);

        return nums[nums.length - k];
    }
}
