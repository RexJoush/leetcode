package y2022.m04April.day556DailyTemperatures;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Rex Joush
 * @time 2022.04.16
 */

/*
    每日温度
    https://leetcode.cn/problems/daily-temperatures/

    给定一个整数数组 temperatures，表示每天的温度，返回一个数组 answer，
    其中 answer[i] 是指在第 i 天之后，才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。

    示例 1:
        输入: temperatures = [73,74,75,71,69,72,76,73]
        输出:[1,1,4,2,1,1,0,0]
    示例 2:
        输入: temperatures = [30,40,50,60]
        输出:[1,1,1,0]
    示例 3:
        输入: temperatures = [30,60,90]
        输出: [1,1,0]

    提示：
        1 <= temperatures.length <= 10^5
        30 <= temperatures[i]<= 100

 */
public class DailyTemperatures {

    /*
        方法一：类动态规划
        首先，数组的最后一个元素一定不存在比他更大的温度，直接为 0
        往前推一天
            如果 T[i-1] < T[i], 那么 res[i-1] = 1, 即下一个更大的温度即为今天
            如果 T[i-1] > T[i], 那么 res[i-1] = 0, 即不存在更高的温度
        一般情况
            当 T[i-1] < T[i], 那么 res[i-1] = 1,
            当 T[i-1] > T[i], 那么分两种情况
                res[i] = 0, 则，res[i-1] = 0;
                res[i] != 0, 则, 比较 T[i-1] 与 T[i + res[i]], 即比i天恰好温度高的日子，继续比较
        结果：
            7 ms, 99.96%
            50.7 MB, 94.10%
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];

        // 最后一天为 0
        result[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n) {
                // 如果当前比后一天小，那么直接等于两者坐标之差
                if (temperatures[i] < temperatures[j]) {
                    result[i] = j - i;
                    break;
                } else if (result[j] == 0) {
                    // 如果后面等于0，则直接等于 0
                    result[i] = 0;
                    break;
                }
                // j 往后挪
                j += result[j];
            }
        }
        return result;
    }

    /*
        方法二：单调栈
            维护一个单调递减栈，遍历数组
            如果栈为空或当前元素小于等于栈顶元素，那么直接入栈
            反之，说明当前栈顶元素的下一个最大温度已经找到，计算结果即可
        结果：
            22 ms, 89.58%
            56.8 MB, 18.47%
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            // 出栈
            if (!deque.isEmpty() && temperatures[i] > temperatures[deque.peek()]) {
                int pop = deque.pop();
                result[pop] = i - pop;
            }
            deque.push(i);
        }
        return result;
    }
}
