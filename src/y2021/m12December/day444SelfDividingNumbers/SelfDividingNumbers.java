package y2021.m12December.day444SelfDividingNumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.12.25
 */

/*
    自除数
    https://leetcode-cn.com/problems/self-dividing-numbers/

    自除数 是指可以被它包含的每一位数除尽的数。
    例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
    还有，自除数不允许包含 0 。
    给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。

    示例 1：
        输入：上边界left = 1, 下边界right = 22
        输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]

    注意：
        每个输入参数的边界满足 1 <= left <= right <= 10000。

 */
public class SelfDividingNumbers {

    /*
        遍历区间的每一个数字，判断是否是自除数即可
        结果：
            2 ms, 67.76%
            35.7 MB, 96.08%
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            if (isNumber(i)) {
                list.add(i);
            }
        }

        return list;
    }

    public boolean isNumber(int i) {
        int n = i;
        // 不断的取出数字，判断能否被自身整除，如果有 0 则直接返回 false
        while (i > 0) {
            int d = i % 10;
            if (d == 0 || (n % d) > 0) {
                return false;
            }
            i /= 10;
        }
        return true;
    }
}
