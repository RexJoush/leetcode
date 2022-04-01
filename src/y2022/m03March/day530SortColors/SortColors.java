package y2022.m03March.day530SortColors;

/**
 * @author Rex Joush
 * @time 2022.03.21
 */

/*
    颜色分类
    https://leetcode-cn.com/problems/sort-colors/

    给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    必须在不使用库的sort函数的情况下解决这个问题。

    示例 1：
        输入：nums = [2,0,2,1,1,0]
        输出：[0,0,1,1,2,2]
    示例 2：
        输入：nums = [2,0,1]
        输出：[0,1,2]

    提示：
        n == nums.length
        1 <= n <= 300
        nums[i] 为 0、1 或 2

    进阶：
        你可以不使用代码库中的排序函数来解决这道题吗？
        你能想出一个仅使用常数空间的一趟扫描算法吗？

 */
public class SortColors {

    /*
        方法二：一趟扫描
        结果：
            0 ms, 100.00%
            40 MB, 19.24%
     */
    public void sortColors2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i <= right; i++) {
            // 当前数字是 2 时，与结尾交换，结尾往前移动
            while (i <= right && nums[i] == 2) {
                swap(nums, i, right);
                right--;
            }
            // 当前时 0，与首指针交换，首指针后移
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
            }
        }
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /*
        方法一：扫描两遍
            扫描一遍，将所有的 2 移动到最右端
            再来一遍，从最左边的 2 开始，挪动 1 和 0，将 0 移动到最前面，1 往后移动
        结果：
            0 ms, 100.00%
            39.7 MB, 52.85%
     */
    public void sortColors(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 从后往前找到第一个不为 2 的值
            while (left < right && nums[right] == 2) {
                right--;
            }
            // 从前往后找到第一个 2
            while (left < right && nums[left] != 2) {
                left++;
            }
            // 交换即可
            if (left < right) {
                swap(nums, left, right);
            }
        }
        // 此时，right 就处于最左的 2 的前一个，把 left 置为 0 即可，继续此过程
        left = 0;
        while (left < right) {
            while (left < right && nums[right] == 1) {
                right--;
            }
            while (left < right && nums[left] != 1) {
                left++;
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
    }
}
