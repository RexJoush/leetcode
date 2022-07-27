package y2022.m05May.day572TaskScheduler;

import java.util.Arrays;

/**
 * @author Rex Joush
 * @time 2022.05.02
 */

/*
    任务调度器
    https://leetcode.cn/problems/task-scheduler/
    
    给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
    任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
    在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
    然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
    因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
    你需要计算完成所有任务所需要的 最短时间。

    示例 1：
        输入：tasks = ["A","A","A","B","B","B"], n = 2
        输出：8
        解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
             在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
    示例 2：
        输入：tasks = ["A","A","A","B","B","B"], n = 0
        输出：6
        解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
        ["A","A","A","B","B","B"]
        ["A","B","A","B","A","B"]
        ["B","B","B","A","A","A"]
        ...
        诸如此类
    示例 3：
        输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
        输出：16
        解释：一种可能的解决方案是：
             A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A

    提示：
        1 <= task.length <= 10^4
        tasks[i] 是大写英文字母
        n 的取值范围为 [0, 100]

 */
public class TaskScheduler {

    /*
        此处主要讲解公式的思路
            1.举例，如果有下面任务序列
                [A, A, A, B, B, C], n = 2, 其中 A 的频率最高，即 count = 3;
            所以，两个 A 之间必须间隔两个任务，才能满足最短序列
            因此执行顺序为
                A X X A X X A
            这里的 X 指的是其他字母或者是待命时间，因此上面的执行结果为
                有 count - 1 个 A, 每个 A 需要配 n 个 X, 再加上最后一个 A, 总时间为
                (count - 1) * (n + 1) + 1
            2.又因为，可能存在多个相同的最大值，如 [A, A, A, B, B, B, C, C]
            所以，最后会剩下一个 A 和一个 B, 因此最后需要加上最多任务的个数 maxCount
            3.最后，公式算出的值可能会比数组的长度小，如 [A, B, A, B], n = 0
            因此要和数组长度相比
     */
    public int leastInterval(char[] tasks, int n) {
        // 词频统计数组
        int[] map = new int[26];

        // 统计每个任务出现的次数，并排序，找到最大的
        for (char task : tasks) {
            map[task - 'A']++;
        }
        Arrays.sort(map);

        int maxCount = 1;
        // 记录最大值有几个，因为排序了，所以此处 map[25] 即为最大值
        for (int i = 24; i >= 0; i--) {
            if (map[i] == map[25]) {
                maxCount++;
            } else {
                break;
            }
        }
        // 返回结果
        return Math.max((map[25] - 1) * (n + 1) + maxCount, tasks.length);
    }

}
