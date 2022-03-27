package y2022.m03March.day522SlidingWindowMaximum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @author Rex Joush
 * @time 2022.03.13
 */

/*
    滑动窗口最大值
    https://leetcode-cn.com/problems/sliding-window-maximum/

    给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    返回 滑动窗口中的最大值 。
    
    示例 1：
        输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        输出：[3,3,5,5,6,7]
        解释：
        滑动窗口的位置                最大值
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
         1 [3  -1  -3] 5  3  6  7       3
         1  3 [-1  -3  5] 3  6  7       5
         1  3  -1 [-3  5  3] 6  7       5
         1  3  -1  -3 [5  3  6] 7       6
         1  3  -1  -3  5 [3  6  7]      7
    示例 2：
        输入：nums = [1], k = 1
        输出：[1]
    
    提示：
        1 <= nums.length <= 105
        -104 <= nums[i] <= 104
        1 <= k <= nums.length

 */
public class SlidingWindowMaximum {

    /*
        双端队列
            刚开始遍历时，L 和 R 都为 0，有一个形成窗口的过程，此过程没有最大值，L 不动，R 向右移。
            当窗口大小形成时，L 和 R 一起向右移，
            每次移动时，判断队首的值的数组下标是否在 [L,R] 中，如果不在则需要弹出队首的值，
            当前窗口的最大值即为队首的数。

            具体过程如下：
                初始状态：L=R=0,队列:{}
                i=0,nums[0]=1。队列为空,直接加入。队列：{1}
                i=1,nums[1]=3。队尾值为1，3>1，弹出队尾值，加入3。队列：{3}
                i=2,nums[2]=-1。队尾值为3，-1<3，直接加入。队列：{3,-1}。此时窗口已经形成，L=0,R=2，result=[3]
                i=3,nums[3]=-3。队尾值为-1，-3<-1，直接加入。队列：{3,-1,-3}。队首3对应的下标为1，L=1,R=3，有效。result=[3,3]
                i=4,nums[4]=5。队尾值为-3，5>-3，依次弹出后加入。队列：{5}。此时L=2,R=4，有效。result=[3,3,5]
                i=5,nums[5]=3。队尾值为5，3<5，直接加入。队列：{5,3}。此时L=3,R=5，有效。result=[3,3,5,5]
                i=6,nums[6]=6。队尾值为3，6>3，依次弹出后加入。队列：{6}。此时L=4,R=6，有效。result=[3,3,5,5,6]
                i=7,nums[7]=7。队尾值为6，7>6，弹出队尾值后加入。队列：{7}。此时L=5,R=7，有效。result=[3,3,5,5,6,7]
        结果：
            29 ms, 82.12%
            57.8 MB, 31.20%
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int n = nums.length;
        // 双端队列
        Deque<Integer> deque = new ArrayDeque<>();
        // 结果数组
        int[] result = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            // 保证从小到大，前面数小，则一次弹出，直至满足要求
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            // 放入下标
            deque.addLast(i);
            // 计算左边界
            int left = i - k + 1;
            // 当前队首元素下标小于窗口左边界 left 时，从队首移除
            if (deque.peek() < left) {
                deque.pollFirst();
            }
            // 数组从0开始，当右边界 right + 1 大于等于窗口 k 时，形成了窗口，添加结果
            if (i + 1 >= k) {
                result[left] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
