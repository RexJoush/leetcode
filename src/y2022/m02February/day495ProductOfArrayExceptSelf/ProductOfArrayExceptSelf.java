package y2022.m02February.day495ProductOfArrayExceptSelf;

/**
 * @author Rex Joush
 * @time 2022.02.14
 */

/*
    除自身以外数组的乘积
    https://leetcode-cn.com/problems/product-of-array-except-self/

    给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请不要使用除法，且在 O(n) 时间复杂度内完成此题。

    示例 1:
        输入: nums = [1,2,3,4]
        输出: [24,12,8,6]
    示例 2:
        输入: nums = [-1,1,0,-3,3]
        输出: [0,0,9,0,0]

    提示：
        2 <= nums.length <= 10^5
        -30 <= nums[i] <= 30
        保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内

    进阶：
        你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

 */
public class ProductOfArrayExceptSelf {

    /*
        从左至右计算每个节点的乘积得到数组 A[]，在从右至左计算每个节点的乘积得到乘积数组 B[]
        每个节点的结果 a[i] = A[i-1] & B[i+1]
        举例
            1 2 3 4
            A[] = {1, 2, 6, 24}
            B[] = {24, 24, 12, 4}
        答案数组 result[] = {24, 12, 8, 6}
        结果：
            1 ms, 100.00%
            49.8 MB, 26.69%
     */
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];

        res[0] = nums[0];

        // 正向计算每个乘积
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i];
        }
        // 反向计算，更新最后的值
        res[n - 1] = res[n - 2];

        // 临时变量存储反向计算的乘积
        int temp = nums[n - 1];

        // 计算最终结果
        for (int i = n - 2; i > 0; i--) {
            res[i] = temp * res[i - 1];
            temp *= nums[i];
        }
        // 更新第一个值
        res[0] = temp;

        return res;
    }
}
