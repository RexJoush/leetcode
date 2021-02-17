package y2021.m01January.day93PrintNumber;

/**
 * @author Joush
 * @time 2021.01.08
 */

/*
    打印从1到最大的n位数
    https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/

    输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

    示例 1:
        输入: n = 1
        输出: [1,2,3,4,5,6,7,8,9]

    说明：
        用返回一个整数列表来代替打印
        n 为正整数

 */
public class PrintNumber {
    /*
        直接打印即可
        结果：
            1 ms, 100.00%
            46.7 MB, 47.19%
     */
    public int[] printNumbers(int n) {
        int top = 1;
        for (int i = 0; i < n; i++) {
            top *= 10;
        }
        int[] result = new int[top-1];
        for (int i = 0; i < top - 1; i++) {
            result[i] = i + 1;
        }
        return result;
    }
}
