package y2022.m04April.day551MaximumPopulationYear;

/**
 * @author Rex Joush
 * @time 2022.04.11
 */

/*
    人口最多的年份
    https://leetcode-cn.com/problems/maximum-population-year/

    给你一个二维整数数组 logs ，其中每个 logs[i] = [birthi, deathi] 表示第 i 个人的出生和死亡年份。
    年份 x 的 人口 定义为这一年期间活着的人的数目。
    第 i 个人被计入年份 x 的人口需要满足：x 在闭区间 [birthi, deathi - 1] 内。注意，人不应当计入他们死亡当年的人口中。
    返回 人口最多 且 最早 的年份。

    示例 1：
        输入：logs = [[1993,1999],[2000,2010]]
        输出：1993
        解释：人口最多为 1 ，而 1993 是人口为 1 的最早年份。
    示例 2：
        输入：logs = [[1950,1961],[1960,1971],[1970,1981]]
        输出：1960
        解释：
        人口最多为 2 ，分别出现在 1960 和 1970 。
        其中最早年份是 1960 。

    提示：
        1 <= logs.length <= 100
        1950 <= birthi < deathi <= 2050

 */
public class MaximumPopulationYear {

    /*
        差分数组
        结果：
            0 ms, 100.00%
            39.7 MB, 23.29%
     */
    public int maximumPopulation(int[][] logs) {
        int offset = 1950;
        int[] diff = new int[102];

        for (int[] log : logs) {
            diff[log[0] - offset]++;     // 左区间的位置 + 1
            diff[log[1] - offset]--; // 右区间+1 的位置-1
        }
        int max = 0; // 最大值
        int res = 0; // 最大值的最小下标
        int curr = 0; // 当前年份的人数
        for (int i = 0; i < diff.length; i++) {
            curr += diff[i];
            if (curr > max) {
                max = curr;
                res = i;
            }
        }
        return res + offset;
    }
}
