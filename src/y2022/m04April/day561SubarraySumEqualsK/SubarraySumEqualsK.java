package y2022.m04April.day561SubarraySumEqualsK;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2022.04.21
 */

/*
    和为 K 的子数组
    https://leetcode.cn/problems/subarray-sum-equals-k/

    给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。

    示例 1：
        输入：nums = [1,1,1], k = 2
        输出：2
    示例 2：
        输入：nums = [1,2,3], k = 3
        输出：2

    提示：
        1 <= nums.length <= 2 * 10^4
        -1000 <= nums[i] <= 1000
        -10^7 <= k <= 10^7

 */
public class SubarraySumEqualsK {

    /*
        方法一: 暴力枚举
            枚举所有的连续子数组，看结果是否为 k
        结果：
            1795 ms, 5.05%
            44.9 MB, 30.41%
     */
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            int temp = 0;
            for (int j = i; j < nums.length; ++j) {
                temp += nums[j];
                if (temp == k) {
                    ++result;
                }
            }
        }
        return result;
    }

    /*
        方法二：前缀和 + 哈希表
            使用哈希表来存储前缀和，减少计算的量
            定义 pre[i] = nums[0] + nums[1] + ... + nums[i]
            那么 pre[i] = pre[i-1] + nums[i]
            因此，[i...j] 这个数组的和为 k 可以转化为
                pre[j] - pre[i - 1] = k
            即
                pre[i - 1] = pre[j] - k
            因此，我们只需统计有多少个前缀和为 pre[j] - k 
            建立哈希表，以和为 key，出现的次数为 value，记录 pre[j] 出现的次数
            那么以 j 为结尾的答案，就等于 map[pre[j] - k]
        结果：
            25 ms, 42.69%
            45.6 MB, 19.21%
     */
    public int subarraySum2(int[] nums, int k) {
        int result = 0;
        int pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 前缀和为 0 出现了一次
        for (int i = 0; i < nums.length; ++i) {
            pre += nums[i];
            // 当前 pre 即为上面的 pre[j],如果 前面的前缀和有 pre[j] - k，即说明等式满足
            if (map.containsKey(pre - k)) {
                result += map.get(pre - k);
            }
            // 推入 map
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return result;
    }
}
