package y2021.m11November.day414PerfectNumber;

import java.util.Set;

/**
 * @author Rex Joush
 * @time 2021.11.25
 */

/*
    完美数
    https://leetcode-cn.com/problems/perfect-number/

    对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
    给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
    
    示例 1：
    输入：num = 28
        输出：true
        解释：28 = 1 + 2 + 4 + 7 + 14
        1, 2, 4, 7, 和 14 是 28 的所有正因子。
    示例 2：
        输入：num = 6
        输出：true
    示例 3：
        输入：num = 496
        输出：true
    示例 4：
        输入：num = 8128
        输出：true
    示例 5：
        输入：num = 2
        输出：false
    
    提示：
        1 <= num <= 10^8

 */
public class PerfectNumber {

    /*
        方法一：枚举
        根据要求，遍历即可,只需枚举 1 - 根号 n 即可
        结果：
            1 ms, 92.20%
            35.1 MB, 63.26%
     */
    public boolean checkPerfectNumber(int num) {

        if (num == 1) {
            return false;
        }
        int sum = 1;

        for (int i = 2; i * i <= num; i++) {
            // 即 a * b = num 加上 a 和 b，去除特殊情况， a = b
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }
            }

        }

        return sum == num;
    }

    /*
        方法二：欧几里得-欧拉定理
        每个完全数均可以写成 2^(p-1) * (2^p - 1)
        因此，计算出 10^8 内的完全数仅有 6 个。分别为 6, 28, 496, 8128, 33550336
        结果:
            0 ms, 100.00%
            34.9 MB, 92.27%
     */
    public boolean checkPerfectNumber2(int num) {

        Set<Integer> set = Set.of(6, 28, 496, 8128, 33550336);

        return set.contains(num);
    }

}
