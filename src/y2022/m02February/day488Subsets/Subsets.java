package y2022.m02February.day488Subsets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.02.07
 */

/*
    子集
    https://leetcode-cn.com/problems/subsets/

    给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

    示例 1：
        输入：nums = [1,2,3]
        输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    示例 2：
        输入：nums = [0]
        输出：[[],[0]]

    提示：
        1 <= nums.length <= 10
        -10 <= nums[i] <= 10
        nums 中的所有元素 互不相同

 */
public class Subsets {

    /*
        方法二：回溯法
            遍历所有结果即可
            回溯法的模板

            public void backTrace(参数列表) {
                if (终止条件) {
                    记录结果
                    return;
                }

                for (选择本层的所有节点集合) {
                    处理节点
                    backTrace(递归，更新层数);
                    回溯，撤销处理
                }
            }

     */
    List<List<Integer>> result = new ArrayList<>(); // 存放结果
    LinkedList<Integer> path = new LinkedList<>(); // 存放路径

    public List<List<Integer>> subsets2(int[] nums) {
        if (nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        backTracking(nums, 0);
        return result;
    }

    // 回溯过程
    private void backTracking(int[] nums, int index) {
        // 需要将所有的路径加入结果集合
        result.add(new ArrayList<>(path));

        // 遍历剩下的数字
        for (int i = index; i < nums.length; i++) {
            // 将当前节点放入
            path.add(nums[i]);
            // 递归下一层
            backTracking(nums, i + 1);
            // 状态重置
            path.removeLast();
        }
    }

    /*
        方法一：迭代法
            考虑将每个数字代表一位二进制位
                3 5 8 所有的子集即对应 0 0 0, 0 0 1, 0 1 0, 0 1 1, 1 0 0, 1 0 1, 1 1 0, 1 1 1 共八种情况
                                      {}  ,  {8}     {5}  {5,8}   {3}   {3,8}  {3,5}   {3,5,8}
            结果：
                1 ms, 25.18%
                41.8 MB, 5.07%
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;

        List<Integer> temp = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        // 遍历即可
        // 此处，2^(n - 1) 次幂可表示为 1 << n
        for (int i = 0; i < (1 << n); i++) {
            temp.clear();
            for (int index = 0; index < n; index++) {
                // 当前位上不为 0，即将此位代表的数字放入结果中
                if (((1 << index) & i) != 0) {
                    temp.add(nums[index]);
                }
            }
            res.add(new ArrayList<>(temp));
        }
        return res;
    }
}
