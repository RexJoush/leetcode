package y2020.m11November.day41ValidBoomerang;

/**
 * @author Joush
 * @time 2020.11.17
 */

/*
    有效的回旋镖
    https://leetcode-cn.com/problems/valid-boomerang/

    回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
    给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。

    示例 1：
        输入：[[1,1],[2,3],[3,2]]
        输出：true
    示例 2：
        输入：[[1,1],[2,2],[3,3]]
        输出：false

    提示：
        points.length == 3
        points[i].length == 2
        0 <= points[i][j] <= 100

 */
public class ValidBoomerang {

    /*
        大佬想法
        k1 = (y2-y1) / (x2-x1)
        k2 = (y3-y1) / (x3-x1)

        当 k1 != k2 时，即表示不在一条线上，使用交叉相乘可以避免除数为0
     */
    public boolean isBoomerang2(int[][] points){
        return (points[1][1] - points[0][1]) * (points[2][0] - points[0][0]) != (points[2][1] - points[0][1]) * (points[1][0] - points[0][0]);
    }

    public boolean isBoomerang(int[][] points) {

        // 如果出现重复的点，返回 false
        if (points[0][0] == points[1][0] && points[0][1] == points[1][1]){
            return false;
        }
        if (points[0][0] == points[2][0] && points[0][1] == points[2][1]){
            return false;
        }
        if (points[1][0] == points[2][0] && points[1][1] == points[2][1]){
            return false;
        }
        try {
            // 计算任两条斜率不相等，即不在一条直线上
            if (((points[2][1] - points[1][1]) * 1.0 / (points[2][0] - points[1][0]) != (points[1][1] - points[0][1]) * 1.0 / (points[1][0] - points[0][0])) &&
                    ((points[2][1] - points[0][1]) * 1.0 / (points[2][0] - points[0][0]) != (points[1][1] - points[0][1]) * 1.0 / (points[1][0] - points[0][0])) &&
                    ((points[2][1] - points[0][1]) * 1.0 / (points[2][0] - points[0][0]) != (points[2][1] - points[1][1]) * 1.0 / (points[2][0] - points[1][0]))) {
                return true;
            }
        }
        // 出现了除零异常，表示两点一线，直接返回 false
        catch (Exception e){
            return false;
        }
        return false;
    }

}
