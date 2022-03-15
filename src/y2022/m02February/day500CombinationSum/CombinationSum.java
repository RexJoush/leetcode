package y2022.m02February.day500CombinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.02.19
 */

/*
    组合总和
    https://leetcode-cn.com/problems/combination-sum/

    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target，找出 candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
    candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
    对于给定的输入，保证和为 target 的不同组合数少于 150 个。

    示例 1：
        输入：candidates = [2,3,6,7], target = 7
        输出：[[2,2,3],[7]]
        解释：
        2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
        7 也是一个候选， 7 = 7 。
        仅有这两种组合。
    示例 2：
        输入: candidates = [2,3,5], target = 8
        输出: [[2,2,2,2],[2,3,3],[3,5]]
    示例 3：
        输入: candidates = [2], target = 1
        输出: []

    提示：
        1 <= candidates.length <= 30
        1 <= candidates[i] <= 200
        candidate 中的每个元素都 互不相同
        1 <= target <= 500

 */
public class CombinationSum {

    /*
        回溯法
        4 ms, 28.39%
        41.4 MB, 43.26%
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backTrace(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * 回溯的过程
     * @param candidates 源数组
     * @param target 目标值
     * @param index 当前遍历的层数
     * @param temp 临时结果
     * @param result 结果列表
     */
    public void backTrace(int[] candidates, int target, int index, List<Integer> temp, List<List<Integer>> result) {
        // 如果小于 0 则说明不会出现结果，返回即可
        if (target < 0) {
            return;
        }
        // 如果等于 0，则找到结果，加入结果集合
        if (target == 0) {
            result.add(new ArrayList<>(temp));
        }

        // 回溯过程
        for (int i = index; i < candidates.length; i++) {
            // 选择，加入结果
            temp.add(candidates[i]);
            // 回溯，此处回溯 i 不加 1 是因为可以重复选择自己
            backTrace(candidates, target - candidates[i], i, temp, result);
            // 重置选择
            temp.remove(temp.size() - 1);
        }
    }
}
