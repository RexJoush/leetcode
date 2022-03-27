package y2022.m03March.day512MergeInterval;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.03.03
 */

/*
    合并区间
    https://leetcode-cn.com/problems/merge-intervals/
    
    以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
    请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

    示例 1：
        输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
        输出：[[1,6],[8,10],[15,18]]
        解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
        示例 2：
        输入：intervals = [[1,4],[4,5]]
        输出：[[1,5]]
        解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。

    提示：
        1 <= intervals.length <= 10^4
        intervals[i].length == 2
        0 <= starti <= endi <= 10^4

 */
public class MergeInterval {

    /*
        自己写的位图
        结果：
            2 ms, 100.00%
            46.2 MB, 34.03%
     */
    public int[][] merge3(int[][] intervals) {
        BitSet bits = new BitSet();

        for (int[] interval : intervals) {
            int start = interval[0] * 2;
            int end = interval[1] * 2 + 1;

            bits.set(start, end, true);
        }
        List<int[]> result = new ArrayList<>();
        int index = 0;
        while (index < bits.length()) {
            // 获取从 index 开始的第一个为 1 的值
            int start = bits.nextSetBit(index);
            // 获取从 start 开始第一个不为 1 的值
            int end = bits.nextClearBit(start);
            // 封装结果
            int[] temp = new int[]{start / 2, (end - 1) / 2};
            result.add(temp);
            // 更新起始点
            index = end;
        }
        return result.toArray(new int[result.size()][]);
    }

    /*
        方法二：位图
     */
    /**
     * BitSet
     * set(start, end, bool): [start, end) 这些位设置为bool值
     * nextClearBit(startIndex): 返回从startIndex开始的第一个为false的位的索引, 和nextSetBit相反
     */
    public int[][] merge2(int[][] intervals) {
        BitSet bit = new BitSet();

        int max = 0;
        // 把时间间隔全部摊到轴上
        for (int i = 0; i < intervals.length; i++) {
            // *2: 使这种在数字上连续的区间不能被合并  [1,2] [3,4], 这种例子是不能做合并的
            int start = 2 * intervals[i][0];
            // end加上一个1, 防止类似 [1,4] [5,5] 存在开始区间是结束区间的例子出错
            //    目的: 因为 bit.set()时区间是 [start, end), [5, 5]这样的数据在set [10, 10]时实际上就没效果
            //      没加1, nextSetBit时会取到-1, 在 nextClearBit 就会产生 IndexOutOfBoundsException: fromIndex < 0: -1
            int end = 2 * intervals[i][1] + 1;
            max = Math.max(max, end);

            // start end
            //   ↓   ↓
            // 0011110001111
            bit.set(start, end, true);
        }

        int cnt = 0;
        int index = 0;
        while (index < max) {
            int start = bit.nextSetBit(index);
            // 和set时相反, 现在的 (end-1)/2 就是区间合并后的末时间
            int end = bit.nextClearBit(start);

            // 借用intervals的空间存储时间结果区间, 因为intervals.length 肯定 >= 结果数组的长度
            intervals[cnt++] = new int[]{start / 2, (end - 1) / 2};

            //     index, 让index重新去找下一个时间的开始
            //       ↓
            // 0011110001111
            index = end;
        }

        // 封装cnt个结果集, intervals[0, cnt) 已经被我们用合并后的区间重置了
        int[][] res = new int[cnt][2];
        for (int i = 0; i < cnt; i++) {
            res[i] = intervals[i];
        }

        return res;
    }

    /*
        方法一：排序，递增遍历，满足就加入结果
        结果：
            8 ms, 37.30%
            46.4 MB, 18.35%
     */
    public static int[][] merge(int[][] intervals) {

        int n = intervals.length;
        // 按区间起点增量排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        // 遍历排序完后的区间
        int index = 0;
        int start = intervals[index][0];
        int end = intervals[index][1];
        while (index < n) {
            // 如果满足，就区间一直往后合并
            while (index + 1 < n && end >= intervals[index + 1][0]) {
                // 更新区间结尾, 如果新的结尾没有当前大，则继续往后，如 [[1,10][2,6]]
                end = Math.max(intervals[index + 1][1], end);
                index++;
            }
            // 不满足结束了，添加一段区间
            int[] temp = new int[]{start, end};
            res.add(temp);
            index++;
            // 更新新的起始和结束为下一段区间
            if (index < n) {
                start = intervals[index][0];
                end = intervals[index][1];
            }
        }
        // 返回结果
        return res.toArray(new int[res.size()][]);
    }
}
