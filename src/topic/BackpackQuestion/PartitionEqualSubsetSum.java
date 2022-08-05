package topic.BackpackQuestion;

/**
 * @author Rex Joush
 * @time 2022.07.31
 */

/*
    分割等和子集
 */
public class PartitionEqualSubsetSum {

    /*
        01 背包
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum % 2) == 1) {
            return false;
        }
        int limit = sum / 2;
        int[] dp = new int[limit + 1];

        for (int i = 0; i < n; i++) {
            for (int j = limit; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return nums[sum] == limit;
    }
}