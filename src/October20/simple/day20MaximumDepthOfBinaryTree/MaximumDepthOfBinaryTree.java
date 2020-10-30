package October20.simple.day20MaximumDepthOfBinaryTree;

/**
 * @author Joush
 * @time 2020.10.27
 */

/*

    二叉树的最大深度
    https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/

    给定一个二叉树，找出其最大深度。
    二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

    说明: 叶子节点是指没有子节点的节点。

    示例：
        给定二叉树 [3,9,20,null,null,15,7]，
            3
           / \
          9  20
            /  \
           15   7
        返回它的最大深度 3 。

 */

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {

        // 递归找左右子树的最高子树，深度优先搜索
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }

}
