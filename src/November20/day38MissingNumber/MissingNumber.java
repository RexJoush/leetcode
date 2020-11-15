package November20.day38MissingNumber;

/**
 * @author Joush
 * @time 2020.11.14
 */

/*
    0～n-1中缺失的数字（剑指 Offer 53 - II）
    https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
    
    一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

    示例 1:
        输入: [0,1,2,4,5]
        输出: 2
    示例 2:
        输入: [0,1,2,3,4,5,6,7,9]
        输出: 8

    限制：
        1 <= 数组长度 <= 10000

    
 */
public class MissingNumber {


    /*
        二分法
     */
    public int missingNumber2(int[] nums){

        // 空数组返回 0
        if (nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left <= right){

            int mid = (left + right) / 2;
            // 如果当前值不等，则说明缺的数字在左侧子数组
            if (mid != nums[mid]){
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        // 返回
        return left;

    }



    /*
        遍历法，从头到尾遍历，如是当前索引不等于当前值，就返回
     */
    public int missingNumber(int[] nums) {

        if (nums.length == 0){
            return 0;
        }
        int i = 0;
        for (i = 0; i < nums.length; i++){
            if (i != nums[i]){
                return i;
            }
        }
        return i;
    }
}
