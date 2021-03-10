package y2021.m02February.day136PowerOfFour;

/**
 * @author Joush
 * @time 2021.02.20
 */

/*
    4的幂
    https://leetcode-cn.com/problems/power-of-four/

    给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
    整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x

    示例 1：
        输入：n = 16
        输出：true
    示例 2：
        输入：n = 5
        输出：false
    示例 3：
        输入：n = 1
        输出：true

    提示：
        -2^31 <= n <= 2^31 - 1

    进阶：
        你能不使用循环或者递归来完成本题吗？

 */
public class PowerOfFour {

    /*
        数学运算
        x = 4^a, 则 a = log4(x) = (1/2) log2(x)
        我们只需判断 log2(x)是否为偶数即可
        结果：
            1 ms, 100.00%
            35.8 MB, 5.06%
     */
    public boolean isPowerOfFour2(int n) {
        return (n > 0) && (Math.log(n) / Math.log(2) % 2 == 0);
    }

    /*
        和上题 3 的幂基本一样
        结果：
            1 ms, 100.00%
            35.3 MB, 87.08%
     */
    public boolean isPowerOfFour(int n) {
        // 4^0 == 1
        if (n == 1){
            return true;
        }
        // 其余的小于 4 的均为 false
        if (n < 4){
            return false;
        }
        // 不断除以 4，直到出现除不尽 4 时，返回 false，否则返回 true
        while (n != 4) {
            if (n % 4 == 0){
                n /= 4;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
