package y2022.m03March.day523LargestRectangleInHistogram;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Rex Joush
 * @time 2022.03.14
 */

/*
    柱状图中最大的矩形
    https://leetcode-cn.com/problems/largest-rectangle-in-histogram/

    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。

    示例 1:
        输入：heights = [2,1,5,6,2,3]
        输出：10
        解释：最大的矩形为图中红色区域，面积为 10
    示例 2：
        输入： heights = [2,4]
        输出： 4

    提示：
        1 <= heights.length <=10^5
        0 <= heights[i] <= 10^4

 */
public class LargestRectangleInHistogram {

    /*
        单调栈
     */
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> deque = new ArrayDeque<>();

        // 在数组两端加两个0，方便进行判断
        int[] temp = new int[heights.length + 2];
        //                原数组  原数组初始下标 目标数组 目标数组下标 拷贝的长度
        System.arraycopy(heights, 0, temp, 1, heights.length);
        int max = 0;
        for (int i = 0; i < temp.length; i++) {
            // 比当前大，就出栈，同时计算面积
            while (!deque.isEmpty() && temp[i] < temp[deque.peek()]) {
                int h = temp[deque.pop()];
                max = Math.max(max, (i - deque.peek() - 1) * h);
            }
            // 入栈，放下标
            deque.push(i);
        }
        return max;
    }
}
