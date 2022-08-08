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
        方法三：优化的 0-1 背包
        假设最终的方案中，所有的负数的和的绝对值为 m
        那么满足 target 的情况即满足下面等式
            (s - m) - m = s - 2 * m = target
        即
            m = (s - target) / 2
        那么问题就转换为，在 nums 中，选则一些数，使得他们的和为 m 的方案数，那么就是基础的背包问题了
        结果：
            4 ms, 54.41%
            41.3 MB, 15.10%
     */
    public int findTargetSumWays3(int[] nums, int target) {
        int n = nums.length;
        int s = 0;
        for (int num : nums) {
            s += Math.abs(num);
        }
        // 无法满足的情况
        if (Math.abs(target) > s || (s - target) % 2 != 0) {
            return 0;
        }
        int m = (s - target) / 2;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 不选当前数字
                dp[i][j] += dp[i - 1][j];
                // 可选当前数字
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][m];
    }

    /*
        方法二：0-1 背包问题求方案数
        定义 dp[i][j] 表示前 i 个数当前计算结果为 j 的方案数
        起始情况，不考虑任何数，计算结果为 0 的方案数有 1 种
            dp[0][0] = 1
        那么对于 第 i 个值有两种情况
            dp[i][j] = dp[i-1][j-nums[i-1]] + dp[i-1][j+nums[i-1]]
        对于 j，范围为 [-sum, sum] 因此在开辟空间时，需要开辟双倍的空间,同时做好数据偏移
        结果：
            11 ms, 43.35%
            41.6 MB, 8.41%
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;
        int s = 0;
        for (int num : nums) {
            s += Math.abs(num);
        }
        // 无法满足，返回 0
        if (Math.abs(target) > s) {
            return 0;
        }

        int[][] dp = new int[n + 1][s * 2 + 1];
        dp[0][s] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = -s; j <= s; j++) {
                // 负值的部分
                if (j - nums[i - 1] + s >= 0) {
                    dp[i][j + s] += dp[i - 1][j - nums[i - 1] + s];
                }
                // 正值的部分
                if (j + nums[i - 1] + s <= 2 * s) {
                    dp[i][j + s] += dp[i - 1][j + nums[i - 1] + s];
                }
            }
        }
        return dp[n][s + target];
    }

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
