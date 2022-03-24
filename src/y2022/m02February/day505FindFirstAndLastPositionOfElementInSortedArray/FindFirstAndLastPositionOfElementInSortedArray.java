package y2022.m02February.day505FindFirstAndLastPositionOfElementInSortedArray;

/**
 * @author Rex Joush
 * @time 2022.02.24
 */

/*
    在排序数组中查找元素的第一个和最后一个位置
    https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/

    给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
    如果数组中不存在目标值 target，返回 [-1, -1]。

    进阶：
        你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？、

    示例 1：
        输：nums = [5,7,7,8,8,10], target = 8
        输出：[3,4]
    示例 2：
        输入：nums = [5,7,7,8,8,10], target = 6
        输出：[-1,-1]
    示例 3：
        输入：nums = [], target = 0
        输出：[-1,-1]

    提示：
        0 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9
        nums 是一个非递减数组
        -10^9 <= target <= 10^9

 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    /*
        方法二：二分法查找左右两个值
        考虑，target 开始和结束的位置，其实是我们要找的数组中第一个等于 target 位置和第一个大于 target 的位置 - 1

     */
    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        // 找到左边界，此处目标值为 target - 1
        int left = binarySearch2(nums, target - 1);
        // 找到右边界，为结束位置 - 1
        int right = binarySearch2(nums, target) - 1;

        if (left <= right && nums[left] == target) {
            return new int[]{left, right};
        }
        // 返回结果
        return new int[]{-1, -1};
    }

    // 寻找第一个大于 target 的数的下标
    private int binarySearch2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 在左区间
            if (nums[mid] > target) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    // 找到第一个值
    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            // 即左边界在右侧区间
            if (nums[mid] < target) {
                left = mid + 1;
            }
            // 左边界在左侧区间
            else if (nums[mid] == target) {
                right = mid;
            }
            // 此时，nums[mid] > target，即在左侧区间
            else {
                right = mid - 1;
            }
        }
        // 需要判断左边界是否存在
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    // 找到第二个值
    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 上取整
            int mid = (left + right + 1) / 2;

            // 即右边界在右侧区间
            if (nums[mid] < target) {
                left = mid + 1;
            }
            // 左边界在左侧区间
            else if (nums[mid] == target) {
                left = mid;
            }
            // 此时，nums[mid] > target，即在左侧区间
            else {
                right = mid - 1;
            }
        }

        return left;
    }

    /*
        方法一：通过二分法查到其中一个数字的位置，中心扩散
        结果：
            0 ms, 100.00%
            44.7 MB, 9.09%
     */
    public int[] searchRange(int[] nums, int target) {

        int left = -1;
        int right = -1;

        int flag = binarySearch(nums, target);
        if (flag != -1) {
            int i = flag - 1;
            while (i >= 0 && nums[i] == target) {
                i--;
            }
            left = i + 1;
            i = flag + 1;
            while (i < nums.length && nums[i] == target) {
                i++;
            }
            right = i - 1;
        }
        return new int[]{left, right};
    }

    // 二分查到一个
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
