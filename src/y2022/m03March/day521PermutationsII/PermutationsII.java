package y2022.m03March.day521PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.03.12
 */

/*
    全排列 II
    https://leetcode-cn.com/problems/permutations-ii/

    给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

    示例 1：
    输入：nums = [1,1,2]
    输出：
    [[1,1,2],
     [1,2,1],
     [2,1,1]]
    示例 2：
    输入：nums = [1,2,3]
    输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

    提示：
        1 <= nums.length <= 8
        -10 <= nums[i] <= 10

 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        boolean[] used = new boolean[n];
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrace(nums, 0, new ArrayList<>(), used, res);
        return res;
    }

    public void backtrace(int[] nums, int index, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        // 满足条件了，返回
        if (nums.length == index) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 前一个数字
        int pre = -11;
        for (int i = 0; i < nums.length; i++) {
            // 用过，返回，与前面一样，返回
            if (used[i]) continue;
            if (nums[i] == pre) {
                continue;
            }
            // 更新值
            pre = nums[i];
            used[i] = true;
            path.add(nums[i]);
            backtrace(nums, index + 1, path, used, res);
            // 回溯
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
