package y2021.m11November.day409NextGreaterElementI;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Rex Joush
 * @time 2021.11.20
 */

/*
    下一个更大元素 I
    https://leetcode-cn.com/problems/next-greater-element-i/

    给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
    请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
    nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。
    如果不存在，对应位置输出 -1 。

    示例 1:
        输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
        输出: [-1,3,-1]
        解释:
            对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
            对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
            对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
    示例 2:
        输入: nums1 = [2,4], nums2 = [1,2,3,4].
        输出: [3,-1]
        解释:
            对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
            对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。


    提示：
        1 <= nums1.length <= nums2.length <= 1000
        0 <= nums1[i], nums2[i] <= 10^4
        nums1 和 nums2 中所有整数 互不相同
        nums1 中的所有整数同样出现在 nums2 中

 */
public class NextGreaterElementI {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        new NextGreaterElementI().nextGreaterElement(nums1, nums2);
    }

    /*
        单调栈，适用于解决 next great number 类问题
        结果：
            4 ms, 48.64%
            38.6 MB, 46.84%
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        // 从后往前遍历 nums2, 找到每个元素对应的右边第一个较大的值，通过单调栈的思想
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            // 如果当前栈里的元素比当前队列的元素小，那么直接出栈
            while (!stack.empty() && num > stack.peek()) {
                stack.pop();
            }
            // 将当前元素最大值放入哈希表中，最后计算结果使用
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            // 将当前的最大元素入栈
            stack.push(num);
        }

        // 获取 nums1 中每个元素的右边第一个较大的值
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }

}
