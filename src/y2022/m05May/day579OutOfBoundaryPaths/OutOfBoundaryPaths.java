package y2022.m05May.day579OutOfBoundaryPaths;

/**
 * @author Rex Joush
 * @time 2022.05.09
 */

/*
    出界的路径数
    https://leetcode.cn/problems/out-of-boundary-paths/

    给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn]。
    你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
    给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。
    因为答案可能非常大，返回对 10^9 + 7 取余 后的结果。

    示例 1：
        输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
        输出：6
    示例 2：

        输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
        输出：12

    提示：
        1 <= m, n <= 50
        0 <= maxMove <= 50
        0 <= startRow < m
        0 <= startColumn < n

 */
public class OutOfBoundaryPaths {

    /*
        记忆化搜索，定义 dp[i][j][move] 表示从 i,j 出发，最多移动 move 步，能够出去的方案数
        又因为，球可以往上下左右移动，因此
            dp[i][j][move] =   dp[i+1][j][move-1] + dp[i-1][j][move-1]
                             + dp[i][j+1][move-1] + dp[i][j-1][move-1]
        初始化，当我们处于边界值的时候，能够出去的条数等于格子本身的值
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        max = maxMove;
        int mod = 1000000007;

        // dp[i][j][move] 代表从 i，j 的位置出发，移动步数不超过 move 的路径数量
        int[][][] dp = new int[m][n][maxMove + 1];

        // 初始化边缘格子的路径数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) add(i, j, dp);
                if (i == m - 1) add(i, j, dp);
                if (j == 0) add(i, j, dp);
                if (j == n - 1) add(i, j, dp);
            }
        }

        // 定义可移动的四个方向
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // 从小到大枚举「可移动步数」
        for (int step = 1; step <= maxMove; step++) {
            // 枚举所有的「位置」
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] d : dirs) {
                        int nx = i + d[0], ny = j + d[1];
                        // 如果位置有「相邻格子」，则「相邻格子」参与状态转移
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                            dp[i][j][step] += dp[nx][ny][step - 1];
                            dp[i][j][step] %= mod;
                        }
                    }
                }

            }
        }

        // 最终结果为从起始点触发，最大移动步数不超 maxMove 的路径数量
        return dp[startRow][startColumn][maxMove];
    }

    void add(int i, int j, int[][][] dp) {
        for (int step = 1; step <= max; step++) {
            dp[i][j][step]++;
        }
    }

    int max;

    /*
        将前两个坐标 i,j 优化成 i * n + j， 三维 dp 可以优化成 二维 dp
     */
    public int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        max = maxMove;
        int mod = 1000000007;

        //  dp[i][j] 代表从 idx 为 i 的位置出发，移动步数不超过 j 的路径数量
        int[][] dp = new int[m * n][maxMove + 1];

        // 初始化边缘格子的路径数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) add(j, dp);
                if (i == m - 1) add(i * n + j, dp);
                if (j == 0) add(i * n, dp);
                if (j == n - 1) add(i * n + j, dp);
            }
        }

        // 定义可移动的四个方向
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // 从小到大枚举「可移动步数」
        for (int step = 1; step <= maxMove; step++) {
            // 枚举所有的「位置」
            for (int index = 0; index < m * n; index++) {
                int x = index / n, y = index % n;
                for (int[] d : dirs) {
                    int nx = x + d[0], ny = y + d[1];
                    // 如果位置有「相邻格子」，则「相邻格子」参与状态转移
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        dp[index][step] += dp[nx * n + ny][step - 1];
                        dp[index][step] %= mod;
                    }
                }
            }
        }

        // 最终结果为从起始点触发，最大移动步数不超 N 的路径数量
        return dp[startRow * n + startColumn][maxMove];
    }

    // 为每个「边缘」格子，添加一条路径
    void add(int index, int[][] dp) {
        for (int step = 1; step <= max; step++) {
            dp[index][step]++;
        }
    }


}
