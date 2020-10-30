package October20.simple.day18MoveZeroes;

/**
 * @author Joush
 * @time 2020.10.25
 */

/*

    移动零
    https://leetcode-cn.com/problems/move-zeroes/

    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

    示例:
        输入: [0,1,0,3,12]
        输出: [1,3,12,0,0]

 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};

        moveZeroes(nums);
    }

    public static void moveZeroes(int[] nums) {

        int i = 0;
        int index = 0;

        while (i < nums.length){
            if (nums[i] == 0){
                index = i;
                while (nums[index] == 0 && index < nums.length - 1){
                    index++;
                }
                nums[i] = nums[index];
                nums[index] = 0;
            }
            i++;
        }

    }

}
