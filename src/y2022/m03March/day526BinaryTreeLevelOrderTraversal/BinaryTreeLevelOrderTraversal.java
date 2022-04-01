package y2022.m03March.day526BinaryTreeLevelOrderTraversal;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Rex Joush
 * @time 2022.03.17
 */

/*
    二叉树的层序遍历
    https://leetcode-cn.com/problems/binary-tree-level-order-traversal/

    给你二叉树的根节点 root ，返回其节点值的 层序遍历。 （即逐层地，从左到右访问所有节点）。

    示例 1：
            3
          /   \
         9     20
              /  \
             15  7
        输入：root = [3,9,20,null,null,15,7]
        输出：[[3],[9,20],[15,7]]
    示例 2：
        输入：root = [1]
        输出：[[1]]
    示例 3：
        输入：root = []
        输出：[]

    提示：
        树中节点数目在范围 [0, 2000] 内
        -1000 <= Node.val <= 1000

 */
public class BinaryTreeLevelOrderTraversal {

    /*
        层序遍历即可，使用队列
        结果：
            0 ms, 100.00%
            41.2 MB, 64.74%
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return new LinkedList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
            res.add(list);
        }
        return res;
    }
}
