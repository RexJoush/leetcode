package y2021.m02February.day131AddDigits;

/**
 * @author Joush
 * @time 2021.02.15
 */

/*
    各位相加
    https://leetcode-cn.com/problems/add-digits/

    给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。

    示例:
        输入: 38
        输出: 2
        解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。

    进阶:
        你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？

 */
public class AddDigits {

    /*
        O(1)解法，找规律
        1 : 1       2 : 2
        3 : 3       4 : 4
        5 : 5       6 : 6
        7 : 7       8 : 8
        9 : 9       10 : 1
        11 : 2      12 : 3
        13 : 4      14 : 5
        15 : 6      ...
        100 : 1     101 : 2
        102 : 3

        可以看到，最终结果从1-9依次变化，所以想到要对9求余数
        代码逻辑：
            1.输入数字为 0 时，输出为 0
            2.输入数字是 9 的倍数时，输出为 9
            3.输入数字非 0 且不是 9 的倍数时，输出为（x % 9）
        结果：
            1 ms, 100.00%
            35.6 MB, 37.93%
     */
    public int addDigits2(int num) {
        if (num == 0)
            return 0;

        return num % 9 == 0 ? 9 : num % 9;
    }

    /*
        相加即可
        结果：
            1 ms, 100.00%
            35.7 MB, 27.74%
     */
    public int addDigits(int num) {
        int res = getNext(num);
        while (res >= 10) {
            res = getNext(res);
        }
        return res;
    }

    /*
        各位相加
     */
    public int getNext(int num) {
        int result = 0;
        while (num != 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }
}
