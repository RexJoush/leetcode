package y2021.m03March.day169UnivaluedBinaryTree;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2021.03.25
 */

/*
    单值二叉树
    https://leetcode-cn.com/problems/univalued-binary-tree/
    
    如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
    只有给定的树是单值二叉树时，才返回 true；否则返回 false。

    示例 1：
            1
          /   \
         1     1
       /   \    \
      1     1    1
        输入：[1,1,1,1,1,null,1]
        输出：true
    示例 2：
            2
          /   \
         2     2
       /   \
      5     2
        输入：[2,2,2,5,2]
        输出：false

    提示：
        给定树的节点数范围是 [1, 100]。
        每个节点的值都是整数，范围为 [0, 99] 。
 */
public class UnivaluedBinaryTree {

    /*
        遍历整棵树，如果出现不同的值，直接返回结果
        结果：
            0 ms, 100.00%
            36 MB, 44.42%
     */
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root);
    }

    public boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }
        // 左子节点不等于根节点，返回 false
        if (node.left != null && node.left.val != node.val) {
            return false;
        }
        // 右子节点不等于根节点，返回 false
        if (node.right != null && node.right.val != node.val) {
            return false;
        }
        // 递归左右子树
        return dfs(node.left) && dfs(node.right);
    }
}
