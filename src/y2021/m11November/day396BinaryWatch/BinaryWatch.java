package y2021.m11November.day396BinaryWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.11.07
 */

/*
    二进制手表
    https://leetcode-cn.com/problems/binary-watch/

    二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。

    例如，下面的二进制手表读取 "3:25" 。
    给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。
    你可以 按任意顺序 返回答案。
    小时：8  4  2  1
               ·  ·
    分钟：32 16  8 4 2  1
             ·  ·      ·
    小时不会以零开头：

    例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
    分钟必须由两位数组成，可能会以零开头：

    例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。

    示例 1：
        输入：turnedOn = 1
        输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
    示例 2：
        输入：turnedOn = 9
        输出：[]

    提示：
        0 <= turnedOn <= 10
 */
public class BinaryWatch {

    /*
        枚举法，计算所有的时间和分钟，一共 720 种可能，记录每一种的灯的数量即可
        结果：
            10 ms, 55.61%
            38.5 MB, 32.40%
     */
    public List<String> readBinaryWatch(int turnedOn) {
        // 初始化答案列表
        List<String> ans = new ArrayList<>();

        // 遍历小时和分钟，查看两个数字中二进制位 1 的个数
        for (int h = 0; h < 12; ++h) {
            for (int m = 0; m < 60; ++m) {
                // 如果符合。加入结果集合
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        // 返回答案即可
        return ans;
    }
}
