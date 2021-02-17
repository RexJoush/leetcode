package y2020.m12December.day55ContainsDuplicate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joush
 * @time 2020.12.01
 */

 /*
    存在重复元素 II
    https://leetcode-cn.com/problems/contains-duplicate-ii/

    给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。

    示例 1:
        输入: nums = [1,2,3,1], k = 3
        输出: true
    示例 2:
        输入: nums = [1,0,1,1], k = 1
        输出: true
    示例 3:
        输入: nums = [1,2,3,1,2,3], k = 2
        输出: false

 */
public class ContainsDuplicate {

    /*
        遍历数组，将元素和下标值加入 hash 表中，如果出现重复的元素，就将当前 i 值与 存储的下标值进行比较
        如果小于等于 k，则直接返回 true，否则将 map 中的下标值进行更新

        结果
            8 ms, 97.04%
            44.1 MB, 30.10%
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        // 遍历数组
        for (int i = 0; i < nums.length; i++){
            // 如果包含当前元素
            if (map.containsKey(nums[i])){
                // 进行下标值做差
                if (i - map.get(nums[i]) <= k) {
                    return true;
                } else {
                    // 更新下标值
                    map.put(nums[i], i);
                }
            }
            // 不包含就加入集合
            else {
                map.put(nums[i], i);
            }
        }
        // 遍历到最后没有符合条件的，返回 false
        return false;
    }

}
