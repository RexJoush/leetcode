package y2022.m03March.day515MakingALargeIsland;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2022.03.06
 */

/*
    最大人工岛
    https://leetcode-cn.com/problems/making-a-large-island/

    给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格'0 变成'1 。
    返回执行此操作后，grid 中最大的岛屿面积是多少？
    岛屿 由一组上、下、左、右四个方向相连的'1 形成。

    示例 1:
        输入: grid = [[1, 0], [0, 1]]
        输出: 3
        解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
    示例 2:
        输入: grid = [[1, 1], [1, 0]]
        输出: 4
        解释: 将一格0变成1，岛屿的面积扩大为 4。
    示例 3:
        输入: grid = [[1, 1], [1, 1]]
        输出: 4
        解释: 没有0可以让我们变成1，面积依然为 4。

    提示：
        n == grid.length
        n == grid[i].length
        1 <= n <= 500
        grid[i][j] 为 0 或 1

 */
public class MakingALargeIsland {

    /*
        回溯，将所有的岛屿标记为索引编号即可
     */
    public int largestIsland(int[][] grid) {

        int m = grid.length;

        boolean[][] used = new boolean[m][m];

        int index = 1;
        // 记录每个索引所对应的面积值
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                // 标记每个岛屿，并且更新此过程的最大单体岛屿，防止出现整个数组全是岛屿的情况
                int temp = dfs(grid, i, j, index, used);
                max = Math.max(max, temp);
                // 记录当前索引所对应的岛屿的面积
                if (temp != 0) {
                    map.put(index, temp);
                    index++;
                }
            }
        }

        // 计算，每遇到一个 0，就将此变为岛屿，并加上，上下左右格子的索引值所对应的岛屿的面积个数
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int result = 1;
                    /*
                    此处使用集合去重，防止下面的情况出现，如果不去重，则将索引为5的岛屿加了四次，重复计算
                        5 5 5
                        5 0 5
                        5 5 5
                     */
                    if (i - 1 >= 0) { set.add(grid[i - 1][j]); }
                    if (i + 1 < m) { set.add(grid[i + 1][j]); }
                    if (j - 1 >= 0) { set.add(grid[i][j - 1]); }
                    if (j + 1 < m) { set.add(grid[i][j + 1]); }
                    for (Integer integer : set) { result += map.get(integer); }
                    set.clear();
                    // 更新最大值
                    max = Math.max(max, result);
                }
            }
        }
        // 返回最大值
        return max;
    }

    // 将每个节点标记为索引值，并返回当前索引的岛屿大小
    public int dfs(int[][] grid, int i, int j, int index, boolean[][] used) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid.length) {
            return 0;
        }
        if (grid[i][j] == 0 || used[i][j]) {
            return 0;
        }
        int count = 1;
        used[i][j] = true;
        // 将当前节点标记为索引值
        grid[i][j] = index;

        count += dfs(grid, i - 1, j, index, used);
        count += dfs(grid, i + 1, j, index, used);
        count += dfs(grid, i, j - 1, index, used);
        count += dfs(grid, i, j + 1, index, used);
        return count;
    }
}
