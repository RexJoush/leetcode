package y2021.m01January.day100ReplaceElementsWithRight;

/**
 * @author Joush
 * @time 2021.01.15
 */

/*
    将每个元素替换为右侧最大元素
    https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side/

    给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
    完成所有替换操作后，请你返回这个数组。

    示例 1：
        输入：arr = [17,18,5,4,6,1]
        输出：[18,6,6,6,1,-1]
        解释：
        - 下标 0 的元素 --> 右侧最大元素是下标 1 的元素 (18)
        - 下标 1 的元素 --> 右侧最大元素是下标 4 的元素 (6)
        - 下标 2 的元素 --> 右侧最大元素是下标 4 的元素 (6)
        - 下标 3 的元素 --> 右侧最大元素是下标 4 的元素 (6)
        - 下标 4 的元素 --> 右侧最大元素是下标 5 的元素 (1)
        - 下标 5 的元素 --> 右侧没有其他元素，替换为 -1
    示例 2：
        输入：arr = [400]
        输出：[-1]
        解释：下标 0 的元素右侧没有其他元素。

    提示：
        1 <= arr.length <= 10^4
        1 <= arr[i] <= 10^5

 */
public class ReplaceElementsWithRight {

    /*
        从后往前计算，每次记录从后往前的最大值，进行替换即可
        结果：
            1 ms, 100.00%
            39.9 MB, 17.64%
     */
    public int[] replaceElements(int[] arr) {
        // 结果数组
        int[] result = new int[arr.length];
        // 最后一位置为 -1
        result[arr.length - 1] = -1;
        // 记录当前最末尾的最大值
        int max = arr[arr.length - 1];
        // 从后往前遍历
        for (int i = arr.length - 2; i >=0; i--){
            // 结果等于当前最大值
            result[i] = max;
            // 更新最大值
            if (arr[i] > max){
                max = arr[i];
            }
        }
        return result;
    }

}
