package y2021.m10October.day384BinaryTreeInorderTraversal;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.10.26
 */

/*
    二叉树的中序遍历
    https://leetcode-cn.com/problems/binary-tree-inorder-traversal/

    给定一个二叉树的根节点 root ，返回它的 中序 遍历。
    
    示例 1：
        1
         \
          2
         /
        3
    输入：root = [1,null,2,3]
    输出：[1,3,2]

    示例 2：
    输入：root = []
    输出：[]

    示例 3：
    输入：root = [1]
    输出：[1]

    示例 4：
        1
       /
      2
    输入：root = [1,2]
    输出：[2,1]

    示例 5：
        1
         \
          2
    输入：root = [1,null,2]
    输出：[1,2]

    提示：
        树中节点数目在范围 [0, 100] 内
        -100 <= Node.val <= 100
 */
public class BinaryTreeInorderTraversal {

    /*
        递归遍历即可
        结果：
            0 ms, 100.00%
            36.7 MB, 43.68%
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    // 先序遍历
    public void preOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    // 中序遍历
    public void inOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
    }

    // 后续遍历
    public void postOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);
    }

}
