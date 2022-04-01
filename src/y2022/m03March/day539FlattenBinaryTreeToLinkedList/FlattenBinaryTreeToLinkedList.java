package y2022.m03March.day539FlattenBinaryTreeToLinkedList;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.03.30
 */

/*
    二叉树展开为链表
    https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/

    给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。

    示例 1：
                1           1
              /   \          \
             2     5          2
           /   \    \          \
          3     4    6          3
                                 \
                                  4
                                   \
                                    5
                                     \
                                      6
        输入：root = [1,2,5,3,4,null,6]
        输出：[1,null,2,null,3,null,4,null,5,null,6]
    示例 2：
        输入：root = []
        输出：[]
    示例 3：
        输入：root = [0]
        输出：[0]

    提示：
        树中结点数在范围 [0, 2000] 内
        -100 <= Node.val <= 100

 */
public class FlattenBinaryTreeToLinkedList {

    /*
        遍历一遍，存储先序节点，再建树即可
     */
    List<TreeNode> list = new ArrayList<>();
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root);
        TreeNode p = root;
        root.left = null;
        // 拼接树即可
        for (int i = 1; i < list.size(); i++) {
            p.right = list.get(i);
            p.left = null;
            p = p.right;
        }
    }

    // 中序遍历
    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node);
        inorder(node.left);
        inorder(node.right);
    }
}
