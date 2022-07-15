package y2022.m04April.day562ShortestUnsortedContinuousSubarray;

/**
 * @author Rex Joush
 * @time 2022.04.22
 */

/*
    最短无序连续子数组
    https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
    
    给你一个整数数组 nums ，你需要找出一个 连续子数组，
    如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
    请你找出符合题意的 最短 子数组，并输出它的长度。

    示例 1：
        输入：nums = [2,6,4,8,10,9,15]
        输出：5
        解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
    示例 2：
        输入：nums = [1,2,3,4]
        输出：0
    示例 3：
        输入：nums = [1]
        输出：0

    提示：
        1 <= nums.length <= 10^4
        -10^5 <= nums[i] <= 10^5

 */
public class ShortestUnsortedContinuousSubarray {

    /*
        从左往右找到第一个不满足递增的值，即为需要排序的右边界
        从右往左找到第一个不满足递增的值，即为需要排序的左边界
        结果：
            1 ms, 91.69%
            42.1 MB, 46.72%
     */
    // 2,6,4,8,10,9,15
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int to = -1;
        // 从左向右找到第一个不满足单调增的值，即为右边界
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < max) {
                to = i;
            }
            max = Math.max(max, nums[i]);
        }

        int min = Integer.MAX_VALUE;
        int from = nums.length;

        // 从右向左找到第一个不满足的值，即为左边界
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > min) {
                from = i;
            }
            min = Math.min(min, nums[i]);
        }

        // 已经有序
        if (to == -1) {
            return 0;
        }

        return to - from + 1;
    }
}
