package y2021.m04April.day178TopKFrequentElements;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2021.04.03
 */

/*
    前 K 个高频元素
    https://leetcode-cn.com/problems/top-k-frequent-elements/

    给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

    示例 1:
        输入: nums = [1,1,1,2,2,3], k = 2
        输出: [1,2]
    示例 2:
        输入: nums = [1], k = 1
        输出: [1]

    提示：
        1 <= nums.length <= 105
        k 的取值范围是 [1, 数组中不相同的元素的个数]
        题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的


    进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n是数组大小

 */
public class TopKFrequentElements {

    /*
        哈希表计数，排序
        结果：
            13 ms, 51.49%
            44.1 MB, 14.76%
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 计数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer orDefault = map.getOrDefault(num, 0);
            map.put(num, orDefault + 1);
        }
        // 如果只有一个数字，防止下面排序出现越界，直接返回当前数字即可
        if (map.size() == 1) {
            return new int[]{nums[0]};
        }
        // 将 map 转换成数组，方便排序
        int[][] result = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }
        // 按照个数进行从高到低排序
        Arrays.sort(result, (o1, o2) -> o2[1] - o1[1]);
        // 获取前 k 个的 key
        int[] res = new int[k];
        for (int j = 0; j < k; j++) {
            res[j] = result[j][0];
        }
        return res;
    }

    /*
        使用 stream api 对 map 直接排序，但效率好像不高
     */
    public int[] topKFrequent2(int[] nums, int k) {
        // 计数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer orDefault = map.getOrDefault(num, 0);
            map.put(num, orDefault + 1);
        }
        // 如果只有一个数字，防止下面排序出现越界，直接返回当前数字即可
        if (map.size() == 1) {
            return new int[]{nums[0]};
        }

        // 使用 stream() 直接对 map 进行排序按照个数进行从高到低排序
        return map.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
