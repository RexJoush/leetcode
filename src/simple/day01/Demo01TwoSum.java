package simple.day01;

/*
    ##两数之和
    `https://leetcode-cn.com/problems/two-sum/`

    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

    示例：
        给定 nums = [2, 7, 11, 15], target = 9

        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]
 */

/**
 * author: joush
 * time: 2020.10.09
 */

import java.util.HashMap;
import java.util.Map;

public class Demo01TwoSum {

    // 简单的暴力查找，找出所有的两两组合，直到找到为止
    public int[] twoSum(int[] nums, int target){
        int[] arr = new int[2];

        for (int i = 0; i < nums.length; i++){
            arr[0] = i;
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    arr[1] = j;
                    return arr;
                }
            }
        }

        return arr;
    }


    /*
        哈希表法
        确定 x 后，需要查到 target - x 是否在列表中，所以，通过哈希表可以把查找时间由O(n) 降到 O(1)
     */

    public int[] twoSumHash(int[] nums, int target){

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++){

            // 如果表中包含了当前 x 对应的 target - x
            if (map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i}; // 返回当前 key 的值 和 i 值
            }

            // 放进hash表中
            map.put(nums[i],i);
        }

        return new int[0];
    }



}
