package y2022.m04April.day547QueueReconstructionByHeight;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.04.07
 */

/*
    根据身高重建队列
    https://leetcode-cn.com/problems/queue-reconstruction-by-height/

    假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
    请你重新构造并返回输入数组people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。

    示例 1：
        输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
        输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
        解释：
        编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
        编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
        编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
        编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
        编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
        编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
        因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
    示例 2：
        输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
        输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]

    提示：
        1 <= people.length <= 2000
        0 <= hi <= 10^6
        0 <= ki < people.length
        题目数据确保队列可以被重建

 */
public class QueueReconstructionByHeight {

    /*
        贪心思想，先按身高排队，这样保证高的在前
        此时，在使用插入排序的思想，按照个数进行排队
        如下例子 [[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]
            第一次插入 [7,0] 到第 0 个位置, [[7,0]]
            第二次插入 [7,1] 到第 1 个位置, [[7,0], [7,1]]
            第三次插入 [6,1] 到第 1 个位置, [[7,0], [6,1], [7,1]]
            第四次插入 [5,0] 到第 0 个位置, [[5,0], [7,0], [6,1], [7,1]]
            第五次插入 [5,2] 到第 2 个位置, [[5,0], [7,0], [5,2], [6,1], [7,1]]
            第六次插入 [4,4] 到第 4 个位置, [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
        结果：
            8 ms, 35.31%
            42.4 MB, 16.72%
     */
    public int[][] reconstructQueue(int[][] people) {
        // 按身高优先排序，再按个数降序排列
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        List<int[]> list = new LinkedList<>();

        // 按顺序插入即可
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[people.length][]);
    }
}