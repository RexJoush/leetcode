package y2021.m11November.day390IntersectionOfTwoArraysII;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2021.11.09
 */

/*
    两个数组的交集 II
    https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/

    给定两个数组，编写一个函数来计算它们的交集。

    示例 1：
        输入：nums1 = [1,2,2,1], nums2 = [2,2]
        输出：[2,2]
    示例 2:
        输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出：[4,9]

    说明：
        输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
        我们可以不考虑输出结果的顺序。
 */
public class IntersectionOfTwoArraysII {

    /*
        遍历第一个数组存储哈希表，并记录次数
        再遍历第二个表，如果存在，则加入结果，并减少一次
        最终返回即可
        结果：
            3 ms, 43.43%
            38.8 MB, 5.24%
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // 将短的数组置为第一个
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums1) {
            /*
                map.getOrDefault() 方法，当集合中包含此 key 时，返回此 key 的 value
                否则返回参数中的 defaultValue
             */
            int count = map.getOrDefault(i, 0) + 1;
            map.put(i, count);
        }

        // 定义返回数组
        int[] result = new int[nums1.length];
        int index = 0;
        for (int i : nums2) {
            // 获取当前 key 的值，如果存在，则获得 count，否则获取为 0
            int count = map.getOrDefault(i, 0);
            // 存在当前 key
            if (count > 0) {
                // 放入结果集合中
                result[index++] = i;
                // count - 1
                count--;
                // 如果 count 还有，则更新集合中的个数，否则移除该 key
                if (count > 0) {
                    map.put(i, count);
                } else {
                    map.remove(i);
                }
            }
        }
        // 返回数组 0 到 index 的子数组
        return Arrays.copyOfRange(result, 0, index);
    }

}
