package November20.day44ValidPerfectSquare;

/**
 * @author Joush
 * @time 2020.11.20
 */

/*
    有效的完全平方数
    https://leetcode-cn.com/problems/valid-perfect-square/

    给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
    说明：不要使用任何内置的库函数，如 sqrt。

    示例 1：
        输入：16
        输出：true
    示例 2：
        输入：14
        输出：false

 */
public class ValidPerfectSquare {

    /*
        完全平方数的性质
        完全平方数可以通过累加从1往后的奇数找到，

        1 = 1;
        4 = 1 + 3;
        9 = 1 + 3 + 5;
        16 = 1 + 3 + 5 + 7;
     */
    public boolean isPerfectSquare3(int num){
        if (num == 0 ) return false;

        int i = 1;
        while ( num > 0){
            num -= i;
            i += 2;
        }
        return num == 0 ? true : false;
    }

    /*
        牛顿迭代法
     */
    public boolean isPerfectSquare2(int num){
        if (num < 2) return true;
        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
            if (x * x == num) {
                return true;
            }
        }
        return false;
    }

    // 会超出时间限制
    public boolean isPerfectSquare(int num) {

        if (num == 1){
            return true;
        }
        for (int i = 1 ; i <= num / 2; i++){
            if (i * i == num){
                return true;
            }
        }
        return false;
    }
}
