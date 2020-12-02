package December20.day56CheckStraightLine;

/**
 * @author Joush
 * @time 2020.12.02
 */

/*
    缀点成线
    https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/

    在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
    请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。

    示例 1：
        输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
        输出：true
    示例 2：
    输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
    输出：false

    提示：
        2 <= coordinates.length <= 1000
        coordinates[i].length == 2
        -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
        coordinates 中不含重复的点

 */
public class CheckStraightLine {

    public static void main(String[] args) {
        int[][] coordinates = new int[][]{{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
        int[][] coordinates2 = new int[][]{{0,0},{0,1},{0,-1}};

//        System.out.println(coordinates[0][0]); // 1
//        System.out.println(coordinates[0][1]); // 1
//        System.out.println(coordinates[1][0]); // 2
//        System.out.println(coordinates[1][1]); // 2
//        System.out.println(coordinates[2][0]); // 3
//        System.out.println(coordinates[2][1]); // 4

        System.out.println(new CheckStraightLine().checkStraightLine(coordinates2));

    }

    public boolean checkStraightLine(int[][] coordinates) {

        // 两个点就直接返回 ture
        if (coordinates.length == 2){
            return true;
        }

        // 计算前两个点的斜率，作为基准斜率
        double k = (coordinates[1][1] - coordinates[0][1]) * 1.0 / (coordinates[1][0] - coordinates[0][0]);

        // 如果斜率为无穷大，即为垂直 x 轴的直线，只需判断所有点是否都在一条垂线上即可。第 78/79 个测试用例无法通过
        if (Double.isInfinite(k)){
            for (int i = 2; i < coordinates.length; i++){
                if (coordinates[i][0] != coordinates[0][0]){
                    return false;
                }
            }
            return true;
        }

        // 非垂直斜率，用每个点与第一个点求斜率，遇到不一样的就直接返回 false
        for (int i = 2; i < coordinates.length; i++){
            // 依次计算每个点与第一个点的斜率
            if ((coordinates[i][1] - coordinates[0][1]) * 1.0 / (coordinates[i][0] - coordinates[0][0]) != k){
                return false;
            }
        }

        return true;
    }

}
