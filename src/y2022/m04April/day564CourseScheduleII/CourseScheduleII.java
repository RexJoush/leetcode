package y2022.m04April.day564CourseScheduleII;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Rex Joush
 * @time 2022.04.24
 */

/*
    课程表 II
    https://leetcode.cn/problems/course-schedule-ii/

    现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
    给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前必须先选修 bi 。
    例如，想要学习课程 0 ，你需要先完成课程 1，我们用一个匹配来表示：[0,1] 。
    返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回任意一种就可以了。
    如果不可能完成所有课程，返回 一个空数组 。
    
    示例 1：
        输入：numCourses = 2, prerequisites = [[1,0]]
        输出：[0,1]
        解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
    示例 2：
        输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
        输出：[0,2,1,3]
        解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
        因此，一个正确的课程顺序是  [0,1,2,3] 。另一个正确的排序是  [0,2,1,3] 。
    示例 3：
        输入：numCourses = 1, prerequisites = []
        输出：[0]
    
    提示：
        1 <= numCourses <= 2000
        0 <= prerequisites.length <= numCourses * (numCourses - 1)
        prerequisites[i].length == 2
        0 <= ai, bi < numCourses
        ai != bi
        所有[ai, bi] 互不相同

 */
public class CourseScheduleII {

    /*
        方法一：拓扑排序，广度优先搜索
            参考课程表 1 的拓扑排序，记录过程即可
        结果：
            8 ms, 19.19%
            42.2 MB, 85.59%
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            in[prerequisite[0]]++;
            List<Integer> list = map.getOrDefault(prerequisite[1], new ArrayList<>());
            list.add(prerequisite[0]);
            map.put(prerequisite[1], list);
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result.add(poll);
            List<Integer> list = map.get(poll);
            if (list == null) {
                continue;
            }
            for (Integer integer : list) {
                in[integer]--;
                if (in[integer] == 0) {
                    queue.add(integer);
                }
            }
        }
        // 不满足返回空数组
        if (result.size() < numCourses) {
            return new int[]{};
        }
        // 满足返回结果
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
        方法二：广度优先搜索
            分析题目可得，课程情况为一个有向图，若想要同时完成，保证此有向图无环即可
            因此题目转变为，判断此有向图是否有环，有环则满足题意，无环，则不满足
     */
    // 存储有向图
    List<List<Integer>> edges;
    // 存储节点的入度
    int[] in;
    int[] result; // 答案
    int index; // 答案下标
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        in = new int[numCourses];
        result = new int[numCourses];
        index = 0;

        for (int[] prerequisite : prerequisites) {
            // 记录 p[1] -> p[0]
            edges.get(prerequisite[0]).add(prerequisite[1]);
            // 记录入度
            in[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result[index++] = poll; // 放入答案

            for (int v : edges.get(poll)) {
                in[v]--;

            }
        }

    }

}
