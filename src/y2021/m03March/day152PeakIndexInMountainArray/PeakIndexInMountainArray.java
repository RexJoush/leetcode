package y2021.m03March.day152PeakIndexInMountainArray;

/**
 * @author Rex Joush
 * @time 2021.03.08
 */

/*
    山脉数组的峰顶索引
    https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/

    符合下列属性的数组 arr 称为 山脉数组 ：
    arr.length >= 3
    存在 i（0 < i < arr.length - 1）使得：
    arr[0] < arr[1] < ... arr[i-1] < arr[i]
    arr[i] > arr[i+1] > ... > arr[arr.length - 1]
    给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。

    示例 1：
        输入：arr = [0,1,0]
        输出：1
    示例 2：
        输入：arr = [0,2,1,0]
        输出：1
    示例 3：
        输入：arr = [0,10,5,2]
        输出：1
    示例 4：
        输入：arr = [3,4,5,1]
        输出：2
    示例 5：
        输入：arr = [24,69,100,99,79,78,67,36,26,19]
        输出：2
    
    提示：
        3 <= arr.length <= 10^4
        0 <= arr[i] <= 10^6
        题目数据保证 arr 是一个山脉数组

 */
public class PeakIndexInMountainArray {

    /*
        方法一：二分法
        此处，left = 1，right = length - 2，规避了数组越界的情况
     */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (right - left) / 2 + left;

            // 如果 arr[mid] 比左边大，比右边大，则返回 mid
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            // 比左边大，答案在右侧
            else if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            }
            // 答案在左侧
            else {
                right = mid - 1;
            }
        }
        // 返回结果
        return left;
    }

}
