package y2021.m11November.day397SumOfLeftLeaves;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2021.11.08
 */

/*
    左叶子之和
    https://leetcode-cn.com/problems/sum-of-left-leaves/

    计算给定二叉树的所有左叶子之和。

    示例：
        3
       / \
      9  20
        /  \
       15   7

    在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

 */
public class SumOfLeftLeaves {

    /*
        深度优先搜索，找到所有符合条件的节点，加起来即可
        结果：
            0 ms, 100.00%
            36.1 MB, 67.05%
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode root) {
        int result = 0;
        // 左节点不为空
        if (root.left != null) {
            // 是叶子节点就加上当前值，不是叶子节点就递归遍历左子树
            result += isLeafNode(root.left) ? root.left.val : dfs(root.left);
        }
        // 右节点不为空且非叶子节点，递归遍历右子树
        if (root.right != null && !isLeafNode(root.right)) {
            result += dfs(root.right);
        }
        // 返回结果
        return result;
    }

    // 判断叶子节点
    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

}
