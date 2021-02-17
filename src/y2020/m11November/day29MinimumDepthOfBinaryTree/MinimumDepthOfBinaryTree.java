package y2020.m11November.day29MinimumDepthOfBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Joush
 * @time 2020.11.05
 */

/*

    二叉树的最小深度
    https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/

    给定一个二叉树，找出其最小深度。
    最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

    说明：叶子节点是指没有子节点的节点。

    示例 1：
        输入：root = [3,9,20,null,null,15,7]
        输出：2
    示例 2：
        输入：root = [2,null,3,null,4,null,5,null,6]
        输出：5
    提示：
        树中节点数的范围在 [0, 100000] 内
        -1000 <= Node.val <= 1000

 */
public class MinimumDepthOfBinaryTree {


    // 广度优先搜索，bfs，即层序遍历，遍历到某一层出现空节点，则直接返回当前层数即可
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 创建一个队列
        Queue<QueueNode> queue = new LinkedList<QueueNode>();

        // 将根节点放入
        queue.offer(new QueueNode(root, 1));

        // 只要队列不空，就拿出来队头节点，进行遍历
        while (!queue.isEmpty()) {
            // 拿出队头节点
            QueueNode nodeDepth = queue.poll();
            TreeNode node = nodeDepth.node;

            // 记录当前深度
            int depth = nodeDepth.depth;

            // 如果到了叶子节点，直接返回深度
            if (node.left == null && node.right == null) {
                return depth;
            }

            // 如果左节点不空，就把左节点放入队列
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            // 如果右节点不空，就把右节点放入队列
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }

        }

        return 0;
    }

    // 深度优先搜索，dfs
    public int minDepth(TreeNode root) {

        // 如果节点为空，返回0
        if (root == null){
            return 0;
        }
        // 如果左右子节点都为空，返回 1
        if (root.left == null && root.right == null){
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;

        // 如果左节点不空，返回左节点的最小深度和当前最小深度的较小值
        if (root.left != null){
            minDepth = Math.min(minDepth(root.left), minDepth);
        }
        // 如果右节点不空，返回右节点的最小深度和当前最小深度的较小值
        if (root.right != null){
            minDepth = Math.min(minDepth(root.right), minDepth);
        }

        // 最后返回深度加上根节点
        return minDepth + 1;
    }
}
