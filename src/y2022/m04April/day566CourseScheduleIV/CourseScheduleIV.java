package y2022.m04April.day566CourseScheduleIV;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.04.26
 */

/*
    课程表 IV
    https://leetcode.cn/problems/course-schedule-iv/
    
    你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1。
    你会得到一个数组 prerequisite ，其中 prerequisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
    有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
    先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
    你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条件。
    返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。

    示例 1：
            1 -> 0
        输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
        输出：[false,true]
        解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
    示例 2：
            1 -> 0
             \」 /「
              2
        输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
        输出：[false,false]
        解释：没有先修课程对，所以每门课程之间是独立的。
    示例 3：
        输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
        输出：[true,true]

    提示：
        2 <= numCourses <= 100
        0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
        prerequisites[i].length == 2
        0 <= ai, bi <= n - 1
        ai != bi
        每一对 [ai, bi] 都 不同
        先修课程图中没有环。
        0 <= ui, vi <= n - 1
        ui != vi

 */
public class CourseScheduleIV {

    /*
        Floyd 算法，标识两点之间的可达性，如果可达，说明为前驱课程，否则为非前驱课程
        结果：
            23 ms, 72.65%
            46 MB, 43.73%
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] graph = new boolean[numCourses][numCourses];

        for (int[] prerequisite : prerequisites) {
            // 标识 0 -> 1 表示 0 为 1 的前驱课程
            graph[prerequisite[0]][prerequisite[1]] = true;
        }

        // floyd 求解
        for (int k = 0; k < numCourses; ++k) {
            for (int i = 0; i < numCourses; ++i) {
                for (int j = 0; j < numCourses; ++j) {
                    // 如果 i，j 能经由中间节点 k 到达，那么标识为可达，即 i 为 j 的前驱节点
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        // 遍历获取结果即可
        List<Boolean> result = new LinkedList<>();
        for (int[] query : queries) {
            result.add(graph[query[0]][query[1]]);
        }
        return result;
    }

}
