package y2021.m12December.day436MaximumProductOfThreeNumbers;

import java.util.Arrays;

/**
 * @author Rex Joush
 * @time 2021.12.17
 */

/*
    三个数的最大乘积
    https://leetcode-cn.com/problems/maximum-product-of-three-numbers/

    给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

    示例 1：
        输入：nums = [1,2,3]
        输出：6
    示例 2：
        输入：nums = [1,2,3,4]
        输出：24
    示例 3：
        输入：nums = [-1,-2,-3]
        输出：-6

    提示：
        3 <= nums.length <= 10^4
        -1000 <= nums[i] <= 1000

 */
public class MaximumProductOfThreeNumbers {

    /*
        方法一：排序
            找到最大的三位数乘积即可，最大值可能出现在以下两种情况中
            a < b < c < ··· < d < e < f
            1. a * b * f
            2. d * e * f
        结果：
            12 ms, 14.11%
            40 MB, 23.25%
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }

    /*
        方法二：遍历
            由方法一可知，只需找到最大的三个数和最小的三个数即可。所以无需排序遍历一遍即可
        结果：
            2 ms, 99.35%
            39.8 MB, 71.96%
     */
    public int maximumProduct2(int[] nums) {

        // 两个最小值
        int minFirst = Integer.MAX_VALUE;
        int minSecond = Integer.MAX_VALUE;
        // 三个最大值
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int num : nums) {
            // 更新最大值
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second) {
                third = second;
                second = num;
            } else if (num > third) {
                third = num;
            }

            // 更新最小值
            if (num < minFirst) {
                minSecond = minFirst;
                minFirst = num;
            } else if (num < minSecond) {
                minSecond = num;
            }
        }
        // 返回结果
        return Math.max(minFirst * minSecond * first, first * second * third);
    }
}
