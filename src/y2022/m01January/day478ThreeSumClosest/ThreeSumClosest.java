package y2022.m01January.day478ThreeSumClosest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.02.28
 */

/*
    最接近的三数之和
    https://leetcode-cn.com/problems/3sum-closest/

    给你一个长度为 n 的整数数组nums和 一个目标值target。
    请你从 nums 中选出三个整数，使它们的和与target最接近。
    返回这三个数的和。假定每组输入只存在恰好一个解。

    示例 1：
        输入：nums = [-1,2,1,-4], target = 1
        输出：2
        解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
    示例 2：
        输入：nums = [0,0,0], target = 1
        输出：0

    提示：
        3 <= nums.length <= 1000
        -1000 <= nums[i] <= 1000
        -10^4 <= target <= 10^4

 */
public class ThreeSumClosest {

    /*
        参考三数之和的解法，排序 + 双指针
        结果：
            6 ms, 49.83%
            41.1 MB, 7.58%
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        // 最小差值
        int best = nums[0] + nums[1] + nums[2];
        // 遍历 a
        for (int first = 0; first < n; first++) {

            // 保证不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            // b 指针最左端，c 对应的指针初始指向数组的最右端
            int second = first + 1;
            int third = n - 1;
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                // 如果当前相同，最佳答案直接返回
                if (sum == target) {
                    return sum;
                }

                // 如果当前差值小于最优结果，更新最优结果
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }

                // 如果大于 target，说明 third 过于靠右，则更新 third 指针往左移
                if (sum > target) {
                    third--;
                }
                // 否则，说明 second 靠左，则 second 往右移动
                else {
                    second++;
                }
            }
        }
        return best;
    }
}
