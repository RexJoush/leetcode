package y2022.m03March.day533ValidateBinarySearchTree;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2022.03.24
 */

/*
    验证二叉搜索树
    https://leetcode-cn.com/problems/validate-binary-search-tree/

    给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    有效 二叉搜索树定义如下：
        节点的左子树只包含 小于 当前节点的数。
        节点的右子树只包含 大于 当前节点的数。
        所有左子树和右子树自身必须也是二叉搜索树。

    示例 1：
                2
               / \
              1   3
        输入：root = [2,1,3]
        输出：true
    示例 2：
                    5
                  /   \
                 1     4
                      /  \
                     3    6
        输入：root = [5,1,4,null,null,3,6]
        输出：false
        解释：根节点的值是 5 ，但是右子节点的值是 4 。

    提示：
        树中节点数目范围在[1, 10^4] 内
        -2^31 <= Node.val <= 2^31 - 1

 */
public class ValidateBinarySearchTree {

    /*
        递归判断即可
        结果：
            0 ms, 100.00%
            40.8 MB, 48.41%
     */
    public boolean isValidBST(TreeNode root) {
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /*
        记录一下右侧最小值不能小于根节点，左侧最大值不能大于根节点
     */
    public boolean isBST(TreeNode node, long min, long max) {

        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }
}
