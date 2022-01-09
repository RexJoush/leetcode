package y2022.m01January.day459FairCandySwap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2022.01.09
 */

/*
    公平的糖果交换
    https://leetcode-cn.com/problems/fair-candy-swap/

    爱丽丝和鲍勃拥有不同总数量的糖果。给你两个数组 aliceSizes 和 bobSizes ，aliceSizes[i] 是爱丽丝拥有的第 i 盒糖果中的糖果数量，bobSizes[j] 是鲍勃拥有的第 j 盒糖果中的糖果数量。
    两人想要互相交换一盒糖果，这样在交换之后，他们就可以拥有相同总数量的糖果。一个人拥有的糖果总数量是他们每盒糖果数量的总和。
    返回一个整数数组 answer，其中 answer[0] 是爱丽丝必须交换的糖果盒中的糖果的数目，answer[1] 是鲍勃必须交换的糖果盒中的糖果的数目。如果存在多个答案，你可以返回其中 任何一个 。题目测试用例保证存在与输入对应的答案。

    示例 1：
        输入：aliceSizes = [1,1], bobSizes = [2,2]
        输出：[1,2]
    示例 2：
        输入：aliceSizes = [1,2], bobSizes = [2,3]
        输出：[1,2]
    示例 3：
        输入：aliceSizes = [2], bobSizes = [1,3]
        输出：[2,3]
    示例 4：
        输入：aliceSizes = [1,2,5], bobSizes = [2,4]
        输出：[5,4]

    提示：
        1 <= aliceSizes.length, bobSizes.length <= 10^4
        1 <= aliceSizes[i], bobSizes[j] <= 10^5
        爱丽丝和鲍勃的糖果总数量不同。
        题目数据保证对于给定的输入至少存在一个有效答案。

 */
public class FairCandySwap {

    /*
        题目的意思是，alice 和 bob 的糖果数组中，交换一个，可以使得两人的总糖果数相同
        方法：哈希表
            设最终结果为 {x, y}
            则 sumAlice + y - x = sumBob + x - y
            即
                    x = y + (sumAlice - sumBob) / 2
            即对于 bobSizes 中任一个数 y，只要 aliceSizes 中存在一个数 x 满足上式即可
        结果：
            14 ms, 61.25%
            39.6 MB, 82.36%
     */
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {

        // 计算两个和
        int sumAlice = Arrays.stream(aliceSizes).sum();
        int sumBob = Arrays.stream(bobSizes).sum();

        Set<Integer> set = new HashSet<>();

        // 将 alice 的值放入哈希表中
        for (int aliceSize : aliceSizes) {
            set.add(aliceSize);
        }
        int temp = (sumAlice - sumBob) / 2;
        for (int bobSize : bobSizes) {
            if (set.contains(bobSize + temp)) {
                return new int[]{bobSize + temp, bobSize};
            }
        }
        return null;
    }
}
