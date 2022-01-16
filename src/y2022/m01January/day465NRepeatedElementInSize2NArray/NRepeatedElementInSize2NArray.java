package y2022.m01January.day465NRepeatedElementInSize2NArray;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2022.01.15
 */

/*
    在长度 2N 的数组中找出重复 N 次的元素
    https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array/

    给你一个整数数组 nums ，该数组具有以下属性：

    nums.length == 2 * n.
    nums 包含 n + 1 个 不同的 元素
    nums 中恰有一个元素重复 n 次
    找出并返回重复了 n 次的那个元素。

    示例 1：
        输入：nums = [1,2,3,3]
        输出：3
    示例 2：
        输入：nums = [2,1,2,5,3,2]
        输出：2
    示例 3：
        输入：nums = [5,1,5,2,5,3,5,4]
        输出：5

    提示：
        2 <= n <= 5000
        nums.length == 2 * n
        0 <= nums[i] <= 104
        nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次

 */
public class NRepeatedElementInSize2NArray {

    /*
        重复 N 次意味着，这个数出现 n 次，另外所有数字均只出现了一次
        所有出现重复时返回即可
        结果：
            0 ms, 100.00%
            39.5 MB, 31.49%
     */
    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return 0;
    }
}
