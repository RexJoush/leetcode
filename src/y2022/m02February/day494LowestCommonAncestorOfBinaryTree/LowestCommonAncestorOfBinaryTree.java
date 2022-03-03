package y2022.m02February.day494LowestCommonAncestorOfBinaryTree;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2022.02.13
 */

/*
    二叉树的最近公共祖先
    https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/

    给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
    最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

    示例 1：
                3
              /   \
             5     1
           /  \   /  \
          6    2 0    8
              / \
             7   4
        输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        输出：3
        解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
    示例 2：
                3
              /   \
             5     1
           /  \   /  \
          6    2 0    8
              / \
             7   4
        输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        输出：5
        解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
    示例 3：
        输入：root = [1,2], p = 1, q = 2
        输出：1

    提示：
        树中节点数目在范围 [2, 105] 内。
        -109 <= Node.val <= 109
        所有 Node.val 互不相同 。
        p != q
        p 和 q 均存在于给定的二叉树中。

 */
public class LowestCommonAncestorOfBinaryTree {

    /*
        由题可得，若 root 为 p 和 q 的公共祖先，则只可能为以下三种情况之一
            1.p q 在 root 的子树中，且在 root 的两侧
            2.p = root，q 在 root 的左或右子树上
            3.q = root，p 在 root 的左或右子树上
        考虑通过递归进行遍历
            1.终止条件
                叶子节点直接返回 null
                当遍历到 p q 直接返回当前节点
            2.递归遍历左右子树，结果记为 left 和 right
            3.结果有四种
                1.left right 均为 null，说明当前节点的子树不包含 p q，返回 null
                2.left right 均不为空，说明当前节点即为最近公共祖先
                3.left 空，right 不空，p q 都不在 root 的左子树，直接返回 right，可分以下两种情况
                    3.1 p q 其中一个在 root 的右子树，此时 right 指向 p(或q)
                    3.2 p q 两节点都在 root 的右子树，此时 right 指向最近公共祖先节点
                4.left 不空，right 空，p q 都不在 root 的右子树，直接返回 left，
                    4.1 p q 其中一个在 root 的左子树，此时 left 指向 p(或q)
                    4.2 p q 两节点都在 root 的左子树，此时 left 指向最近公共祖先节点
        结果：
            6 ms, 100.00%
            42.7 MB, 18.47%
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 为空，找到 p q 直接返回
        if (root == null || root == p || root == q) {
            return root;
        }

        // 找左子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 找右子树
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 情况 3 左子树为空，返回右子树
        if (left == null) {
            return right;
        }
        // 情况 4 右子树为空，返回左子树
        if (right == null) {
            return left;
        }
        // 情况 2，左右子树均不为空，即当前节点为最近公共祖先
        return root;
    }
}
