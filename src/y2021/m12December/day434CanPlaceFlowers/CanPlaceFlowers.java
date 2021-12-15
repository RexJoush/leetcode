package y2021.m12December.day434CanPlaceFlowers;

/**
 * @author Rex Joush
 * @time 2021.12.15
 */

/*
    种花问题
    https://leetcode-cn.com/problems/can-place-flowers/

    假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。
    可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
    给你一个整数数组 flowerbed 表示花坛，
    由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。
    另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。

    示例 1：
        输入：flowerbed = [1,0,0,0,1], n = 1
        输出：true
    示例 2：
        输入：flowerbed = [1,0,0,0,1], n = 2
        输出：false

    提示：
        1 <= flowerbed.length <= 2 * 10^4
        flowerbed[i] 为 0 或 1
        flowerbed 中不存在相邻的两朵花
        0 <= n <= flowerbed.length

 */
public class CanPlaceFlowers {

    /*
        遍历计算每两个 0 之间的个数
        发现，当两个 1 之间 0 的个数为 n 个时，可填充的 1 的个数为 (n - 1) / 2
        由于， 0 0 1， 1 0 0，这种特殊情况，所以预设开头和结尾均有一个 0 即可避免处理特殊
        结果：
            1 ms, 89.14%
            40.2 MB, 5.16%
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        // 预设开头有一个 0
        int count = 1;
        // 计算每两个 1 之间 0 的个数
        for (int i : flowerbed) {
            // 当遇见 1 时，计算可以种的的个数，同时将 count 置 0
            if (i == 1) {
                if (count > 2) {
                    n -= (count - 1) / 2;
                }
                count = 0;
            } else {
                count++;
            }
        }
        // 预设结尾有多余的一个 0
        count++;
        n -= (count - 1) / 2;
        return n <= 0;
    }
}
