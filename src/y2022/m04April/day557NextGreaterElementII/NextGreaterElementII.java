package y2022.m04April.day557NextGreaterElementII;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author Rex Joush
 * @time 2022.04.17
 */

/*
    下一个更大元素 II
    https://leetcode.cn/problems/next-greater-element-ii/

    给定一个循环数组 nums (nums[nums.length - 1] 的下一个元素是 nums[0])，返回 nums 中每个元素的 下一个更大元素 。
    数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。

    示例 1:
        输入: nums = [1,2,1]
        输出: [2,-1,2]
        解释: 第一个 1 的下一个更大的数是 2；
             数字 2 找不到下一个更大的数；
             第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
    示例 2:
        输入: nums = [1,2,3,4,3]
        输出: [2,3,4,-1,4]

    提示:
        1 <= nums.length <= 10^4
        -10^9 <= nums[i] <= 10^9
 */
public class NextGreaterElementII {

    /*
        单调栈，维护一个单调递减栈
            当栈空，或者小于等于栈顶元素，那就入栈
            如果比栈顶元素大，那么就说明栈顶元素的最大值已经找到
            对于循环的问题，可以考虑将使用取模的操作
        结果：
            5 ms, 93.35%
            43.1 MB, 17.72%
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> deque = new ArrayDeque<>();
        // 取模循环即可
        for (int i = 0; i < n * 2 - 1; i++) {
            // 当比栈顶元素大时就找到了，将栈顶弹出进行计算
            while (!deque.isEmpty() && nums[i % n] > nums[deque.peek()]) {
                result[deque.pop()] = nums[i % n];
            }
            deque.push(i % n);
        }
        return result;
    }

}
