package y2020.m12December.day76SmallerCurrentNumber;

/**
 * @author Joush
 * @time 2020.12.22
 */

/*
    有多少小于当前数字的数字
    https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
    
    给你一个数组  nums，对于其中每个元素  nums[i]，请你统计数组中比它小的所有数字的数目。
    换而言之，对于每个  nums[i]  你必须计算出有效的  j  的数量，其中 j 满足  j != i 且 nums[j] < nums[i] 。
    以数组形式返回答案。

    示例 1：
        输入：nums = [8,1,2,2,3]
        输出：[4,0,1,1,3]
        解释：
        对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
        对于 nums[1]=1 不存在比它小的数字。
        对于 nums[2]=2 存在一个比它小的数字：（1）。
        对于 nums[3]=2 存在一个比它小的数字：（1）。
        对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
    示例 2：
        输入：nums = [6,5,4,8]
        输出：[2,1,0,3]
    示例 3：
        输入：nums = [7,7,7,7]
        输出：[0,0,0,0]

    提示：
        2 <= nums.length <= 500
        0 <= nums[i] <= 100

 */
public class SmallerCurrentNumber {
    
    /*
        桶排序思想
        因为题目限制在 0 <= nums[i] <= 100，所以新建一个数组，计算频次
        此方式适用于数据量大，但数据范围不大的情况
        结果：
            1 ms, 99.99%
            38.7 MB, 16.31%

     */
    public int[] smallerNumbersThanCurrent2(int[] nums){
        int[] result = new int[nums.length];
        int[] frequency = new int[101];

        // 数组中存储的是每个数字出现的频率
        for (int num : nums) {
            frequency[num]++;
        }

        // 数组中存储的是每个数字比当前数字小的个数
        for (int i = 1; i < frequency.length; i++) {
            frequency[i] += frequency[i-1];
        }
        // 输出结果数组
        for (int i = 0; i < result.length; i++) {
            if (nums[i] > 0){
                result[i] = frequency[nums[i] - 1];
            }
        }
        return result;
    }

    /*
        遍历，计数，O(n2) 级复杂度
        结果：
            15 ms, 35.27%
            38.9 MB, 7.93%
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];

        int count = 0;
        for (int i = 0 ; i < nums.length; i++){
            for (int j = 0; j < nums.length; j++){
                if (nums[j] < nums[i]){
                    count++;
                }
            }
            result[i] = count;
            count = 0;
        }
        return result;
    }
}
