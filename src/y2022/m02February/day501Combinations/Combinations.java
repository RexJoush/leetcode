package y2022.m02February.day501Combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.02.20
 */

/*
    组合
    https://leetcode-cn.com/problems/combinations/

    给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
    你可以按 任何顺序 返回答案。

    示例 1：
        输入：n = 4, k = 2
        输出：
        [
          [2,4],
          [3,4],
          [2,3],
          [1,2],
          [1,3],
          [1,4],
        ]
    示例 2：
        输入：n = 1, k = 1
        输出：[[1]]

    提示：
        1 <= n <= 20
        1 <= k <= n

 */
public class Combinations {

    /*
        回溯
        结果：
            10 ms, 56.15%
            42.7 MB, 17.29%
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void backTrack(int n, int k, int index, ArrayList<Integer> temp, List<List<Integer>> result) {
        // 终止时的情况
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i <= n; i++) {
            // 加入当前集合
            temp.add(i);
            // 回溯
            backTrack(n, k, i + 1, temp, result);
            // 重置选择
            temp.remove(temp.size() - 1);
        }
    }
}
