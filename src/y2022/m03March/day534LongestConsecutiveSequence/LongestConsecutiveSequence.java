package y2022.m03March.day534LongestConsecutiveSequence;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2022.03.25
 */

/*
    最长连续序列
    https://leetcode-cn.com/problems/longest-consecutive-sequence/

    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

    示例 1：
        输入：nums = [100,4,200,1,3,2]
        输出：4
        解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
    示例 2：
        输入：nums = [0,3,7,2,5,8,4,6,0,1]
        输出：9

    提示：
        0 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9

 */
public class LongestConsecutiveSequence {

    /*
        动态规划
            定义一个map，记录以 num 起始的最长的连续序列
     */
    public int longestConsecutive(int[] nums) {
        // 定义哈希表，提高查找效率，并去重
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : set) {
            // 如果当前数字的前一个数字不存在，那么当前数字的最长为 1
            if (!set.contains(num - 1)) {
                int curr = num;
                int currentStack = 1;

                // 判断当前数字的后续数字在不在集合中，知道不在停止循环，更新最大值
                while (set.contains(curr + 1)) {
                    curr++;
                    currentStack++;
                }
                max = Math.max(max, currentStack);
            }
        }
        return max;
    }
}
