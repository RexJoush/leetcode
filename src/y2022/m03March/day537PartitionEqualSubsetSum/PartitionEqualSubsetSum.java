package y2022.m03March.day537PartitionEqualSubsetSum;

/**
 * @author Rex Joush
 * @time 2022.04.01
 */

/*
    分割等和子集
    https://leetcode-cn.com/problems/partition-equal-subset-sum/

    给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，
    使得两个子集的元素和相等。

    示例 1：
        输入：nums = [1,5,11,5]
        输出：true
        解释：数组可以分割成 [1, 5, 5] 和 [11] 。
    示例 2：
        输入：nums = [1,2,3,5]
        输出：false
        解释：数组不能分割成两个元素和相等的子集。

    提示：
        1 <= nums.length <= 200
        1 <= nums[i] <= 100

 */
public class PartitionEqualSubsetSum {

    /*
        动态规划，0-1 背包问题
            初始条件
                定义 dp[i][j] 表示，考虑，下标 [0, i] 这个区间里所有的整数，
                在他们中能否选出一些数字，使得这些数字之和为 j
            状态转移方程
                1.不选择 nums[i], dp[i][j] = dp[i-1][j]
                2.选择 nums[i]
                    (1) nums[i] == j, dp[i][j] = true;
                    (2) nums[i] < j , dp[i][j] = dp[i-1][j - nums[i]];
            初始化
                dp[i][j] = false
                dp[len-1][sum/2] // 输出的结果，当数组全部遍历完毕，和为 sum / 2;
            例子，1 5 11 5
                    0   1   2   3   4   5   6   7   8   9   10   11 // 因为 sum为 22，考虑到 11
               1    F   T   F   F   F   F   F   F   F   F   F    F     // 因为，1这个背包只能填满容量为 1 的背包，因此只有 1 是 true
               5    F   T   F   F   F   T   T   F   F   F   F    F     // 5 之前的背包无法填充，直接抄下来，5 可以填满 5 的背包，而 6，当把 5 装进背包后，剩余为 1，而上一行 1 为 true，所以可以填满
               11   F   T   F   F   F   T   T   F   F   F   F    T     // 11 之前的照抄，11 可以填满，11 为 true
               5    F   T   F   F   F   T   T   F   F   F   T    T     // 5 之前的照抄，6 考虑和第二行一样，10 因为当前装下了 5，上一行也能装下 5，因此 10 为 true
        结果：
            32 ms, 29.42%
            42.5 MB, 15.94%
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        // 先求和
        for (int i : nums) {
            sum += i;
        }
        // 奇数直接返回 false
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        // 初始化dp数组
        boolean[][] dp = new boolean[n][target + 1];
        // 第一行，只有自己的容量可以填满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                // 照抄上一行
                dp[i][j] = dp[i-1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
                }
            }
            // 根据表格中，某一行为 true，那么这一行之后的所有列均为 true，
            // 所以当某一行的 target 为 true 时，返回 true
            if (dp[i][target]) {
                return true;
            }
        }
        return dp[n - 1][target];
    }
}
