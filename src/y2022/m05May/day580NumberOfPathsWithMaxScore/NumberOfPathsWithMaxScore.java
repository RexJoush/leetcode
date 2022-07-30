package y2022.m05May.day580NumberOfPathsWithMaxScore;

import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.05.10
 */

/*
    最大得分的路径数目
    https://leetcode.cn/problems/number-of-paths-with-max-score/

    给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。
    你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字符 1, 2, ..., 9 或者障碍 'X'。
    在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。
    一条路径的 「得分」 定义为：路径上所有数字的和。
    请你返回一个列表，包含两个整数：
    第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对 10^9 + 7 取余。
    如果没有任何路径可以到达终点，请返回 [0, 0] 。
    
    示例 1：
            E 2 3
            2 X 2
            1 2 S
        输入：board = ["E23","2X2","12S"]
        输出：[7,1]
    示例 2：
        输入：board = ["E12","1X1","21S"]
        输出：[4,2]
    示例 3：
        输入：board = ["E11","XXX","11S"]
        输出：[0,0]
    
    提示：
        2 <= board.length == board[i].length <= 100

 */
public class NumberOfPathsWithMaxScore {

    /*
        动态规划
        dp[i][j] 定义为从 S 到 i, j 点的最大路径值
            那么，不在边界，或上边界和左边界
                dp[i][j] = max(dp[i+1][j], dp[i][j+1], dp[i+1][j+1]) + board[i][j]
            在下边界
                dp[i][j] = dp[i+1][j] + board[i][j]
            在右边界
                dp[i][j] = dp[i][j+1] + board[i][j]
        此时，dp[0][0] 即为最大的路径值，那么最大方案数也可由 dp 过程得到
        异常处理，参考注释
        定义 plan[i][j] 表示从 S 到 i, j 点的最大方案数
            不在边界或上边界和左边界
                plan[i][j] = 取 max 值的结果的方案数，如果两边方案数一样，那么就取相加的结果
            有边界
                plan[i][j] = plan[i][j+1]
            下边界
                plan[i][j] = plan[i+1][j]
        结果：
            8 ms, 83.65%
            41.8 MB, 54.67%
     */
    public int[] pathsWithMaxScore(List<String> board) {
        int mod = 1000000007;
        int n = board.size();
        int[][] dp = new int[n][n];
        int[][] plan = new int[n][n];

        // 右下角的方案数为 1，值为 0
        dp[n - 1][n - 1] = 0;
        plan[n - 1][n - 1] = 1;

        // 转换成字符数组
        char[][] chars = new char[n][n];
        for(int i = 0; i < n; i++){
            chars[i] = board.get(i).toCharArray();
        }

        // 初始化下边界
        for (int i = n - 2; i >= 0; i--) {
            // 如果当前位置是 X, 或者前一个值为 -1，那么直接赋值 dp = -1, plan = 0
            if (chars[n - 1][i] == 'X' || dp[n - 1][i + 1] == -1) {
                dp[n - 1][i] = -1;
                plan[n - 1][i] = 0;
            } else {
                dp[n - 1][i] = dp[n - 1][i + 1] + chars[n - 1][i] - '0';
                plan[n - 1][i] = plan[n - 1][i + 1];
            }
        }
        // 初始化右边界，与下边界一样的处理情况
        for (int i = n - 2; i >= 0; i--) {
            // 为 X 或者前一个为 -1, 直接赋值
            if (chars[i][n - 1] == 'X' || dp[i + 1][n - 1] == -1) {
                dp[i][n - 1] = -1;
                plan[i][n - 1] = 0;
            } else {
                dp[i][n - 1] = dp[i + 1][n - 1] + chars[i][n - 1] - '0';
                plan[i][n - 1] = plan[i + 1][n - 1];
            }
        }

        // dp 过程
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                // 如果为障碍物，dp 置为 -1，方案数为 0
                if (chars[i][j] == 'X') {
                    dp[i][j] = -1;
                    plan[i][j] = 0;
                    continue;
                }
                // 记录一下右边，下边和右下边的 dp 值，用于记录当前选择哪一种
                int right = dp[i][j + 1];
                int down = dp[i + 1][j];
                int corn = dp[i + 1][j + 1];
                // dp[i][j] 取最大值
                int max = Math.max(Math.max(right, down), corn);
                // 如果 max = -1, 说明不可由右，下，右下到达，直接赋值不可达
                if (max == -1) {
                    dp[i][j] = -1;
                    plan[i][j] = 0;
                    continue;
                }
                // 计算当前的 dp 值为 三个最大值 + 本身的值
                dp[i][j] = max + chars[i][j] - '0';

                // 记录取最大值的方案，那一侧能取到最大值，就添加
                if (dp[i][j + 1] == max) {
                    plan[i][j] += plan[i][j + 1];
                }
                if (dp[i + 1][j] == max) {
                    plan[i][j] += plan[i + 1][j];
                }
                if (dp[i + 1][j + 1] == max) {
                    plan[i][j] += plan[i + 1][j + 1];
                }
                // 取 mod
                plan[i][j] %= mod;
            }
        }
        // 说明不可达
        if (dp[0][0] == -1) {
            return new int[]{0, 0};
        }
        // 此处因为做计算过程中，0,0 点的值为 max + 'E' - '0', 所以做一下反处理
        return new int[]{dp[0][0] - 'E' + '0', plan[0][0]};
    }
}
