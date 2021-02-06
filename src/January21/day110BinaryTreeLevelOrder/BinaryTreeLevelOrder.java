package January21.day110BinaryTreeLevelOrder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Joush
 * @time 2021.01.25
 */

/*
    二叉树的层序遍历 II
    https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/

    给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
    例如：
    给定二叉树 [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    返回其自底向上的层序遍历为：
    [
      [15,7],
      [9,20],
      [3]
    ]

 */
public class BinaryTreeLevelOrder {

    /*
        层序遍历即可，记录结果，在最前面插入
        结果：
            1 ms, 99.19%
            38.8 MB, 35.57%
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        // 新建一个队列，存储遍历的节点
        Queue<TreeNode> queue = new LinkedList<>();

        // 放入根节点
        queue.add(root);

        // 遍历队列
        while (!queue.isEmpty()) {
            // 建立本层的集合
            List<Integer> level = new LinkedList<>();
            // 当前队列的长度即为本层节点的个数
            int size = queue.size();
            // 将本层的节点放入本层集合中
            for (int i = 0; i < size; i++) {
                // 获取节点，添加该节点的值
                TreeNode node = queue.poll();
                level.add(node.val);

                // 放入该节点的左右子节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 在结果列表的最前面插入，即可实现逆序
            result.add(0, level);

        }
        // 返回结果
        return result;
    }
}
