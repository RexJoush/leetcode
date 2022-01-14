package y2021.m03March.day166ValidMountainArray;

/**
 * @author Rex Joush
 * @time 2021.03.22
 */

/*
    有效的山脉数组
    https://leetcode-cn.com/problems/valid-mountain-array/

    给定一个整数数组 arr，如果它是有效的山脉数组就返回 true，否则返回 false。
    让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
        arr.length >= 3
        在 0 < i < arr.length - 1 条件下，存在 i 使得：
            arr[0] < arr[1] < ... arr[i-1] < arr[i]
            arr[i] > arr[i+1] > ... > arr[arr.length - 1]
    
    示例 1：
        输入：arr = [2,1]
        输出：false
    示例 2：
        输入：arr = [3,5,5]
        输出：false
    示例 3：
        输入：arr = [0,3,2,1]
        输出：true

    提示：
        1 <= arr.length <= 10^4
        0 <= arr[i] <= 10^4

 */
public class ValidMountainArray {

    /*
        顺序判断即可
        结果：
            1 ms, 100.00%
            39.4 MB, 49.39%
     */
    public boolean validMountainArray(int[] arr) {

        int index = 0;

        // 判断上升
        while (index < arr.length - 1 && arr[index] < arr[index + 1]) {
            index++;
        }
        // 如果未上升，或者直接上升到底，即单调数列返回 false
        if (index == 0 || index == arr.length - 1) {
            return false;
        }
        // 判断下降
        while (index < arr.length - 1 && arr[index] > arr[index + 1]) {
            index++;
        }

        // 如果到结尾，返回 true
        return index == arr.length - 1;
    }
}
