package y2021.m12December.day437ImageSmoother;

/**
 * @author Rex Joush
 * @time 2021.12.18
 */

/*
    图片平滑器
    https://leetcode-cn.com/problems/image-smoother/

    包含整数的二维矩阵 M 表示一个图片的灰度。
    你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，
    平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。

    示例 1:
        输入:
            [[1,1,1],
             [1,0,1],
             [1,1,1]]
        输出:
            [[0, 0, 0],
             [0, 0, 0],
             [0, 0, 0]]
        解释:
            对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
            对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
            对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0

    注意:
        给定矩阵中的整数范围为 [0, 255]。
        矩阵的长和宽的范围均为 [1, 150]。

 */
public class ImageSmoother {

    /*
        计算每个点相邻九个格的值之和，取均值即可
        结果：
            8 ms, 55.91%
            39.1 MB, 85.93%
     */
    public int[][] imageSmoother(int[][] img) {
        int R = img.length, C = img[0].length;
        int[][] ans = new int[R][C];

        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                int count = 0;
                for (int nr = r - 1; nr <= r + 1; ++nr)
                    for (int nc = c - 1; nc <= c + 1; ++nc) {
                        if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                            ans[r][c] += img[nr][nc];
                            count++;
                        }
                    }
                ans[r][c] /= count;
            }
        return ans;
    }
}
