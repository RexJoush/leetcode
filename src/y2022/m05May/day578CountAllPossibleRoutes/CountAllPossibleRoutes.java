package y2022.m05May.day578CountAllPossibleRoutes;

import java.util.Arrays;

/**
 * @author Rex Joush
 * @time 2022.05.08
 */

/*
    统计所有可行路径
    https://leetcode.cn/problems/count-all-possible-routes/
    
    给你一个 互不相同 的整数数组，其中 locations[i] 表示第 i 个城市的位置。
    同时给你 start，finish 和 fuel 分别表示出发城市、目的地城市和你初始拥有的汽油总量
    每一步中，如果你在城市 i ，你可以选择任意一个城市 j ，满足  j != i 且 0 <= j < locations.length ，并移动到城市 j 。
    从城市 i 移动到 j 消耗的汽油量为 |locations[i] - locations[j]|，|x| 表示 x 的绝对值。
    请注意， fuel 任何时刻都 不能 为负，且你 可以 经过任意城市超过一次（包括 start 和 finish ）。
    请你返回从 start 到 finish 所有可能路径的数目。由于答案可能很大， 请将它对 10^9 + 7 取余后返回。
    
    示例 1：
        输入：locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
        输出：4
        解释：以下为所有可能路径，每一条都用了 5 单位的汽油：
        1 -> 3
        1 -> 2 -> 3
        1 -> 4 -> 3
        1 -> 4 -> 2 -> 3
    示例 2：
        输入：locations = [4,3,1], start = 1, finish = 0, fuel = 6
        输出：5
        解释：以下为所有可能的路径：
        1 -> 0，使用汽油量为 fuel = 1
        1 -> 2 -> 0，使用汽油量为 fuel = 5
        1 -> 2 -> 1 -> 0，使用汽油量为 fuel = 5
        1 -> 0 -> 1 -> 0，使用汽油量为 fuel = 3
        1 -> 0 -> 1 -> 0 -> 1 -> 0，使用汽油量为 fuel = 5
    示例 3：
        输入：locations = [5,2,1], start = 0, finish = 2, fuel = 3
        输出：0
        解释：没有办法只用 3 单位的汽油从 0 到达 2 。因为最短路径需要 4 单位的汽油。

    提示：
        2 <= locations.length <= 100
        1 <= locations[i] <= 109
        所有 locations 中的整数 互不相同 。
        0 <= start, finish < locations.length
        1 <= fuel <= 200

 */
public class CountAllPossibleRoutes {

    /*
        方法二：动态规划
        根据记忆化搜索的思路，将 dfs 函数进行优化
            dp[pos][fuel] 表示当前点到达重点的路径条数
        根据搜索过程可得
            dp[pos][fuel] = dp[pos][fuel] + dp[k][fuel-need]
            其中 k 为可枚举个下一个位置，need 为 pos 到 k 所需的油量
        结果：
            159 ms, 31.59%
            40.8 MB, 72.85%
     */
    public int countRoutes2(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;

        // dp[i][j] 表示 从 i 出发，剩余油量为 j，到达重点的路径数量
        int[][] dp = new int[n][fuel + 1];

        // 本身在终点的值为 1
        for (int i = 0; i <= fuel; i++) {
            dp[finish][i] = 1;
        }

        // 从小到达枚举油量，看能到达哪个位置
        for (int cur = 0; cur <= fuel; cur++) {
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    if (i != k) {
                        // cur 枚举油量，k 为中间点
                        int need = Math.abs(locations[i] - locations[k]);
                        if (cur >= need) {
                            dp[i][cur] += dp[k][cur - need];
                            dp[i][cur] %= mod;
                        }
                    }
                }
            }
        }
        return dp[start][fuel];
    }

    /*
        方法一：记忆化搜索
        考虑，用 f[pos][fuel] 表示从当前位置到达终点的路径个数
        状态转移时，枚举下一个可能的城市 i, (i != pos)
        从 pos 到 i 消耗 cost = |locations[i] - locations[pos]| 个汽油，且 cost 不能超过 fuel
        因此可以得到下面的方程
            f[pos][fuel] = f[i][fuel-cost](0 < i < n-1) 求和，其中 fuel > cost
        如果当前就在终点，即 pos = finish，那么不移动也是一种方案，f[pos][fuel] 需要额外 +1
        最终的答案即为 f[start][fuel]
        其中，如果从起点城市 start 到 finish 的最小汽油消耗量
            cost = |locations[start] - locations[finish]| > fuel 那么，即无法到达，返回 0 即可
        结果：
            50 ms, 61.81%
            40.8 MB, 76.38%
     */
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        f = new int[locations.length][fuel + 1];
        for (int[] ints : f) {
            // 区分数量为 0 和未搜索过
            Arrays.fill(ints, -1);
        }
        return dfs(locations, start, finish, fuel);
    }

    int[][] f;
    int mod = 1000000007;

    private int dfs(int[] locations, int pos, int finish, int fuel) {
        // 已经有答案了，返回
        if (f[pos][fuel] != -1) {
            return f[pos][fuel];
        }

        int n = locations.length;

        // 当前油量为 0，且未到达重点，那么即路径条为 0，返回即可
        if (fuel == 0 && pos != finish) {
            f[pos][fuel] = 0;
            return 0;
        }
        // 查看当前是否可以到达后面的任何一个点
        boolean hasNext = false;
        for (int i = 0; i < n; i++) {
            if (i != pos) {
                if (fuel >= Math.abs(locations[i] - locations[pos])) {
                    hasNext = true;
                    break;
                }
            }
        }
        // 即，油量不为 0，但无法到达任何一个点
        if (fuel != 0 && !hasNext) {
            // 当前在终点，那么就有一种解，否则为 0
            if (pos == finish) {
                f[pos][fuel] = 1;
                return 1;
            } else {
                f[pos][fuel] = 0;
                return 0;
            }
        }

        // 计算剩余的情况，如果在重点，那么多一种情况 +1
        int sum = pos == finish ? 1 : 0;
        for (int i = 0; i < n; i++) {
            if (i != pos) {
                int need = Math.abs(locations[i] - locations[pos]);
                if (fuel >= need) {
                    sum += dfs(locations, i, finish, fuel - need);
                    sum %= mod;
                }
            }
        }
        // 当前可情况计算出来
        f[pos][fuel] = sum;
        return sum;
    }

    /*
        优化后的 dfs，即如果从某个位置一步不能到达某个位置，那么多步也必然不能
        即 |locations[pos] - locations[i]| > fuel
        那么可以直接得到 f[pos][fuel] = 0;
        结果：
            40 ms, 78.68%
            40.7 MB, 91.87%
     */
    private int dfs2(int[] locations, int pos, int finish, int fuel) {
        // 已经有答案了，返回
        if (f[pos][fuel] != -1) {
            return f[pos][fuel];
        }

        // 如果一步无法到达，那么就永远无法到达
        int need = Math.abs(locations[finish] - locations[pos]);

        if (need > fuel) {
            f[pos][fuel] = 0;
            return 0;
        }

        int n = locations.length;
        // 计算剩余的情况，如果在重点，那么多一种情况 +1
        int sum = pos == finish ? 1 : 0;
        for (int i = 0; i < n; i++) {
            if (i != pos) {
                need = Math.abs(locations[i] - locations[pos]);
                if (fuel >= need) {
                    sum += dfs(locations, i, finish, fuel - need);
                    sum %= mod;
                }
            }
        }
        // 当前可情况计算出来
        f[pos][fuel] = sum;
        return sum;
    }
}
