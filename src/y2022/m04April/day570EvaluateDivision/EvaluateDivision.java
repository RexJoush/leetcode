package y2022.m04April.day570EvaluateDivision;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.04.30
 */

/*
    除法求值
    https://leetcode.cn/problems/evaluate-division/

    给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
    另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
    返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
    注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。

    示例 1：
        输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
        解释：
            条件：a / b = 2.0, b / c = 3.0
            问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
            结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
    示例 2：
        输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        输出：[3.75000,0.40000,5.00000,0.20000]
    示例 3：
        输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
        输出：[0.50000,2.00000,-1.00000,-1.00000]

    提示：
        1 <= equations.length <= 20
        equations[i].length == 2
        1 <= Ai.length, Bi.length <= 5
        values.length == equations.length
        0.0 < values[i] <= 20.0
        1 <= queries.length <= 20
        queries[i].length == 2
        1 <= Cj.length, Dj.length <= 5
        Ai, Bi, Cj, Dj 由小写英文字母与数字组成

 */
public class EvaluateDivision {

    /*
        Floyd 算法，找到途中所有点到点的距离即可，打表，最后读取即可
        结果：
            0 ms, 100.00%
            40.1 MB, 35.82%
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // 统计字符的个数，将字符映射为数字
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (List<String> equation : equations) {
            if (!map.containsKey(equation.get(0))) {
                map.put(equation.get(0), index++);
            }
            if (!map.containsKey(equation.get(1))) {
                map.put(equation.get(1), index++);
            }
        }
        int n = map.size();
        // 图数组
        double[][] graph = new double[n][n];

        // 计算给定的结果
        for (int i = 0; i < equations.size(); i++) {
            /*
                如：["a", "c"] -> [0.5], 且 a 对应 1, c 对应 2，
                那么，下面表示 graph[1][2] = 0.5, graph[2][1] = 2.0
             */
            graph[map.get(equations.get(i).get(0))][map.get(equations.get(i).get(1))] = values[i];
            graph[map.get(equations.get(i).get(1))][map.get(equations.get(i).get(0))] = 1 / values[i];
        }
        // i 借助 k 到 j
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 已经计算过的不再计算
                    if (j == k || graph[i][j] != 0) {
                        continue;
                    }
                    /*
                        如果 i / k = a, 且 k / j = b
                        那么 i / j = a * b
                     */
                    if (graph[i][k] != 0 && graph[k][j] != 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }
        // 结果查表即可
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            // 如果表中有则计算
            if (map.containsKey(a) && map.containsKey(b)) {
                double temp = graph[map.get(a)][map.get(b)];
                // 如果值为 0 说明不可达，赋值为 -1.0
                result[i] = temp == 0 ? -1.0 : temp;
            } else {
                result[i] = -1.0;
            }
        }
        // 返回结果即可
        return result;
    }
}
