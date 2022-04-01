package y2022.m03March.day538ConstructBinaryTree;

import datastructure.TreeNode;

import java.util.Arrays;

/**
 * @author Rex Joush
 * @time 2022.03.29
 */

/*
    从前序与中序遍历序列构造二叉树
    https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

    给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
    inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

    示例 1:
                3
              /   \
             9     20
                  /  \
                 15   7
        输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        输出: [3,9,20,null,null,15,7]
    示例 2:
        输入: preorder = [-1], inorder = [-1]
        输出: [-1]

    提示:
        1 <= preorder.length <= 3000
        inorder.length == preorder.length
        -3000 <= preorder[i], inorder[i] <= 3000
        preorder 和 inorder 均 无重复 元素
        inorder 均出现在 preorder
        preorder 保证 为二叉树的前序遍历序列
        inorder 保证 为二叉树的中序遍历序列

 */
public class ConstructBinaryTree {

    /*
        递归遍历即可
        结果：
            6 ms, 14.07%
            41.9 MB, 7.16%
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length == 0 || preorder.length == 0) {// 结束了，返回 null
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        // 找到根节点
        int i = 0;
        while (preorder[0] != inorder[i]) {
            i++;
        }
        // 根据根节点划分左右子树的中序数组
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, i);
        int[] rightInorder = Arrays.copyOfRange(inorder, i + 1, inorder.length);

        // 找到子树的先序数组
        int[] leftPreOrder = Arrays.copyOfRange(preorder, 1, i + 1);
        int[] rightPreOrder = Arrays.copyOfRange(preorder, i + 1, preorder.length);

        root.left = buildTree(leftPreOrder,  leftInorder);
        root.right = buildTree(rightPreOrder, rightInorder);
        return root;
    }
}
