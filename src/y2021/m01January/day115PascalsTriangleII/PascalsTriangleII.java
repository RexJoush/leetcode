package y2021.m01January.day115PascalsTriangleII;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2021.01.30
 */

/*
    杨辉三角 II
    https://leetcode-cn.com/problems/pascals-triangle-ii/

    给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
    在杨辉三角中，每个数是它左上方和右上方的数的和。

    示例:
        输入: 3
        输出: [1,3,3,1]

    进阶：
    你可以优化你的算法到 O(k) 空间复杂度吗？

 */
public class PascalsTriangleII {

    /*
        组合数的计算，直接计算出杨辉三角第 i 行的值
                 n!                                                   n-m+1
        Cmn = ————————  可以得到同一行的相邻组合数关系 Cmn = C(m-1)n  * ————————
              m!(n-m)!                                                  m
        C0n = 1, 所以可以直接计算出第 i 行的杨辉三角值
        结果：
            0 ms, 100.00%
            36.1 MB, 80.10%
     */
    public List<Integer> getRow2(int rowIndex) {
        // 定义返回结果
        List<Integer> result = new ArrayList<>();
        // 加入 1
        result.add(1);
        // 计算当前的组合数值
        for (int i = 1; i <= rowIndex; ++i) {
            result.add((int) ((long) result.get(i - 1) * (rowIndex - i + 1) / i));
        }
        // 返回结果
        return result;
    }

    /*
        与常规杨辉三角一样，记录第i行的结果即可，因为优化存储空间，无需存储之前的所有行，只需存储上一行即可
        结果：
            2 ms, 30.33%
            36.6 MB, 5.15%
     */
    public List<Integer> getRow(int rowIndex) {

        // 定义上一层的结果
        List<Integer> last = new ArrayList<>();

        // 计算结果
        for (int i = 0; i <= rowIndex; i++) {
            // 定义该行的列表
            List<Integer> row = new ArrayList<>();
            // 第 i 行共有 i 个值，所以从 0 -> i 计算
            for (int j = 0; j <= i; j++) {
                // 两个边界是 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // 获取上一行的 j-1 的值和 j 的值
                    row.add(last.get(j - 1) + last.get(j));
                }
            }
            // 存储本层的结果作为上一层的结果，先清空之前的结果，在存储本层的值
            last.clear();
            last.addAll(row);
        }

        // 返回结果
        return last;
    }
}
