package y2022.m05May.day586DeepestLeavesSum;

import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author Rex Joush
 * @time 2022.05.16
 */

/*
    层数最深叶子节点的和
    https://leetcode.cn/problems/deepest-leaves-sum/
    
    给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。

    示例 1：
                1
             /     \
            2       3
          /   \      \
         4     5      6
        /               \
       7                 8
        输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
        输出：15
    示例 2：
        输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
        输出：19

    提示：
    树中节点数目在范围 [1, 104] 之间。
    1 <= Node.val <= 100

 */
public class DeepestLeavesSum {

    /*
        层序遍历，最后一层加起来就行
        结果：
            6 ms, 53.91%
            43.9 MB, 44.22%
     */
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.add(root);
        int result = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            result = 0;
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (poll.left!= null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                size--;
                result += poll.val;
            }
        }
        return result;
    }
    
}
