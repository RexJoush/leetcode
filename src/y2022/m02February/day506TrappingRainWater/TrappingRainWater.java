package y2022.m02February.day506TrappingRainWater;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Rex Joush
 * @time 2022.02.25
 */

/*
    接雨水
    https://leetcode-cn.com/problems/trapping-rain-water/

    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

    示例 1：
        输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
        输出：6
        解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
    示例 2：
        输入：height = [4,2,0,3,2,5]
        输出：9

    提示：
        n == height.length
        1 <= n <= 2 * 10^4
        0 <= height[i] <= 10^5

 */
public class TrappingRainWater {

    /*
        方法三：双指针法
        考虑，动态规划的思想
        左侧能否接水依赖于右侧较高的柱子，即使当前主柱子不够高，单只要左侧比自己高，右侧也高，那么就一定可以接到水
        而右侧同理
        结果：
            0 ms, 100.00%
            42 MB, 20.13%
     */
    public int trap3(int[] height) {
        // 左右指针
        int left = 0;
        int right = height.length - 1;

        // 记录左右两侧的最高柱子
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;

        while (left < right) {
            // 当前柱子比右侧柱子低，那么能否接到水依赖于左侧，即右侧有个很高的柱子，左侧有可能会接到水
            if (height[left] < height[right]) {
                // 如果当前柱子比最大值高，那么更新最大值
                if (height[left] > leftMax) {
                    leftMax = height[left];
                }
                // 否则，可能接到水，进行接水
                else {
                    result += leftMax - height[left];
                }
                // 柱子右移
                left++;
            }
            // 左侧有个很高的柱子，在右侧操作，右侧有可能接到水
            else {
                // 如果当前柱子比最大值高，那么更新最大值
                if (height[right] > rightMax) {
                    rightMax = height[right];
                }
                // 否则，可能接到水，进行接水
                else {
                    result += rightMax - height[right];
                }
                right--;
            }
        }
        return result;
    }
    /*
        方法二：单调栈
        考虑，单调减栈，当后面柱子比前面柱子低时，是无法接水的，所以使用单调递减栈存储可能储水的柱子
        当找到一根比前面高的柱子，就可以计算接到的雨水
        具体过程
            使用一个栈 s 存储柱子的索引
            从左到右遍历 height
                当栈非空，且 height[i] > height[s.peek()]
                    top = st.pop(); // 拿到当前高度
                    说明当前元素比栈顶元素高，可以形成低洼，接到水
                    计算可接水的宽度和高度
                        distance = i - s.peek() - 1
                        height = min(height[i], height[s.peek()]) - height[top]
                    累加
                        result += distance * height
                将下一个下标入栈
                移动 i 到下一个位置
        结果：
            2 ms, 37.60%
            41.8 MB, 34.34%
     */
    public int trap2(int[] height) {
        // 定义一个单调栈
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        int result = 0;
        while (i < height.length) {
            // 能接到水就一直接，有点贪心的思想
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 获取栈顶元素
                int top = stack.pop();
                // 如果栈空，则说明遍历结束，结束循环
                if (stack.isEmpty()) {
                    break;
                }
                // 计算宽度
                int distance = i - stack.peek() - 1;
                // 计算高度
                int height1 = Math.min(height[i], height[stack.peek()]) - height[top];
                result += distance * height1;
            }
            // 加入索引，往后移动
            stack.push(i++);
        }
        return result;
    }

    /*
        方法一：动态规划
        每个地方能接的雨水个数等于 左右两边的柱子高度较低值 - 本身的高度值
                left_max = 0; right_max = 0;
            从 height[0] 到当前位置，寻找最大值 left_max = max (height[j], left_max);
            从当前位置到 height 末端，寻找最大值 right_max = max (height[j], right_max);
            ans = ans + min(left_max, right_max) - height[i];
        每个位置找到往左去的最高柱子，以及往右去的最高柱子值，考虑使用动态规划，存储位置
        即使用单调栈，提前存储每个位置上从左往右，从右往左看的最大值数组
        以题目所给为例
            height    = [0,1,0,2,1,0,1,3,2,1,2,1]
        从左往右看的当前位置的右边最大值
            left_max  = [0,1,1,2,2,2,2,3,3,3,3,0]
        从右往左看的当前位置的左边最大值
            right_max = [3,3,3,3,3,3,3,3,2,2,2,0]
        将两个数组合并即可得到某个位置左右两侧的最大值
        结果：
            1 ms, 74.83%
            41.5 MB, 51.47%
     */
    public int trap(int[] height) {
        int result = 0;
        int length = height.length;
        // 太短无法接水，直接返回
        if (length < 3) {
            return 0;
        }

        // 从左往右看的最大值数组
        int[] leftMaxArr = new int[length];
        // 从右往左看的最大值数组
        int[] rightMaxArr = new int[length];

        // 初始化左数组的左边和右数组的右边
        leftMaxArr[0] = height[0];
        rightMaxArr[length - 1] = height[length - 1];

        // 计算左侧数组
        for (int i = 1; i < length; i++) {
            leftMaxArr[i] = Math.max(leftMaxArr[i - 1], height[i]);
        }
        // 计算右侧数组
        for (int i = length - 2; i >= 0; i--) {
            rightMaxArr[i] = Math.max(rightMaxArr[i + 1], height[i]);
        }

        // 计算结果
        for (int i = 0; i < length; i++) {
            // 左右两侧的较小值，减去当前值
            result += Math.min(leftMaxArr[i], rightMaxArr[i]) - height[i];
        }
        return result;
    }
}
