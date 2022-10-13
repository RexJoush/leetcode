package y2022.m05May.day588AsteroidCollision;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.05.18
 */

/*
    行星碰撞
    https://leetcode.cn/problems/asteroid-collision/

    给定一个整数数组 asteroids，表示在同一行的行星。
    对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
    找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。

    示例 1：
        输入：asteroids = [5,10,-5]
        输出：[5,10]
        解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
    示例 2：
        输入：asteroids = [8,-8]
        输出：[]
        解释：8 和 -8 碰撞后，两者都发生爆炸。
    示例 3：
        输入：asteroids = [10,2,-5]
        输出：[10]
        解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。

    提示：
        2 <= asteroids.length<= 10^4
        -1000 <= asteroids[i] <= 1000
        asteroids[i] != 0

 */
public class AsteroidCollision {

    /*
        模拟即可
        结果：
            2 ms, 95.80%
            42.6 MB, 43.50%
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();

        for (int asteroid : asteroids) {
            // 空队列，队尾的向左，当前为正，直接推入队列
            if (deque.isEmpty() || deque.peekLast() < 0 || asteroid > 0) {
                deque.offer(asteroid);
                continue;
            }
            boolean add = true;
            // 遇到负数，开始往前撞
            while (!deque.isEmpty() && deque.peekLast() > 0) {
                // 队尾互为相反数，两消，继续下一个
                if (deque.peekLast() == -asteroid) {
                    add = false;
                    deque.pollLast();
                    break;
                }
                // 队尾比他小，撞坏，队尾出列
                if (Math.abs(deque.peekLast()) < Math.abs(asteroid)) {
                    deque.pollLast();
                } else {
                    break;
                }
            }
            if (add && (deque.isEmpty() || deque.peekLast() < 0)) {
                deque.offer(asteroid);
            }
        }

        int[] arr = new int[deque.size()];
        int size = deque.size();
        for (int j = 0; j < size; j++) {
            arr[j] = deque.poll();
        }
        return arr;
    }
}
