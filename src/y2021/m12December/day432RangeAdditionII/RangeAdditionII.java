package y2021.m12December.day432RangeAdditionII;

/**
 * @author Rex Joush
 * @time 2021.12.13
 */

/*
    范围求和 II
    https://leetcode-cn.com/problems/range-addition-ii/

    给定一个初始元素全部为 0，大小为 m*n 的矩阵M以及在M上的一系列更新操作。
    操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，
    含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素M[i][j]的值都增加 1。
    在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
    
    示例 1:
        输入:  m = 3, n = 3
               operations = [[2,2],[3,3]]
        输出: 4
        解释:
        初始状态, M =
            [[0, 0, 0],
             [0, 0, 0],
             [0, 0, 0]]
        执行完操作 [2,2] 后, M =
            [[1, 1, 0],
             [1, 1, 0],
             [0, 0, 0]]
        执行完操作 [3,3] 后, M =
            [[2, 2, 1],
             [2, 2, 1],
             [1, 1, 1]]
        M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。

    注意:
        1.m 和 n 的范围是 [1,40000]。
        2.a 的范围是 [1,m]，b 的范围是 [1,n]。
        3.操作数目不超过 10000。

 */
public class RangeAdditionII {

    /*
        由题意，[0, 0] 点的值就等于操作的次数，所以只需记录哪些点的值恰好也是操作次数
        即，找到 a 的最小值和 b 的最小值即可
        结果：
            0 ms, 100.00%
            38.6 MB, 11.62%
     */
    public int maxCount(int m, int n, int[][] ops) {
        int minA = m, minB = n;
        for (int[] op : ops) {
            minA = Math.min(minA, op[0]);
            minB = Math.min(minB, op[1]);
        }
        return minA * minB;
    }

}
