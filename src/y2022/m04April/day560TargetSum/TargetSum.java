package y2022.m04April.day560TargetSum;

/**
 * @author Rex Joush
 * @time 2022.04.20
 */

/*
    目标和
    https://leetcode.cn/problems/target-sum/
    
    给你一个整数数组 nums 和一个整数 target 。
    向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
    例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
    返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
    
    示例 1：
        输入：nums = [1,1,1,1,1], target = 3
        输出：5
        解释：一共有 5 种方法让最终目标和为 3 。
        -1 + 1 + 1 + 1 + 1 = 3
        +1 - 1 + 1 + 1 + 1 = 3
        +1 + 1 - 1 + 1 + 1 = 3
        +1 + 1 + 1 - 1 + 1 = 3
        +1 + 1 + 1 + 1 - 1 = 3
    示例 2：
        输入：nums = [1], target = 1
        输出：1
    提示：
        1 <= nums.length <= 20
        0 <= nums[i] <= 1000
        0 <= sum(nums[i]) <= 1000
        -1000 <= target <= 1000

 */
public class TargetSum {

    /*
        方法一：回溯法
            遍历所有的情况，计算可能的结果
        结果：
            538 ms, 16.90%
            39.3 MB, 49.01%
     */
    public int findTargetSumWays(int[] nums, int target) {
        backtrace(0, target, 0, nums);
        return res;
    }

    int res = 0;

    public void backtrace(int index, int target, int current, int[] nums) {
        // 到达数组末尾，记录结果
        if (index == nums.length) {
            if (current == target) {
                res++;
            }
            return;
        }
        // 回溯 + 当前值
        backtrace(index + 1, target, current + nums[index], nums);
        // 回溯 - 当前值
        backtrace(index + 1, target, current - nums[index], nums);
    }
}
