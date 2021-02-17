package y2021.m01January.day114PascalsTriangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2021.01.29
 */

/*
    杨辉三角
    https://leetcode-cn.com/problems/pascals-triangle/

    给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
    在杨辉三角中，每个数是它左上方和右上方的数的和。

    示例:
        输入: 5
        输出:
        [
             [1],
            [1,1],
           [1,2,1],
          [1,3,3,1],
         [1,4,6,4,1]
        ]
 */
public class PascalsTriangle {

    /*
        计算，从上两行获取值求和加入列表即可
        结果：
            0 ms, 100.00%
            36.4 MB, 29.66%
     */
    public List<List<Integer>> generate(int numRows) {
        // 定义返回结果集合
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            // 定义该行的列表
            List<Integer> row = new ArrayList<>();
            // 第 i 行共有 i 个值，所以从 0 -> i 计算
            for (int j = 0; j <= i; j++) {
                // 两个边界是 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    //      获取上一行         的 j-1 的值        和           j 的值
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            // 将该行加入结果集合
            result.add(row);
        }
        // 返回结果
        return result;
    }
}
