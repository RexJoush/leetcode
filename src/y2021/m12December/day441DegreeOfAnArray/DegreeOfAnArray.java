package y2021.m12December.day441DegreeOfAnArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2021.12.22
 */

/*
    数组的度
    https://leetcode-cn.com/problems/degree-of-an-array/

    给定一个非空且只包含非负数的整数数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。
    你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

    示例 1：
        输入：nums = [1,2,2,3,1]
        输出：2
        解释：
            输入数组的度是 2 ，因为元素 1 和 2 的出现频数最大，均为 2 。
            连续子数组里面拥有相同度的有如下所示：
            [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
            最短连续子数组 [2, 2] 的长度为 2 ，所以返回 2 。
    示例 2
        输入：nums = [1,2,2,3,1,4,2]
        输出：6
        解释：
            数组的度是 3 ，因为元素 2 重复出现 3 次。
            所以 [2,2,3,1,4,2] 是最短子数组，因此返回 6 。

    提示：
        nums.length 在 1 到 50,000 范围内。
        nums[i] 是一个在 0 到 49,999 范围内的整数。

 */
public class DegreeOfAnArray {

    /*
        记录原数组中出现次数最多的值，并找到和原数组相同的最短子数组，那么该子数组必然包含全部的此元素
        且两端恰为此元素的第一次出现和最后一次出现的位置。
        因为符合条件的可能有多个，所以，需要记录最多的出现次数和最短的相差距离，使用哈希表
        结果：
            13 ms, 80.64%
            42.7 MB, 69.82%
     */
    public int findShortestSubArray(int[] nums) {

        Map<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                // 更新个数
                map.get(nums[i])[0]++;
                // 更新结束索引
                map.get(nums[i])[2] = i;
            } else {
                // 加入集合，元素 1 的个数，起始索引，结束索引
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int max = 0;
        int minLen = 0;
        // 查找到个数最长的数字，且索引相差最短
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (arr[1] > max) {
                max = arr[1];
                minLen = arr[2] - arr[1];
            } else if (arr[1] == max) {
                minLen = Math.min(minLen, arr[2] - arr[1]);
            }
        }
        return minLen;
    }

}
