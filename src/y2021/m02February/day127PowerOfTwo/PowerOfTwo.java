package y2021.m02February.day127PowerOfTwo;

/**
 * @author Joush
 * @time 2021.02.11
 */

/*
    2的幂
    https://leetcode-cn.com/problems/power-of-two/

    给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

    示例 1:
        输入: 1
        输出: true
        解释: 20 = 1
    示例 2:
        输入: 16
        输出: true
        解释: 24 = 16
    示例 3:
        输入: 218
        输出: false

 */
public class PowerOfTwo {

    /*
        不断除以2即可
        结果：
            1 ms, 100.00%
            35.4 MB, 74.54%
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n / 2 != 0) {
            if (n % 2 != 0){
                return false;
            }
            n /= 2;
        }
        return true;
    }

    /*
        由于 2 的幂次的数二进制表示均为 10,100,1000,10000 等
        可以考虑位运算，此处，当 x & -x 可以取到最右边的 1
        解释
            x       = 00000111
            ~x      = 11111000
            ~x+1    = 11111001
        而 ~x + 1 = -x
        所以当 x & -x 时，可以取到
            x & -x  = 00000001
            因此，当最后一位的 1 与之前的 x 相等时，我们认为时2的次幂

        结果：
            1 ms, 100.00%
            35.7 MB, 17.47%
     */
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) {
            return false;
        }
        // 进行位运算
        return (n & (-n)) == n;
    }
}
