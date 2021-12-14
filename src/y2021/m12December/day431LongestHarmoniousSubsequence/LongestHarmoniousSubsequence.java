package y2021.m12December.day431LongestHarmoniousSubsequence;

import java.util.HashMap;

/**
 * @author Rex Joush
 * @time 2021.12.12
 */

/*
    最长和谐子序列
    https://leetcode-cn.com/problems/longest-harmonious-subsequence/

    和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
    现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
    数组的子序列是一个由数组派生出来的序列，
    它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。

    示例 1：
        输入：nums = [1,3,2,2,5,2,3,7]
        输出：5
        解释：最长的和谐子序列是 [3,2,2,2,3]
    示例 2：
        输入：nums = [1,2,3,4]
        输出：2
    示例 3：
        输入：nums = [1,1,1,1]
        输出：0

    提示：
        1 <= nums.length <= 2 * 10^4
        -10^9 <= nums[i] <= 10^9
 */
public class LongestHarmoniousSubsequence {

    /*
        统计所有数字出现的次数，加入 map 中
        统计所有的 x, x + 1 个数的最大值即可
        结果：
            15 ms, 66.55%
            38.9 MB, 94.73%
     */
    public int findLHS(int[] nums) {
        HashMap <Integer, Integer> cnt = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        for (int key : cnt.keySet()) {
            if (cnt.containsKey(key + 1)) {
                res = Math.max(res, cnt.get(key) + cnt.get(key + 1));
            }
        }
        return res;
    }

}
