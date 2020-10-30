package October20.simple.day09RomanToIntegerAndSearchInsertPosition;

/**
 * @author Joush
 * @time 2020.1016
 */

/*
    搜索插入位置
    https://leetcode-cn.com/problems/search-insert-position/
    
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    你可以假设数组中无重复元素。

    示例 1:
        输入: [1,3,5,6], 5
        输出: 2
    示例 2:
        输入: [1,3,5,6], 2
        输出: 1
    示例 3:
        输入: [1,3,5,6], 7
        输出: 4
    示例 4:
        输入: [1,3,5,6], 0
        输出: 0

 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3};
        int target = 3;

        System.out.println(searchInsert(nums, target));

    }

    public static int searchInsert2(int[] nums, int target) {
        // 空数组直接返回0
        if (nums.length == 0 || target < nums[0]){
            return 0;
        }
        // 比最后一个大，则直接返回数组长度
        if (target >= nums[nums.length - 1]){
            return nums.length;
        }
        for (int i = 0; i < nums.length; i++){
            if (target < nums[i]){
                return i;
            }
        }
        return 0;

    }

    public static int searchInsert(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right){

            mid = (left + right - 1) / 2;

            if (nums[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
