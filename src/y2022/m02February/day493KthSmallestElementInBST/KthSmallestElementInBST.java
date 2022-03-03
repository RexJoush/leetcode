package y2022.m02February.day493KthSmallestElementInBST;

import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Rex Joush
 * @time 2022.02.12
 */

/*
    二叉搜索树中第K小的元素
    https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/

    给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

    示例 1：
            3
          /   \
         1     4
          \
           2
        输入：root = [3,1,4,null,2], k = 1
        输出：1
    示例 2：
                5
              /   \
             3     6
           /   \
          2     4
         /
        1
    输入：root = [5,3,6,2,4,null,null,1], k = 3
    输出：3

    提示：
        树中的节点数为 n 。
        1 <= k <= n <= 10^4
        0 <= Node.val <= 10^4

    进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
public class KthSmallestElementInBST {

    /*
        方法二：统计节点
            统计左右子树的节点个数，左子树节点为 left
            如果 k = left + 1，那么根节点为所找节点
            如果 k > left + 1，那么所找节点在右子树上
            如果 k < left + 1，那么所找节点在左子树上
        结果：
            0 ms, 100.00%
            40.9 MB, 32.41%
     */
    public int kthSmallest2(TreeNode root, int k) {
        int left = getNode(root.left);
        if (left + 1 == k) {
            return root.val;
        } else if (k <= left) {
            return kthSmallest2(root.left, k);
        } else {
            return kthSmallest2(root.right, k - left - 1);
        }
    }

    /**
     * 获取当前节点的子结点个数
     * @param root 节点
     * @return 子节点个数
     */
    public int getNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getNode(root.left) + getNode(root.right) + 1;
    }

    /*
        方法一：中序遍历
            二叉搜索树的中序遍历序列是单调递增序列，掌握非递归的中序遍历
        结果：
            0 ms, 100.00%
            41 MB, 28.79%
     */
    public int kthSmallest(TreeNode root, int k) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        // 不为空，且栈不空
        while (root != null || !stack.isEmpty()) {
            // 根节点入栈，根节点往左移动到空为止
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 取出节点进行遍历
            root = stack.pop();
            k--;
            if (k == 0) {
                break;
            }
            // 遍历完毕，去右子树
            root = root.right;
        }
        return root.val;
    }
}
