package y2020.m12December.day65NumberOfGoodPairs;

/**
 * @author Joush
 * @time 2020.12.11
 */

/*
    好数对的数目
    https://leetcode-cn.com/problems/number-of-good-pairs/

    给你一个整数数组 nums。
    如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
    返回好数对的数目。

    示例 1：
        输入：nums = [1,2,3,1,1,3]
        输出：4
        解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
    示例 2：
        输入：nums = [1,1,1,1]
        输出：6
        解释：数组中的每组数字都是好数对
    示例 3：
        输入：nums = [1,2,3]
        输出：0

    提示：
        1 <= nums.length <= 100
        1 <= nums[i] <= 100

 */
public class NumberOfGoodPairs {

    /*
        遍历即可，找到所有相同的好数对，最后减去数组长度，因为自身与自身相等重复计算了一次
        结果
            1 ms, 82.76%
            36.1 MB, 36.93%
     */
    public int numIdenticalPairs(int[] nums) {

        int result = 0;

        for (int i = 0; i < nums.length; i++){
            for (int j = i; j < nums.length; j++){
                if (nums[i] == nums[j]){
                    result ++;
                }
            }
        }
        return result - nums.length;
    }

}
