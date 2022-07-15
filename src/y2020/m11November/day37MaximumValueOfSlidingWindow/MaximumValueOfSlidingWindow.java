package y2020.m11November.day37MaximumValueOfSlidingWindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Joush
 * @time 2020.11.13
 */

/*
    滑动窗口的最大值（剑指 Offer 59 - I）
    https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/

    给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

    示例:
        输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
        输出: [3,3,5,5,6,7]
        解释:
          滑动窗口的位置                最大值
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
         1 [3  -1  -3] 5  3  6  7       3
         1  3 [-1  -3  5] 3  6  7       5
         1  3  -1 [-3  5  3] 6  7       5
         1  3  -1  -3 [5  3  6] 7       6
         1  3  -1  -3  5 [3  6  7]      7

    提示：
        你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

 */
public class MaximumValueOfSlidingWindow {

    /*
        双端队列，维护一个双端队列，保证队头元素是当前队列中的最大值，队尾元素是当前队列的最小值
        而队中的元素就是当前窗口内的值，所以每次拿到最大值只需要获取队头元素即可。
    */
    public int[] maxSlidingWindow2(int[] nums, int k) {

        int index = 0;
        // 定义结果数组
        int[] result = new int[nums.length - k + 1];

        // 定义双端队列
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {

            // 在队列不空的情况下，如果队列尾部元素比当前元素小或等于当前元素，那么让尾部元素弹出，直到弹出所有小于当前值的元素为止
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            // 此时说明当前元素比队列尾部元素小，直接加入队尾即可，注意，存的是索引值
            deque.addLast(i);

            // 此时是窗口已经滑过了队头元素，则将头部元素弹出
            if (deque.peekFirst() == (i - k)) {
                deque.pollFirst();
            }

            // 查看是否形成窗口，只有形成了大小为k的窗口，就收集最大值
            if (i >= (k - 1)) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;

    }

    /*
        暴力搜索，找到每个滑动窗口的最大值返回即可
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        // 数组为0，直接返回
        if (k == 0) {
            return new int[]{};
        }
        // 如果 k == 1,则返回当前数组
        if (k == 1) {
            return nums;
        }

        // 记录结果数组的索引
        int index = 0;
        // 左指针
        int left = 0;
        // 右指针
        int right = left + k;
        // 定义结果数组
        int[] result = new int[nums.length - k + 1];
        // 去最大值为 Integer 最小
        int max = Integer.MIN_VALUE;

        // 当右指针到达末尾时结束
        while (right <= nums.length) {

            // 寻找左指针到右指针之间的最大值
            for (int i = left; i < right; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
            }

            // 记录结果数组
            result[index++] = max;
            // 指针右移，最大值置空
            left++;
            right++;
            max = Integer.MIN_VALUE;

        }
        // 返回结果
        return result;

    }

}
