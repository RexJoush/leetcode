package y2022.m05May.day587BrickWall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2022.05.17
 */

/*
    砖墙
    https://leetcode.cn/problems/brick-wall/

    你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和相等。
    你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
    给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。

    示例 1：
        输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
        输出：2
    示例 2：
        输入：wall = [[1],[1],[1]]
        输出：3

    提示：
        n == wall.length
        1 <= n <= 10^4
        1 <= wall[i].length <= 10^4
        1 <= sum(wall[i].length) <= 2 * 10^4
        对于每一行 i ，sum(wall[i]) 是相同的
        1 <= wall[i][j] <= 2^31 - 1

 */
public class BrickWall {

    /*
        求穿越最少的砖块，也即穿越最多的间隙
        通过前缀和的形式，求每一个位置的间隙数，用哈希表记录即可
        结果：
            11 ms, 95.53%
            45.7 MB, 58.12%
     */
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> integers : wall) {
            int temp = 0;
            // 记录每一个位置间隙的数量，最后一个不记录
            for (int j = 0; j < integers.size() - 1; j++) {
                temp += integers.get(j);
                int value = map.getOrDefault(temp, 0) + 1;
                map.put(temp, value);
                max = Math.max(max, value);
            }
        }
        // 墙高减去最大的间隙数量即可
        return wall.size() - max;
    }

}
