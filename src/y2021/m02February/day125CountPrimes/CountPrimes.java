package y2021.m02February.day125CountPrimes;

import java.util.Arrays;

/**
 * @author Joush
 * @time 2021.02.09
 */

/*
    计数质数
    https://leetcode-cn.com/problems/count-primes/

    统计所有小于非负整数 n 的质数的数量。

    示例 1：
        输入：n = 10
        输出：4
        解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
    示例 2：
        输入：n = 0
        输出：0
    示例 3：
        输入：n = 1
        输出：0
    提示：
        0 <= n <= 5 * 106

 */
public class CountPrimes {

    /*
        埃氏筛
        该算法由希腊数学家厄拉多塞（EratosthenesEratosthenes）提出，称为厄拉多塞筛法，简称埃氏筛。

        考虑，如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。
        设 isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 0。
        从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身）
        即 0，这样在运行结束的时候我们即能知道质数的个数。

        当然这里还可以继续优化，对于一个质数 x，
        如果按上文说的我们从 2x 开始标记其实是冗余的，应该直接从 x⋅x 开始标记
        因为 2x,3x,… 这些数一定在 xx 之前就被其他数的倍数标记过了
        结果：
            16 ms, 88.36%
            37.4 MB, 39.32%
     */
    public int countPrimes2(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int result = 0;
        // 从 2 开始
        for (int i = 2; i < n; i++) {
            // 如果当前的值为 true, 表示是素数，为 false，则直接结束当前轮次循环
            if (isPrime[i]) {
                // 将计数器加一
                result++;
                // 将 i 的所有倍数均值为 false
                if ((long) i * i < n) {
                    // 从 i * i 开始标记
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }
        return result;
    }

    /*
        暴力法，计数即可，但在第 19/20 个测试，n = 1500000 时会超时
     */
    public int countPrimes(int n) {

        // 小于 2 没有质数
        if (n <= 2) {
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        if (n <= 5) {
            return 2;
        }
        int result = 3;
        // 所有偶数直接跳过
        for (int i = 5; i < n; i += 2) {
            // 所有 5 的倍数直接跳过
            if (i % 5 == 0) {
                continue;
            }
            if (isPrime(i)) {
                result++;
            }
        }
        return result;
    }

    public boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
