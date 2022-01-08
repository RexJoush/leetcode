package y2022.m01January.day458ProjectionAreaOf3DShapes;

/**
 * @author Rex Joush
 * @time 2022.01.08
 */

/*
    三维形体投影面积
    https://leetcode-cn.com/problems/projection-area-of-3d-shapes/

    在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
    每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
    现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
    投影就像影子，将三维形体映射到一个二维平面上。
    在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
    返回所有三个投影的总面积。

    示例 1：
        输入：[[2]]
        输出：5
    示例 2：
        输入：[[1,2],[3,4]]
        输出：17
        解释：
        这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
    示例 3：
        输入：[[1,0],[0,2]]
        输出：8
    示例 4：
        输入：[[1,1,1],[1,0,1],[1,1,1]]
        输出：14
    示例 5：
        输入：[[2,2,2],[2,1,2],[2,2,2]]
        输出：21

    提示：
        1 <= grid.length = grid[0].length<= 50
        0 <= grid[i][j] <= 50

 */
public class ProjectionAreaOf3DShapes {

    /*
        数学方法
        从顶部看，俯视图为网格中的非零值的个数
        侧视图，由该形状每一行的最大值形成
        前视图，由该形状每一列的最大值形成
        如:
            1   2
            3   4
            顶部俯视图为 4，因为有 4 个非零值
            侧视图为 2 + 4，两行的最大值
            前视图为 3 + 4，两列的最大值

        结果：
            1 ms, 100.00%
            38.3 MB, 6.06%
     */
    public int projectionArea(int[][] grid) {
        int sum1 = 0; // 俯视图
        int sum2 = 0; // 侧视图
        int sum3 = 0; // 主视图
        for (int i = 0; i < grid.length; i++) {

            int max1 = grid[i][0]; // 行最大值
            int max2 = grid[0][i]; // 列最大值
            for (int j = 0; j < grid[i].length; j++) {

                // 如果当前值不为 0, 俯视图加 1
                if (grid[i][j] != 0) {
                    sum1++;
                }
                // 找到每一行的最大值
                if (max1 < grid[i][j]) {
                    max1 = grid[i][j];
                }
                // 找到每一列的最大值
                if (max2 < grid[j][i]) {
                    max2 = grid[j][i];
                }
            }
            // 加上侧视图
            sum2 += max1;
            // 加上正视图
            sum3 += max2;
        }

        // 返回三个之和
        return sum1 + sum2 + sum3;
    }
}
