package October20.day21RepeatNum;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joush
 * @time 2020.10.28
 */

/*
    数组中重复的数字（剑指 Offer 03）
    https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/

    找出数组中重复的数字。
    在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
    数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    示例 1：
        输入：[2, 3, 1, 0, 2, 5, 3]
        输出：2 或 3

 */
public class RepeatNum {

    public int findRepeatNumber(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int i : nums) {
            if (!set.add(i)) {
                return i;
            }
        }
        return 0;

    }
}
