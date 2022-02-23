package y2022.m02February.day482Permutations;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.02.01
 */

/*
    全排列
    https://leetcode-cn.com/problems/permutations/

    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列。你可以 按任意顺序 返回答案。

    示例 1：
        输入：nums = [1,2,3]
        输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    示例 2：
        输入：nums = [0,1]
        输出：[[0,1],[1,0]]
    示例 3：
        输入：nums = [1]
        输出：[[1]]

    提示：
        1 <= nums.length <= 6
        -10 <= nums[i] <= 10
        nums 中的所有整数 互不相同

 */
public class Permutations {

    /*
        回溯法，此方法也叫深度优先搜索树
        考虑 1，2，3 三个值，那么回溯法的搜索树如下
                        [ ]
                /        |       \
               1         2        3  // 空集合时，可以选择三个值的任意一个
             /   \     /   \    /   \
            2     3   1     3  1     2  // 第二次选择时，从未选择的剩余部分中，选择一个加入路径
            |     |   |     |  |     |
            3     2   3     1  2     1  // 第三次选择剩下的，直到全部选择完毕。
        回溯法的过程，即为深度优先搜索回溯树的过程
     */
    public List<List<Integer>> permute(int[] nums) {

        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        if (n == 0) {
            return res;
        }

        // 状态变量，记录当前深度优先搜索的深度
        int depth = 0;

        // 记录当前的 路径，为栈，因为考虑恢复状态时，出栈即可
        Deque<Integer> path = new ArrayDeque<>();
        // 回溯法记录当前数字的选择情况
        boolean[] used = new boolean[n];

        // 深度优先搜索
        dfs(nums, depth, path, used, n, res);
        return res;

    }

    /**
     * 深度优先搜索的过程
     * @param nums 搜索数组
     * @param depth 当前搜索的深度
     * @param path 已经搜索过的路径
     * @param used 搜索的节点表示
     * @param length 总长度，可以不在此传递
     * @param res 结果数组
     */
    private void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used, int length, List<List<Integer>> res) {
        // 当前的深度等于数组长度时，表示搜索完成，添加当前路径到结果中
        if (depth == length) {
            // 将结果添加
            res.add(new ArrayList<>(path));
            // 停止回溯
            return;
        }

        // 遍历后续的节点
        for (int i = 0; i < length; i++) {
            // 如果当前节点没有被遍历到，则加入路径中
            if (!used[i]) {
                // 加入路径，同时标记此节点被用过了
                path.addLast(nums[i]);
                used[i] = true;

                // 继续递归寻找， 同时，深度 + 1
                dfs(nums, depth + 1, path, used, length, res);

                // 回溯状态，即将当前数字的状态置为之前的状态
                used[i] = false;
                path.removeLast();
            }
        }
    }
}
