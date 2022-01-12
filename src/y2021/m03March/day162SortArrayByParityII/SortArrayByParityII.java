package y2021.m03March.day162SortArrayByParityII;

/**
 * @author Rex Joush
 * @time 2021.03.18
 */

/*
    按奇偶排序数组 II
    https://leetcode-cn.com/problems/sort-array-by-parity-ii/

    给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
    对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
    你可以返回任何满足上述条件的数组作为答案。

    示例：
        输入：[4,2,5,7]
        输出：[4,5,2,7]
        解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。

    提示：
        2 <= A.length <= 20000
        A.length % 2 == 0
        0 <= A[i] <= 1000

 */
public class SortArrayByParityII {

    /*
        双指针法，奇数指针和偶数指针分别向后走，遇见某个不对就交换两个数的位置即可
        结果：
            2 ms, 98.39%
            39.7 MB, 60.82%
     */
    public int[] sortArrayByParityII(int[] nums) {
        int odd = 1; // 奇数

        // 遍历完成即可
        for (int i = 0; i < nums.length; i += 2) {
            // 如果当前为奇数，那就移动一个指针，直到碰见偶数，将两个值交换
            if (nums[i] % 2 == 1) {
                // 碰见另一个偶数
                while (nums[odd] % 2 == 1) {
                    odd += 2;
                }
                // 交换
                swap(nums, i, odd);
            }
        }
        return nums;
    }

    /*
        交换两个数的位置
     */
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
