package y2022.m01January.day471MatrixCellsInDistanceOrders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.01.21
 */

/*
    距离顺序排列矩阵单元格
    https://leetcode-cn.com/problems/matrix-cells-in-distance-order/

    给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
    另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
    返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）

    示例 1：
        输入：R = 1, C = 2, r0 = 0, c0 = 0
        输出：[[0,0],[0,1]]
        解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
    示例 2：
        输入：R = 2, C = 2, r0 = 0, c0 = 1
        输出：[[0,1],[0,0],[1,1],[1,0]]
        解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
        [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
    示例 3：
        输入：R = 2, C = 3, r0 = 1, c0 = 2
        输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
        解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
        其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。

    提示：
        1 <= R <= 100
        1 <= C <= 100
        0 <= r0 < R
        0 <= c0 < C

 */
public class MatrixCellsInDistanceOrders {

    /*
        方法一：自定义排序
        将所有节点放入数组中，自定义排序即可
        结果：
            13 ms, 46.20%
            40.4 MB, 63.94%
     */
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {

        // 结果数组
        int[][] result = new int[rows * cols][2];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[index++] = new int[]{i, j};
            }
        }

        // 自定义排序
        Arrays.sort(result, (o1, o2) -> {
            int dis1 = Math.abs(o1[0] - rCenter) + Math.abs(o1[1] - cCenter);
            int dis2 = Math.abs(o2[0] - rCenter) + Math.abs(o2[1] - cCenter);

            return dis1 - dis2;
        });
        return result;
    }

    /*
        方法一：桶排序
        因为结果范围有限，所以计数桶排序可以优化排序的效率
        结果：
            13 ms, 46.20%
            40.4 MB, 63.94%
     */
    public int[][] allCellsDistOrder2(int rows, int cols, int rCenter, int cCenter) {

        // 计算最大距离，即桶的最大个数
        int maxDist = Math.max(rCenter, rows - 1 - rCenter) + Math.max(cCenter, cols - 1 - cCenter);

        // 结果数组
        int[][] result = new int[rows * cols][2];

        // 初始化桶
        List<List<int[]>> bucket = new ArrayList<>();
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new ArrayList<>());
        }

        // 计数并入桶
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int d = Math.abs(i - rCenter) + Math.abs(j - cCenter);
                bucket.get(d).add(new int[]{i, j});
            }
        }

        int index = 0;

        // 遍历桶，生成结果即可
        for (int i = 0; i <= maxDist; i++) {
            for (int[] it : bucket.get(i)) {
                result[index++] = it;
            }
        }

        return result;
    }
}
