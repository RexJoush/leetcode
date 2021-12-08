package y2021.m11November.day413RelativeRanks;

import java.util.Arrays;

/**
 * @author Rex Joush
 * @time 2021.11.24
 */

/*
    相对名次
    https://leetcode-cn.com/problems/relative-ranks/

    给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。
    所有得分都 互不相同 。
    运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。
    运动员的名次决定了他们的获奖情况：
    名次第 1 的运动员获金牌 "Gold Medal" 。
    名次第 2 的运动员获银牌 "Silver Medal" 。
    名次第 3 的运动员获铜牌 "Bronze Medal" 。
    从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
    使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。

    示例 1：
        输入：score = [5,4,3,2,1]
        输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
        解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
    示例 2：
        输入：score = [10,3,8,9,4]
        输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
        解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。

    提示：
        n == score.length
        1 <= n <= 10^4
        0 <= score[i] <= 10^6
        score 中的所有值 互不相同

 */
public class RelativeRanks {

    /*
        排序，使用二维数组记录名次即可
        结果：
             8 ms, 63.33%
             39.5 MB, 67.56%
     */
    public String[] findRelativeRanks(int[] score) {

        int n = score.length;
        String[] result = new String[n];

        int[][] arr = new int[n][2];

        // 二位数组记录分数和当前的下标
        for (int i = 0; i < n; i++){
            arr[i][0] = score[i];
            arr[i][1] = i;
        }
        // 按照分数排序
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);

        // 填写结果
        for (int i = 0; i < n; i++) {
            if (i >= 3) {
                // 按二维数组的第二位填写即可
                result[arr[i][1]] = Integer.toString(i + 1);
            }
            else if (i == 0) {
                result[arr[i][1]] = "Gold Medal";
            } else if (i == 1) {
                result[arr[i][1]] = "Silver Medal";
            } else {
                result[arr[i][1]] = "Bronze Medal";
            }
        }

        return result;
    }

}
