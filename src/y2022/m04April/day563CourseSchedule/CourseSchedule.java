package y2022.m04April.day563CourseSchedule;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.04.23
 */

/*
    课程表
    https://leetcode.cn/problems/course-schedule/
    
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
    其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则必须先学习课程 bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false。

    示例 1：
        输入：numCourses = 2, prerequisites = [[1,0]]
        输出：true
        解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
    示例 2：
        输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
        输出：false
        解释：总共有 2 门课程。学习课程 1 之前，你需要先完成 课程 0；
        并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。

    提示：
        1 <= numCourses <= 10^5
        0 <= prerequisites.length <= 5000
        prerequisites[i].length == 2
        0 <= ai, bi < numCourses
        prerequisites[i] 中的所有课程对 互不相同

 */
public class CourseSchedule {

    /*
        方法二：优化的拓扑排序
        将所有入度为0的节点加入queue，同时，将此queue的节点进行顺序取出，将与之相关的入度均减1
        使用 list 存储与之相关的节点即可
        结果:
            7 ms, 21.93%
            42.2 MB, 8.99%
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (prerequisites.length < 2) {
            return true;
        }
        // 邻接表,记录了所有的入度节点
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 定义学习的顺序队列
        Queue<Integer> queue = new LinkedList<>();
        // 记录所有节点的入度
        int[] in = new int[numCourses];

        // [2,3] 则，3 的入度为 0,且 3 的邻接表保存了 3->2
        for (int[] prerequisite : prerequisites) {
            in[prerequisite[0]]++;
            List<Integer> table = map.getOrDefault(prerequisite[1], new ArrayList<>());
            table.add(prerequisite[0]);
            map.put(prerequisite[1], table);
        }
        // 将入度为 0 的节点加入队列
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0; // 已经学习的课程数量
        while (!queue.isEmpty()) {
            int top = queue.poll();
            count++;
            // 获取当前节点的指向节点列表
            List<Integer> list = map.get(top);
            if (list == null) {
                continue;
            }
            // 遍历指向的节点列表
            for (Integer integer : list) {
                in[integer]--; // 出度 -1
                // 如果出度为 0 了，就加入 queue 待判断
                if (in[integer] == 0) {
                    queue.add(integer);
                }
            }
        }
        // 所学的课程和要求的是否一样
        return count == numCourses;
    }

    /*
        方法一：拓扑排序
        关键路径，定义 map，记录所有节点的入度，找到入度为 0 的点，将此节点指向的所有点的入度减一
        当某节点的入度为 0 时，将此节点从节点 map 中移除，当节点 map 大小为 0 时，说明拓扑排序有效
        否则，当某一刻节点 map 不再减小时，说明拓扑排序无效
        结果:
            53 ms, 5.16%
            43.1 MB, 5.03%
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        // 0 和 1 门课一定可以完成
        if (n < 2) {
            return true;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Map<String, Integer[]> dir = new HashMap<>();
        // 记录所有点的入度
        for (int[] prerequisite : prerequisites) {
            int value = map.getOrDefault(prerequisite[1], 0) + 1;
            // 记录所有待判断的边加入 map，"0->1" -> [0,1]
            map.put(prerequisite[1], value);
            dir.put(prerequisite[0] + "->" + prerequisite[1], new Integer[]{prerequisite[0], prerequisite[1]});
        }

        // 记录前一次 map 的大小
        int pre = map.size();
        while (map.size() != 0) {
            // 待删除的边
            List<String> rm = new LinkedList<>();
            // 遍历待判断的边
            for (Map.Entry<String, Integer[]> entry : dir.entrySet()) {
                // 如果不包含此 key，说明此节点的入度为 0，将之指向的点入度减一
                if (!map.containsKey(entry.getValue()[0])) {
                    // 获取指向的节点的 key
                    int key = entry.getValue()[1];
                    if (map.get(key) == null) {
                        continue;
                    }
                    // 获取指向节点的入度
                    int value = map.get(key);
                    // 如果为 1，将此节点移除
                    if (value == 1) {
                        map.remove(key);
                    } else {
                        // 否则将此节点的入度 -1
                        map.put(key, value - 1);
                    }
                    // 将当前使用完毕的边加入待删除的集合
                    rm.add(entry.getKey());
                }
            }
            // 将使用过的边删除
            for (String s : rm) {
                dir.remove(s);
            }
            // 如果节点个数不变，说明存在环，返回 false
            if (map.size() == pre) {
                return false;
            }
            pre = map.size();
        }
        // 所有节点均删除了，返回 true
        return true;
    }
}
