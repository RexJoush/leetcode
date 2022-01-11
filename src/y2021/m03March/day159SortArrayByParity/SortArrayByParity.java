package y2021.m03March.day159SortArrayByParity;

/**
 * @author Rex Joush
 * @time 2021.03.15
 */

/*
    按奇偶排序数组
    https://leetcode-cn.com/problems/sort-array-by-parity/

    给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
    你可以返回满足此条件的任何数组作为答案。

    示例：
        输入：[3,1,2,4]
        输出：[2,4,3,1]
        输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。

    提示：
        1 <= A.length <= 5000
        0 <= A[i] <= 5000

 */
public class SortArrayByParity {

    /*
        双指针法，前后各一个指针，遇到奇偶不对就交换
        结果：
            1 ms, 97.54%
            39.1 MB, 75.88%
     */
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 遇见前面奇数，后面偶数的就交换
            if (nums[left] % 2 != 0 && nums[right] % 2 == 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
            // 前面为偶数，符合条件，左针右移
            else if (nums[left] % 2 == 0) {
                left++;
            }
            // 后面为奇数，符合条件，右针左移
            else if (nums[right] % 2 != 0) {
                right--;
            }
            // 都符合，则左右针都移动
            else {
                left++;
                right--;
            }
        }
        return nums;
    }
}
