package y2021.m11November.day404IslandPerimeter;

/**
 * @author Rex Joush
 * @time 2021.11.15
 */

/*
    岛屿的周长
    https://leetcode-cn.com/problems/island-perimeter/

    给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地，grid[i][j] = 0 表示水域。
    网格中的格子 水平和垂直 方向相连（对角线方向不相连）。
    整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
    岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
    格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

    示例 1：
        输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
        输出：16
        解释：它的周长是上面图片中的 16 个黄色的边
    示例 2：
        输入：grid = [[1]]
        输出：4
    示例 3：
        输入：grid = [[1,0]]
        输出：4

 */
public class IslandPerimeter {

    /*
        遍历每个网格，当前网格的有效边为 4，当上下左右每出现一个 1 时，有效边就 -1
        计算每个点即可。注意控制越界
        结果：
            7 ms, 87.37%
            39.1MB, 97.24%
     */
    public int islandPerimeter(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        int result = 0;
        for (int i = 0 ; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int c = 4;
                    // 左边如果有格子，且为岛屿
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        c--;
                    }
                    // 下边如果有格子，且为岛屿
                    if (j + 1 < col && grid[i][j + 1] == 1) {
                        c--;
                    }
                    // 右边如果有格子，且为岛屿
                    if (i + 1 < row && grid[i + 1][j] == 1) {
                        c--;
                    }
                    // 上边如果有格子，且为岛屿
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        c--;
                    }
                    result += c;
                }
            }
        }

        return result;
    }

}
