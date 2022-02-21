package y2022.m01January.day480SearchInRotatedSortedArray;

/**
 * @author Rex Joush
 * @time 2022.01.30
 */

/*
    搜索旋转排序数组
    https://leetcode-cn.com/problems/search-in-rotated-sorted-array/

    整数数组 nums 按升序排列，数组中的值 互不相同 。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
    使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。

    示例 1：
        输入：nums = [4,5,6,7,0,1,2], target = 0
        输出：4
    示例2：
        输入：nums = [4,5,6,7,0,1,2], target = 3
        输出：-1
    示例 3：
        输入：nums = [1], target = 0
        输出：-1

    提示：
        1 <= nums.length <= 5000
        -10^4 <= nums[i] <= 10^4
        nums 中的每个值都 独一无二
        题目数据保证 nums 在预先未知的某个下标上进行了旋转
        -10^4 <= target <= 10^4

 */
public class SearchInRotatedSortedArray {

    /*
        数组被旋转之后，从某个位置开始，分成了两段递增序列。
        因此，当数组从中间一分为二时，必定会出现一个数组递增有序，另一个数组可能有序，可能无序
        有序部分使用二分法查找，无需部分继续本方法划分两个数组即可。
        结果：
            0 ms, 100.00%
            41.4 MB, 6.54%
     */
    public int search(int[] nums, int target) {

        int n = nums.length;

        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            /*
                target 和 nums[0] 比较即可
                    target = 5
                    nums = [4, 5, 6, 7, 0, 1, 2, 3]
                target < nums[0], 说明目标值在 [mid, right] 中
                target > nums[0], 说明目标值在 [left, mid] 中
             */
            // 说明前半段是升序的
            if (nums[0] <= nums[mid]) {
                // 说明目标值在后半段，继续在后半段划分
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // 目标值在前半段，前半段继续划分
                    left = mid + 1;
                }
            }
            // 后半段升序
            else {
                // 目标值在后半段，且后半段升序，直接二分即可。
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                }
                // 目标值在前半段，但前半段无序，则前半段继续划分
                else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
