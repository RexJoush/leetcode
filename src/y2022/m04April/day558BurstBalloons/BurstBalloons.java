package y2022.m04April.day558BurstBalloons;

/**
 * @author Rex Joush
 * @time 2022.04.18
 */

/*
    戳气球
    https://leetcode.cn/problems/burst-balloons/
    
    有 n 个气球，编号为 0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
    现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
    这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。
    如果 i - 1 或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
    求所能获得硬币的最大数量。
    
    示例 1：
        输入：nums = [3,1,5,8]
        输出：167
        解释：
            nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
            coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
    示例 2：
        输入：nums = [1,5]
        输出：10
    
    提示：
        n == nums.length
        1 <= n <= 300
        0 <= nums[i] <= 100
    
 */
public class BurstBalloons {

    /*
        动态规划
            定义 dp[i][j] 表示区间 (i, j) 内的所有气球戳破之后的最大硬币数
            相应的存在 i < k < j，k 的位置为区间 (i, j) 内最后一个戳破的气球
            那么 dp[i][j] = dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]
            同时，要保证 dp[i][j] 最大，那么需要枚举每个 k 的位置，来保证结果最大
        结果：
            33 ms, 91.56%
            41.2 MB, 47.39%
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 临时数组，两边为补充 1，方便处理边界
        int[] temp = new int[n + 2];
        temp[0] = 1;
        temp[n + 1] = 1;
        System.arraycopy(nums, 1, temp, 1, n);

        int[][] dp = new int[n + 2][n + 2];
        // len 表示开区间的长度，从 3 开始枚举
        for (int len = 3; len <= n + 2; len++) {
            // i 表示区间左端点
            for (int i = 0; i <= n + 2 - len; i++) {
                int res = 0;
                // k 为枚举 ij 内的索引
                for (int k = i + 1; k < i + len - 1; k++) {
                    int left = dp[i][k];
                    int right = dp[k][i + len - 1];
                    // 计算区间内最大的 res
                    res = Math.max(res, left + right + temp[i] * temp[k] * temp[i + len - 1]);
                }
                // 记录当前区间即可
                dp[i][i + len - 1] = res;
            }
        }
        // 返回最长的区间即可
        return dp[0][n + 1];
    }

}
