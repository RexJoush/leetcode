package y2022.m03March.day531FirstMissingPositive;

/**
 * @author Rex Joush
 * @time 2022.03.22
 */

/*
    缺失的第一个正数
    https://leetcode-cn.com/problems/first-missing-positive/

    给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
    请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

    示例 1：
        输入：nums = [1,2,0]
        输出：3
    示例 2：
        输入：nums = [3,4,-1,1]
        输出：2
    示例 3：
        输入：nums = [7,8,9,11,12]
        输出：1

    提示：
        1 <= nums.length <= 5 * 105
        -231 <= nums[i] <= 231 - 1

 */
public class FirstMissingPositive {

    /*
        遍历一遍，将在 1-n 范围内的值，放到对应下标的位置，即数组第一个值为 1 ，第二个值为 2
        在遍历一遍，与下标不对应的即为第一个最小的正整数
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            // 如果已经在正确的位置上，就后移
            if (nums[i] == i + 1) {
                i++;
                continue;
            }
            // 在正确的范围内，放到正确的位置上
            if (nums[i] > 0 && nums[i] < n) {
                // 如果出现了 nums[i] = i = nums[nums[i]], 此处特殊处理
                // 即样例 [1,1], 会无限交换
                if (nums[i] == nums[nums[i] - 1]) {
                    i++;
                    continue;
                }
                // 交换即可
                swap(nums, nums[i] - 1, i);
                continue;
            }
            i++;
        }
        // 如果与下标不对应，就返回
        for(int index = 0; index < n; index++) {
            if (nums[index] != index + 1) {
                return index + 1;
            }
        }
        // 全部对应，返回 n + 1
        return n + 1;
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
