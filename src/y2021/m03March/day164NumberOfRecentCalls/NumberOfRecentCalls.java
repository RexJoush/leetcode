package y2021.m03March.day164NumberOfRecentCalls;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Rex Joush
 * @time 2021.03.20
 */

/*
    最近的请求次数
    https://leetcode-cn.com/problems/number-of-recent-calls/

    写一个 RecentCounter 类来计算特定时间范围内最近的请求。
    请你实现 RecentCounter 类：
        RecentCounter() 初始化计数器，请求数为 0 。
        int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
        保证 每次对 ping 的调用都使用比之前更大的 t 值。

    示例：
        输入：
        ["RecentCounter", "ping", "ping", "ping", "ping"]
        [[], [1], [100], [3001], [3002]]
        输出：
        [null, 1, 2, 3, 3]
    解释：
        RecentCounter recentCounter = new RecentCounter();
        recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
        recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
        recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
        recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3

    提示：
        1 <= t <= 10^9
        保证每次对 ping 调用所使用的 t 值都 严格递增
        至多调用 ping 方法 10^4 次

 */
public class NumberOfRecentCalls {

    private Queue<Integer> queue;

    /*
        使用队列，队头始终保持最远的请求
        结果：
            21 ms, 56.96%
            46.8 MB, 54.30%
     */
    public NumberOfRecentCalls() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        // 添加当前请求
        queue.add(t);
        // 当有新的请求到来时，从队头开始判断，如果时间超过了 3000 ms，则出队，保证队列中始终只有最近 3000 ms 的请求
        while (!queue.isEmpty() && t - queue.peek() > 3000) {
            queue.poll();
        }
        // 当前队列的 size 就表示 3000 ms 内的请求数
        return queue.size();
    }
}
