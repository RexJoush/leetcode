package y2022.m04April.day565CourseScheduleIII;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Rex Joush
 * @time 2022.04.25
 */

/*
    课程表 III
    https://leetcode.cn/problems/course-schedule-iii/
    
    这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
    你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
    返回你最多可以修读的课程数目。
    
    示例 1：
        输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
        输出：3
        解释：
        这里一共有 4 门课程，但是你最多可以修 3 门：
        首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
        第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
        第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
        第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
    示例 2：
        输入：courses = [[1,2]]
        输出：1
    示例 3：
        输入：courses = [[3,2],[4,3]]
        输出：0
    
    提示:
        1 <= courses.length <= 10^4
        1 <= durationi, lastDayi <= 10^4

 */
public class CourseScheduleIII {

    /*
        贪心算法
        考虑，将结束日期进行升序排列，优先学习结束日期临近的课程
        再学习的过程中，维护一个总时长，sum
            当遇到某门课程时，有两种情况
            1.学习该课程，可以满足 sum + courses[i][0] < courses[i][1]，则可以进行学习
            2.学习该课程不满足上面等式，那么我们就找到之前学习的最长的课程进行替换
        结果：
            30 ms, 95.39%
            48.8 MB, 69.74%
     */
    public int scheduleCourse(int[][] courses) {
        // 按结束时间升序排列
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        // 记录已经学习过的课程的最大值，使用大根堆来实现
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0; // 记录上课的总时长

        for (int[] course : courses) {
            int du = course[0];
            // 如果满足，则学习该课程
            if (sum + du <= course[1]) {
                sum += du;
                queue.offer(du);
            }
            // 不满足，且小于大根堆的堆顶元素，那么就替换堆顶元素
            else if (!queue.isEmpty() && queue.peek() > du) {
                sum -= queue.poll() - du;
                queue.offer(du);
            }
        }
        // 返回堆的大小即为所学的课程数
        return queue.size();
    }

}
