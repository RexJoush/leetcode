package y2022.m02February.day496BinaryTreeMaximumPathSum;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2022.02.15
 */

/*
    二叉树中的最大路径和
    https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/

    路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
    同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
    路径和 是路径中各节点值的总和。
    给你一个二叉树的根节点 root ，返回其 最大路径和 。

    示例 1：
                1
              /   \
             2     3
        输入：root = [1,2,3]
        输出：6
        解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
    示例 2：
               -10
              /   \
             9     20
                 /    \
                15     7
        输入：root = [-10,9,20,null,null,15,7]
        输出：42
        解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42

    提示：
        树中节点数目范围是 [1, 3 * 10^4]
        -1000 <= Node.val <= 1000

 */
public class BinaryTreeMaximumPathSum {

    /*
        遍历整棵树，考虑两种情况
            一：以当前节点为根节点，向左右子树延伸，取得最大值
            二：以当前节点为子树，向左 或 向右延伸，为父节点贡献最大值
        记录此过程的最大值即可
     */
    public int result;
    public int maxPathSum(TreeNode root) {
        result = root.val;
        dfs(root);
        return result;
    }

    // 计算每个节点的最大值
    public int dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            // 叶子节点返回当前节点的值，如果当前节点的值为负数，则返回 0
            result = Math.max(result, root.val);
            return Math.max(root.val, 0);
        }

        int left = 0; // 左子树的最大值
        int right = 0; // 右子树的最大值

        if (root.left == null) {
            // 计算右子树
            right =  dfs(root.right);
        } else if (root.right == null) {
            // 计算左子树
            left =  dfs(root.left);
        } else {
            // 均不为空，计算左右子树
            left = dfs(root.left);
            right = dfs(root.right);
        }

        // 计算以当前节点为根节点的最大路径，并更新最大值
        result = Math.max(result, left + right + root.val);

        // 返回，当前节点加左右子树较大值作为当前节点的值，同时考虑，如果此值为负数，则当前节点对父节点的贡献视为 0
        return Math.max(root.val + Math.max(left, right), 0);

    }

}
