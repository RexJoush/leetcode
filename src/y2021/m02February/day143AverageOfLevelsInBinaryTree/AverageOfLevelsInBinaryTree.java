package y2021.m02February.day143AverageOfLevelsInBinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Rex Joush
 * @time 2021.02.27
 */

/*
    二叉树的层平均值
    https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/

    给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。

    示例 1：
        输入：
            3
           / \
          9  20
            /  \
           15   7
        输出：[3, 14.5, 11]
        解释：
        第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。

    提示：
        节点值的范围在32位有符号整数范围内。

 */
public class AverageOfLevelsInBinaryTree {

    /*
        二叉树的层序遍历，计算平均值即可
        结果：
            2 ms, 94.60%
            40.4 MB, 29.72%
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int index = queue.size();
            double sum = 0;
            while (index > 0) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                index--;
                sum += poll.val;
            }
            result.add(sum / size);
        }
        return result;
    }
}
