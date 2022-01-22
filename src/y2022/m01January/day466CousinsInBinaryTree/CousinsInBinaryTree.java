package y2022.m01January.day466CousinsInBinaryTree;

import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Rex Joush
 * @time 2022.01.16
 */

/*
    二叉树的堂兄弟节点
    https://leetcode-cn.com/problems/cousins-in-binary-tree/

    在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
    如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
    我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
    只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。

    示例 1：
            1
          /   \
         2     3
        /
       4
    输入：root = [1,2,3,4], x = 4, y = 3
    输出：false
    示例 2：
            1
          /   \
         2     3
          \     \
           4     5
    输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
    输出：true
    示例 3：
            1
          /   \
         2     3
          \
           4
    输入：root = [1,2,3,null,4], x = 2, y = 3
    输出：false

    提示：
        二叉树的节点数介于 2 到 100 之间。
        每个节点的值都是唯一的、范围为 1 到 100 的整数。

 */
public class CousinsInBinaryTree {

    /*
        层序遍历，需保证不同父亲，且位于同一层
        结果：
            1 ms, 32.21%
            36 MB, 67.39%
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        int dx = 0; // 深度
        int dy = 0; // 深度

        TreeNode fx = null;
        TreeNode fy = null;

        int h = 1;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                // 取出队头元素
                TreeNode node = queue.poll();

                // 判断左子树
                if (node.left != null) {
                    int val = node.left.val;

                    // 记录深度和父节点
                    if (val == x) {
                        dx = h;
                        fx = node;
                    } else if (val == y) {
                        dy = h;
                        fy = node;
                    }
                    queue.offer(node.left);
                }

                // 判断右子树
                if (node.right != null) {
                    int val = node.right.val;

                    // 记录深度和父节点
                    if (val == x) {
                        dx = h;
                        fx = node;
                    } else if (val == y) {
                        dy = h;
                        fy = node;
                    }
                    queue.offer(node.right);
                }
            }
            h++; // 深度加 1
        }
        // 深度相同，且父节点不同
        return dx == dy && fx != fy;
    }
}
