package y2022.m03March.day510JumpGame;

/**
 * @author Rex Joush
 * @time 2022.03.01
 */

/*
    跳跃游戏
    https://leetcode-cn.com/problems/jump-game/

    给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
    数组中的每个元素代表你在该位置可以跳跃的最大长度。
    判断你是否能够到达最后一个下标。

    示例 1：
        输入：nums = [2,3,1,1,4]
        输出：true
        解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
    示例 2：
        输入：nums = [3,2,1,0,4]
        输出：false
        解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。

    提示：
        1 <= nums.length <= 3 * 104
        0 <= nums[i] <= 105

 */
public class JumpGame {

    /*
        贪心，模拟跳跃过程
        每一个位置上的最远到达距离 k 一定不会落后于每次只走一步所能到达的距离
     */
    public boolean canJump(int[] nums) {
        // 当前能跳的最远距离
        int k = 0;

        // 遍历每个点，记录从每个点开始能跳的最远距离
        for (int i = 0; i < nums.length; i++) {
            // 如果当前位置跳不到，就结束循环了
            if (i > k) return false;
            // 更新最远距离
            k = Math.max(k, nums[i] + i);
        }
        return true;
    }


}
