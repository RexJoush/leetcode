package y2022.m04April.day546HouseRobberIII;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2022.04.06
 */

/*
    打家劫舍 III
    https://leetcode-cn.com/problems/house-robber-iii/

    小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
    除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
    给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。

    示例 1:
           [3]
          /   \
         2     3
          \     \
          [3]   [1]
        输入: root = [3,2,3,null,3,null,1]
        输出: 7
        解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
    示例 2:
            3
          /   \
        [4]   [5]
       /   \    \
      1     3    1
        输入: root = [3,4,5,1,3,null,1]
        输出: 9
        解释:小偷一晚能够盗取的最高金额 4 + 5 = 9

    提示：
        树的节点数在[1, 10^4] 范围内
        0 <= Node.val <= 10^4

 */
public class HouseRobberIII {

    /*
        动态规划
            每个节点被选中的时候，左右子节点均不能被选中
            定义 f(o) 为当前节点被选中的最大值
                g(o) 为当前节点不被选中的最大值
            因此，当前节点 不被选中时 的最大值为左右两个子节点都被选中的和
                f(o) = g(l) + g(r)
            当前 节点选中时 的最大值为左右两个子节点都被选中或都不被选中的较大者
                g(o) = max(f(l),g(l)) + max(f(r) + g(r))
        结果：
            0 ms, 100.00%
            40.7 MB, 65.15%
     */
    public int rob(TreeNode root) {
        int[] dfs = dfs(root);
        // 返回选不选的较大值
        return Math.max(dfs[0], dfs[1]);
    }

    /*
        返回值表示子节点被选中或者不被选中的最大值
     */
    public int[] dfs(TreeNode node) {
        // 空节点返回 0
        if (node == null) {
            return new int[]{0, 0};
        }
        // 计算左节点
        int[] l = dfs(node.left);
        // 计算右节点
        int[] r = dfs(node.right);
        // 计算选择当前节点的最大值
        int f = node.val + l[1] + r[1];
        // 计算不选当前节点的最大值
        int g = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        // 返回当前节点的结果
        return new int[]{f, g};
    }
}
