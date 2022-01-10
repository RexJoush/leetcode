package y2022.m01January.day460SurfaceAreaOf3DShapes;

/**
 * @author Rex Joush
 * @time 2022.01.10
 */

/*
    三维形体的表面积
    https://leetcode-cn.com/problems/surface-area-of-3d-shapes/

    给你一个 n * n 的网格 grid ，上面放置着一些 1 x 1 x 1 的正方体。
    每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
    放置好正方体后，任何直接相邻的正方体都会互相粘在一起，形成一些不规则的三维形体。
    请你返回最终这些形体的总表面积。
    注意：每个形体的底面也需要计入表面积中。
    
    示例 1：
            2
        输入：grid = [[2]]
        输出：10
    示例 2：
            1 2
            3 4
        输入：grid = [[1,2],[3,4]]
        输出：34
    示例 3：
            1 0
            0 2
        输入：grid = [[1,0],[0,2]]
        输出：16
    示例 4：
            1 1 1
            1 0 1
            1 1 1
        输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
        输出：32
    示例 5：
            2 2 2
            2 1 2
            2 2 2
        输入：grid = [[2,2,2],[2,1,2],[2,2,2]]
        输出：46
    
    提示：
        n == grid.length
        n == grid[i].length
        1 <= n <= 50
        0 <= grid[i][j] <= 50

 */
public class SurfaceAreaOf3DShapes {

    /*
        单独计算每一个 V[i][j] 所贡献的表面积，将总面积加起来即可
        如果当前格子不为 0 ，那么顶面和底面贡献 + 2
            侧面则考虑与周围块的关系
            以下面为例
                 1 2 5
                 8 7 3
                 1 4 6
            以 7 为例
            上 2，所以贡献了 5
            左 8，所以贡献了 0
            右 3，贡献了 4
            下 4，贡献了 3
            总贡献为 5 + 0 + 4 + 3 + 2 = 14
            结果：
                5 ms, 14.34%
                38.2 MB, 27.13%
     */
    public int surfaceArea(int[][] grid) {
        int length = grid.length;

        int result = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                int value = grid[i][j];

                // 如果不为 0，则有柱体
                if (value > 0) {
                    // 加上上下两个面
                    result += 2;
                    // 判断左面的贡献
                    if (i - 1 < 0) {
                        result += value;
                    } else {
                        result += Math.max(0, value - grid[i - 1][j]);
                    }
                    // 右
                    if (i + 1 >= length) {
                        result += value;
                    } else {
                        result += Math.max(0, value - grid[i + 1][j]);
                    }
                    // 上
                    if (j - 1 < 0) {
                        result += value;
                    } else {
                        result += Math.max(0, value - grid[i][j - 1]);
                    }
                    // 下
                    if (j + 1 >= length) {
                        result += value;
                    } else {
                        result += Math.max(0, value - grid[i][j + 1]);
                    }

                }
            }
        }
        return result;
    }

}
