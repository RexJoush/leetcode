package y2021.m03March.day170LargestPerimeterTriangle;

import java.util.Arrays;

/**
 * @author Rex Joush
 * @time 2021.03.26
 */

/*
    三角形的最大周长
    https://leetcode-cn.com/problems/largest-perimeter-triangle/

    给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
    如果不能形成任何面积不为零的三角形，返回 0。

    示例 1：
        输入：[2,1,2]
        输出：5
    示例 2：
        输入：[1,2,1]
        输出：0
    示例 3：
        输入：[3,2,3,4]
        输出：10
    示例 4：
        输入：[3,6,2,3]
        输出：8

    提示：
        3 <= A.length <= 10000
        1 <= A[i] <= 10^6

 */
public class LargestPerimeterTriangle {

    /*
        排序，从后向前进行判断，找到最大的能组成三角形的三个边即可
        结果：
            8 ms, 99.76%
            39.1 MB, 33.77%
     */
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {
            // 两较小边之和大于第三边即可
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }
        // 没有满足的，返回 0
        return 0;
    }
}
