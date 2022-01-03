package y2022.m01January.day452LargestTriangleArea;

/**
 * @author Rex Joush
 * @time 2022.01.02
 */

/*
    最大三角形面积
    https://leetcode-cn.com/problems/largest-triangle-area/

    给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。

    示例:
        输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
        输出: 2
        解释: 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。

    注意:
        3 <= points.length <= 50.
        不存在重复的点。
         -50 <= points[i][j] <= 50.
        结果误差值在 10^-6 以内都认为是正确答案。

 */
public class LargestTriangleArea {

    /*
        三重循环暴力解即可
        主要是求三角形面积
        方法一：行列式法
        方法二：鞋带公式
        方法三：海伦公式
     */
    public double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i + 1; j < N; ++j)
                for (int k = j + 1; k < N; ++k)
                    ans = Math.max(ans, getArea1(points[i], points[j], points[k]));
        return ans;
    }

    /*
        行列式求解面积
        A(a0, b0)  B(a1, b1)  C(a2, b2)

        a = AB
        b = AC

        对应化解为行列式
             1  | a1 - a0   a2 - a0 |
        S = ——— |                   |
             2  | b1 - b0   b2 - b0 |

        结果：
            5 ms, 92.24%
            36.6 MB, 42.39%
     */
    public double getArea1(int[] P, int[] Q, int[] R) {
        // A(a0, b0)      B(a1, b1)      C(a2, b2)
        // P(P[0], P[1])  Q(Q[0], Q[1])  R(R[0], R[1])
        return 0.5 * Math.abs((Q[0] - P[0]) * (R[1] - P[1]) - (Q[1] - P[1]) * (R[0] - P[0]));
    }

    /*
        鞋带公式 (https://en.wikipedia.org/wiki/Shoelace_formula)
        结果：
            5 ms, 92.24%
            36.6 MB, 90.75%
     */
    public double getArea2(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0] * Q[1] + Q[0] * R[1] + R[0] * P[1]
                - P[1] * Q[0] - Q[1] * R[0] - R[1] * P[0]);
    }
}
