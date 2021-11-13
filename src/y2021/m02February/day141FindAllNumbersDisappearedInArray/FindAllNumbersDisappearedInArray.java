package y2021.m02February.day141FindAllNumbersDisappearedInArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.02.25
 */

/*
    找到所有数组中消失的数字
    https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/

    给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
    请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

    示例 1：
        输入：nums = [4,3,2,7,8,2,3,1]
        输出：[5,6]
    示例 2：
        输入：nums = [1,1]
        输出：[2]

    提示：
        n == nums.length
        1 <= n <= 105
        1 <= nums[i] <= n

 */
public class FindAllNumbersDisappearedInArray {

    /*
        新建一个 n + 1 长度的数组，计算每个数字对应下标的个数，遍历数组，为 0 的即是缺失的
        结果：
            3 ms, 100.00%
            47.7 MB, 6.90%
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] count = new int[nums.length + 1];

        // 计算每个数字对应下标的个数
        for (int num : nums) {
            count[num]++;
        }
        for (int i = 1; i < count.length; i++) {
            if (count[i] == 0) {
                result.add(i);
            }
        }
        return result;
    }

}
