package y2021.m02February.day135PowerOfThree;

/**
 * @author Joush
 * @time 2021.02.19
 */

/*
    3的幂
    https://leetcode-cn.com/problems/power-of-three/

    给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。

    整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x

    示例 1：
        输入：n = 27
        输出：true
    示例 2：
        输入：n = 0
        输出：false
    示例 3：
        输入：n = 9
        输出：true
    示例 4：
        输入：n = 45
        输出：false

    提示：
        -2^31 <= n <= 2^31 - 1

 */
public class PowerOfThree {

    /*
        大佬解法
        因为题目限制 max 是 2^32 - 1，而在此范围内，3^19 = 1162261467 最大
        所以可能的结果就只有，3^0, 3^1,... 3^19 所以当 n 能被 3^19 除尽是，说明是 3 的幂
        结果：
            结果：
            16 ms, 77.59%
            38.1 MB, 80.02%
     */
    public boolean isPowerOfThree3(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    /*
        数学法
        x = 3^a, 则 a = log3(x) = lg(n)/lg(3) 换底公式
        若满足 x 是 3的幂，那么 a 为整数
        结果：
            15 ms, 99.36%
            38.3 MB, 44.21%
     */
    public boolean isPowerOfThree2(int n) {
        // 计算是否为整数即可
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
    /*
        不断除以 3 即可
        结果：
            16 ms, 78%
            38.1 MB, 90%
     */
    public boolean isPowerOfThree(int n) {
        // 3^0 == 1
        if (n == 1){
            return true;
        }
        // 其余的小于 3 的均为 false
        if (n < 3){
            return false;
        }
        // 不断除以 3，直到出现除不尽 3 时，返回 false，否则返回 true
        while (n != 3) {
            if (n % 3 == 0){
                n /= 3;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
