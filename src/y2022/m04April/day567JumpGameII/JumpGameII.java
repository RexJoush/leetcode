package y2022.m04April.day567JumpGameII;

/**
 * @author Rex Joush
 * @time 2022.04.27
 */

/*
    跳跃游戏 II
    https://leetcode.cn/problems/jump-game-ii/
    给你一个非负整数数组 nums ，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
    你的目标是使用最少的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置。
    
    示例 1:
        输入: nums = [2,3,1,1,4]
        输出: 2
        解释: 跳到最后一个位置的最小跳跃数是 2。
             从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
    示例 2:
        输入: nums = [2,3,0,1,4]
        输出: 2

    提示:
        1 <= nums.length <= 10^4
        0 <= nums[i] <= 1000

 */
public class JumpGameII {

    /*
        方法二：贪心算法
            每次记录能跳到的最远距离，然后 step + 1
        结果：
            1 ms, 99.01%
            41.8 MB, 57.54%
     */
    public int jump2(int[] nums) {
        // 能跳到的边界
        int border = 0;
        // 在边界内，能跳到最远的地方
        int maxIndex = 0;
        // 记录跳跃的步数
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 统计当前位置，到最远距离之间位置能跳到的最远位置
            maxIndex = Math.max(maxIndex, nums[i] + i);
            // 到达最远距离了，必须要跳了
            if (i == border) {
                steps++; // 步数 +1
                border = maxIndex; // 更新边界
            }
        }
        // 返回结果
        return steps;
    }

    /*
        方法一：暴力解法
            steps[i] 表示跳跃至当前所需的最小步数
            遍历每一个 nums[i] 更新当前能跳到的位置的最小值
        结果：
            45 ms, 13.74%
            41.8 MB, 57.54%
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int[] steps = new int[n];
        steps[0] = 0;
        for (int i = 0; i < n; i++) {
            // 遍历当前能跳到的地方
            for (int j = 1; j <= nums[i]; j++){
                if (i + j >= n) {
                    break;
                }
                // =0 说明没跳到，等于当值 + 1
                if (steps[i + j] == 0) {
                    steps[i + j] = steps[i] + 1;
                } else {
                    // 之前跳到了，如果当前跳法更短，就更新
                    steps[i + j] = Math.min(steps[i+j], steps[i] + 1);
                }
            }
        }
        // 返回结果即可
        return steps[n - 1];
    }
    
}
