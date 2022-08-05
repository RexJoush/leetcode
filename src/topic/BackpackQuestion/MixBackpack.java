package topic.BackpackQuestion;

/**
 * @author Rex Joush
 * @time 2022.08.05
 */

/*
    混合背包，指的是同时存在三种背包的情况
 */
public class MixBackpack {

    /*
        当 times[i] = -1, 表示只能拿一次
        当 times[i] = 0, 表示可拿无限次
        当 times[i] = k, 表示可拿 k 次
        其实混合背包可当做多重背包来理解，因此做法也可通过多重背包的方式来做
     */
    public int maxValue(int limit, int[] value, int[] weight, int[] times) {

        int n = value.length;
        int dp[] = new int[limit + 1];

        for (int i = 1; i < n; i++) {
            for (int j = value[i]; j <= limit; j++) {
                // 0-1背包
                if (times[i] == -1) {
                    dp[i] = Math.max(dp[i], dp[j - weight[i]] + value[i]);
                }
            }
        }
        return dp[limit];
    }

}
