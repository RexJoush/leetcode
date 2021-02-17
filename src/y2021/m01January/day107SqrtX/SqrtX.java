package y2021.m01January.day107SqrtX;

/**
 * @author Joush
 * @time 2021.01.22
 */

/*
    x 的平方根
    https://leetcode-cn.com/problems/sqrtx/

    实现 int sqrt(int x) 函数。
    计算并返回 x 的平方根，其中 x 是非负整数。
    由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

    示例 1:
        输入: 4
        输出: 2
    示例 2:
        输入: 8
        输出: 2
        说明: 8 的平方根是 2.82842...,
             由于返回类型是整数，小数部分将被舍去。

 */
public class SqrtX {
    /*
        使用 java 的 Math 库函数
        结果：
            1 ms, 100.00%
            35.4 MB, 79.06%
     */
    public int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    /*
        二分法，计算即可
        结果：
            2 ms, 45.62%
            35.3 MB, 87.13%
     */
    public int mySqrt2(int x) {
        long left = 0;
        long right = x / 2 + 1;
        while (left < right){
            // 取左边的值
            long mid = (left + right + 1) >> 1;
            // 如果大于就去除右半支
            if (mid * mid > x){
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // 返回结果即可
        return (int) left;
    }
}
