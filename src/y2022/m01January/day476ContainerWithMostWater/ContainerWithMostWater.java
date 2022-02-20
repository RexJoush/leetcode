package y2022.m01January.day476ContainerWithMostWater;

/**
 * @author Rex Joush
 * @time 2022.01.26
 */

/*
    盛最多水的容器
    https://leetcode-cn.com/problems/container-with-most-water/

    给定一个长度为 n 的整数数组 height。有 n 条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
    找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    返回容器可以储存的最大水量。
    说明：你不能倾斜容器。

    示例 1：
        输入：[1,8,6,2,5,4,8,3,7]
        输出：49
        解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
    示例 2：
        输入：height = [1,1]
        输出：1

    提示：
        n == height.length
        2 <= n <= 10^5
        0 <= height[i] <= 10^4

 */
public class ContainerWithMostWater {

    /*
        双指针法
        最大的存水容量即为最大的矩形面积，示例 1 的 49 即为最大的正方形，7 * 7 得到
        考虑指针 i = 0, j = n 从两边往中间靠拢
            计算 (j - i) * min(height[i], height[j])，同时记录最大值
        之后将指针由较小的一个往中间移动，反复进行，计算此过程中的最大值即可
        结果：
            3 ms, 92.93%
            51.3 MB, 80.93%
     */
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j) {
            // 计算结果，并移动较小的指针
            if (height[i] < height[j]) {
                max = Math.max(max, (j - i) * height[i]);
                i++;
            } else {
                max = Math.max(max, (j - i) * height[j]);
                j--;
            }
        }
        return max;
    }
}
