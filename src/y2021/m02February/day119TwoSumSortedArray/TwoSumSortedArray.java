package y2021.m02February.day119TwoSumSortedArray;

import java.util.Arrays;

/**
 * @author Joush
 * @time 2021.02.03
 */

/*
    两数之和 II - 输入有序数组
    https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/

    给定一个已按照 升序排列 的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
    函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
    所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length。
    你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。

    示例 1：
        输入：numbers = [2,7,11,15], target = 9
        输出：[1,2]
        解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
    示例 2：
        输入：numbers = [2,3,4], target = 6
        输出：[1,3]
    示例 3：
        输入：numbers = [-1,0], target = -1
        输出：[1,2]

    提示：
        2 <= numbers.length <= 3 * 104
        -1000 <= numbers[i] <= 1000
        numbers 按 递增顺序 排列
        -1000 <= target <= 1000
        仅存在一个有效答案
 */

public class TwoSumSortedArray {

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 3, 5};
        System.out.println(Arrays.toString(new TwoSumSortedArray().twoSum2(numbers, 5)));
    }

    /*
        双指针法
        起始指针位于两端，求和之后，如果 > target，右针左移，< target，左针右移，遍历一遍即可
        结果：
            1 ms, 92.66%
            38.6 MB, 68.40%
     */
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    /*
        遍历即可，确定第一个值，第二个值用二分法
        结果：
            4 ms, 23.62%
            38.9 MB, 14.83%
     */
    public int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            int left = i + 1;
            int right = numbers.length - 1;

            // 第二个值二分查找即可
            while (left <= right) {
                int mid = (left + right) / 2 + left;
                if (numbers[i] + numbers[mid] == target) {
                    // 找到的时候返回结果
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] + numbers[i] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
}
