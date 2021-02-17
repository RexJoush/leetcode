package y2020.m12December.day78MinimumTimeVisitingAllPoints;

/**
 * @author Joush
 * @time 2020.12.24
 */

/*
    访问所有点的最小时间
    https://leetcode-cn.com/problems/minimum-time-visiting-all-points/

    平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。请你计算访问所有这些点需要的最小时间（以秒为单位）。
    你可以按照下面的规则在平面上移动：
    每一秒沿水平或者竖直方向移动一个单位长度，或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。
    必须按照数组中出现的顺序来访问这些点。

    示例 1:
        输入：points = [[1,1],[3,4],[-1,0]]
        输出：7
        解释：一条最佳的访问路径是： [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
        从 [1,1] 到 [3,4] 需要 3 秒
        从 [3,4] 到 [-1,0] 需要 4 秒
        一共需要 7 秒
    示例 2：
        输入：points = [[3,2],[-2,2]]
        输出：5

    提示：
        points.length == n
        1 <= n <= 100
        points[i].length == 2
        -1000 <= points[i][0], points[i][1] <= 1000

 */
public class MinimumTimeVisitingAllPoints {

    /*
        计算每两个点之间的距离，如果两个点在一条斜线上，就走斜线，否则就走最大的斜线后走直线
        在一条斜线即，横坐标之差等于纵坐标之差相等
        否则即，横纵坐标的较小值（最大斜线部分） + 横坐标与纵坐标之差（最小直线）
        结果：
            1 ms, 96.11%
            37.9 MB,91.37%
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;

        // 按顺序遍历每个点计算距离即可
        for (int i = 0; i < points.length - 1; i++) {
            result += getDistance(points[i][0],points[i][1],points[i+1][0],points[i+1][1]);
        }

        return result;
    }

    /**
     * 返回两点之间的巨鹿
     * @param x1 点 A 横坐标
     * @param y1 点 A 纵坐标
     * @param x2 点 B 横坐标
     * @param y2 点 B 纵坐标
     * @return 距离
     */
    public int getDistance(int x1, int y1, int x2, int y2) {
        // 计算 y 轴距离
        int y = Math.abs(y2 - y1);
        // 计算 x 轴距离
        int x = Math.abs(x2 - x1);

        // 如果在一条直线就走直线，否则就最大斜线 + 直线
        return x == y ? x : Math.abs(x-y) + Math.min(x, y);
    }

}
