package November20.day47SetMismatch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Joush
 * @time 2020.11.23
 */

/*

    错误的集合
    https://leetcode-cn.com/problems/set-mismatch/

    集合 S 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
    给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。

    示例 1:
        输入: nums = [1,2,2,4]
        输出: [2,3]

    注意:
        给定数组的长度范围是[2, 10000]。
        给定的数组是无序的。

 */
public class SetMismatch {

    public int[] findErrorNums(int[] nums) {

        // 定义丢失的数字
        int lost = 0;
        // 定义重复的数字
        int repeat = 0;
        // 新建一个数组
        int[] result = new int[nums.length + 1];

        // 将对应位置置为 1
        for (int num : nums) {
            // 如果某个位置已经为1，就表明重复，记录重复
            if (result[num] == 1){
                repeat = num;
            }
            result[num] = 1;
        }
        // 查找为0的，记录 丢失
        for (int i = 1; i < result.length; i++){
            if (result[i] == 0){
                lost = i;
            }
        }
        // 返回重复和丢失
        return new int[]{repeat, lost};
    }

}
