package y2021.m03March.day151RectangleOverlap;

/**
 * @author Rex Joush
 * @time 2021.03.07
 */

/*
    矩形重叠
    https://leetcode-cn.com/problems/rectangle-overlap/

    矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。矩形的上下边平行于 x 轴，左右边平行于 y 轴。
    如果相交的面积为 正 ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
    给出两个矩形 rec1 和 rec2 。如果它们重叠，返回 true；否则，返回 false 。

    示例 1：
        输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
        输出：true
    示例 2：
        输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
        输出：false
    示例 3：
        输入：rec1 = [0,0,1,1], rec2 = [2,2,3,3]
        输出：false

    提示：
        rect1.length == 4
        rect2.length == 4
        -109 <= rec1[i], rec2[i] <= 109
        rec1 和 rec2 表示一个面积不为零的有效矩形

 */
public class RectangleOverlap {

    /*
        经画图可得，当两个矩形有重叠时，那么他们在 x, y 轴的投影区间重叠
        y       图一
        |
        |    |————————|
        |    |    |———|————|
        |    |    |   |    |
        |    |————|———|    |
        |         |————————|
        |———————————————————————————————— x
            s1   s2   e1   e2
        共有六种情况
            1. ———————
                        ------  不重叠
            2. ———————
                    ------      重叠
            3. ——————————
                ------          重叠
            4.    ———
                -------         重叠
            5.    ————————
                ------          重叠
            6.        —————
                ----            不重叠
        可以看到，不重叠有两种情况，判断起来更简单即
           e1 <= s2 或 e2 <= s1
        取反即可得到重叠的条件了
        结果：
            0 ms, 100.00%
            35.8 MB, 17.79%
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // 如果矩形为一条线则返回 false
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            return false;
        }
        // x 轴重叠
        boolean x = !(rec1[2] <= rec2[0] || rec2[2] <= rec1[0]);

        // y 轴重叠
        boolean y = !(rec1[3] <= rec2[1] || rec2[3] <= rec1[1]);
        return x && y;
    }

}
