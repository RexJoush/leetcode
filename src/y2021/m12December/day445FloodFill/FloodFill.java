package y2021.m12December.day445FloodFill;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2021.12.26
 */

/*
    图像渲染
    https://leetcode-cn.com/problems/flood-fill/

    有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
    给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，
    让你重新上色这幅图像。
    为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
    接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，
    重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
    最后返回经过上色渲染后的图像。

    示例 1:
        输入:
        image = [[1,1,1],[1,1,0],[1,0,1]]
        sr = 1, sc = 1, newColor = 2
        输出: [[2,2,2],[2,2,0],[2,0,1]]
        解析:
        在图像的正中间，(坐标(sr,sc)=(1,1)),
        在路径上所有符合条件的像素点的颜色都被更改成2。
        注意，右下角的像素没有更改为2，
        因为它不是在上下左右四个方向上与初始点相连的像素点。

    注意:
        image 和 image[0] 的长度在范围 [1, 50] 内。
        给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
        image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。

 */
public class FloodFill {

    /*
        递归遍历即可
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        draw2(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    /*
        方法二：优化方法一
        判断方式修改，无需记录，判断如果和新颜色相同，就返回
        结果：
            0 ms, 100.00%
            39.2 MB, 56.98%
     */
    public void draw(int[][] image, int sr, int sc, int oldColor, int newColor) {
        // 判断是否越界，是否已经遍历过，是否与原点相同
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length
        || image[sr][sc] != oldColor || newColor == oldColor) {
            return;
        }
        image[sr][sc] = newColor;

        draw(image, sr - 1, sc, oldColor, newColor);
        draw(image, sr + 1, sc, oldColor, newColor);
        draw(image, sr, sc - 1, oldColor, newColor);
        draw(image, sr, sc + 1, oldColor, newColor);
    }

    /*
        方法一：使用哈希表记录哪些点遍历过
        结果：
            14 ms, 14.33%
            39.4 MB, 27.03%
     */
    Set<String> set = new HashSet<>();
    public void draw2(int[][] image, int sr, int sc, int oldColor, int newColor) {
        // 当前点的横纵坐标
        String s = String.valueOf(sr) + sc;
        // 加入遍历集合
        set.add(s);
        // 如果和原点相同，那就更新颜色
        if (image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;
        } else {
            return;
        }
        // 递归遍历左侧点
        if (sr - 1 >= 0) {
            // 保证没遍历过
            if (!set.contains(String.valueOf(sr - 1) + sc)) {
                draw(image, sr - 1, sc, oldColor, newColor);
            }
        }
        // 递归遍历右侧点
        if (sr + 1 < image.length) {
            if (!set.contains(String.valueOf(sr + 1) + sc)) {
                draw(image, sr + 1, sc, oldColor, newColor);
            }
        }
        // 递归遍历上侧点
        if (sc - 1 >= 0) {
            if (!set.contains(sr + String.valueOf(sc - 1))) {
                draw(image, sr, sc - 1, oldColor, newColor);
            }
        }
        // 递归遍历下侧点
        if (sc + 1 < image[0].length){
            if (!set.contains(sr + String.valueOf(sc + 1))) {
                draw(image, sr, sc + 1, oldColor, newColor);
            }
        }

    }
}
