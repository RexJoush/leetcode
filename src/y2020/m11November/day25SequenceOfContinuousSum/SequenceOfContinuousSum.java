package y2020.m11November.day25SequenceOfContinuousSum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2020.11.01
 */

/*

    和为s的连续正数序列 (剑指 Offer 57 - II)
    https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/

    输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

    示例 1：
        输入：target = 9
        输出：[[2,3,4],[4,5]]
    示例 2：
        输入：target = 15
        输出：[[1,2,3,4,5],[4,5,6],[7,8]]

    限制：
        1 <= target <= 10^5

 */
public class SequenceOfContinuousSum {

    /*
        滑动窗口思想，暴力解决

        两个指针，代表左边界和右边界
        计算两指针之间值的和
            如果 sum < target 则窗口需要扩大，右边界向右
            如果 sum = target 则正好，记录结果
            如果 sum > target 则窗口需要缩小，左边界右移

     */

    public int[][] findContinuousSequence(int target) {

        // 左右指针均置1
        int left = 1;
        int right = 1;

        // 计算和的结果
        int sum = 0;

        // 保存结果
        List <int[]> list = new ArrayList<>();

        // 左边界移动到中间时结束
        while (left <= target / 2){

            // 即窗口值不够 target
            if (sum < target){
                sum += right;
                right ++;
            }

            // 窗口值大于 target
            else if (sum > target){
                sum -= left;
                left++;
            }

            // 窗口值等于 target
            else {
                // 新建数组，保存结果
                int[] arr = new int[right - left];

                // 记录结果
                for (int i = left; i < right; i++) {
                    arr[i - left] = i;
                }

                list.add(arr);

                // 记录结果，将当前 sum 值置空
                sum -= left;
                // 左边界向右移动
                left++;
            }
        }

        // 返回结果
        return list.toArray(new int[list.size()][]);
    }

}
