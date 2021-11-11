package y2021.m10October.day389IntersectionOfTwoArrays;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2021.10.31
 */

/*
     两个数组的交集
     https://leetcode-cn.com/problems/intersection-of-two-arrays/

     给定两个数组，编写一个函数来计算它们的交集。

    示例 1：
        输入：nums1 = [1,2,2,1], nums2 = [2,2]
        输出：[2]
    示例 2：
        输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出：[9,4]

    说明：
        输出结果中的每个元素一定是唯一的。
        我们可以不考虑输出结果的顺序。

 */
public class IntersectionOfTwoArrays {

    /*
        将第一个数组放入 set 集合中，遍历第二个数组，判断是否在第一个 set 中，如果存在，放入结果 set，返回即可
        结果：
            2 ms, 90.13%
            38.7 MB, 19.96%
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int j : nums1) {
            set.add(j);
        }

        for (int j : nums2) {
            if (set.contains(j)) {
                result.add(j);
            }
        }
        int index = 0;
        int[] res = new int[result.size()];
        for (Integer integer : result) {
            res[index++] = integer;
        }

        return res;

    }

}
