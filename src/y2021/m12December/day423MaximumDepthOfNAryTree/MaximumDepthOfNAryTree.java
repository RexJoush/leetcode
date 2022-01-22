package y2021.m12December.day423MaximumDepthOfNAryTree;

import datastructure.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Rex Joush
 * @time 2021.12.04
 */

/*
    N 叉树的最大深度
    https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
    
    给定一个 N 叉树，找到其最大深度。
    最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
    N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。

    示例 1：
                    1
                /   |   \
               3    2    4
             /   \
            5     6
        输入：root = [1,null,3,2,4,null,5,6]
        输出：3

    示例 2：
                         1
                /     /     \    \
               2     3       4    5
                   /   \     |   /  \
                  6     7    8  9   10
                        |    |  |
                       11   12  13
                        |
                       14
        输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
        输出：5

    提示：
        树的深度不会超过 1000 。
        树的节点数目位于 [0, 10^4] 之间。

 */
public class MaximumDepthOfNAryTree {

    /*
        方法一：dfs
        深度优先搜索，当前节点的最大深度，等于 Max(所有子节点) + 1;
        结果：
            0 ms, 100.00%
            38.3 MB, 93.54%
     */
    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }

        // 最大深度
        int maxChildDepth = 0;
        List<Node> children = root.children;

        // 遍历子节点
        for (Node child : children) {
            // 当前节点的最大深度
            int childDepth = maxDepth2(child);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }
        return maxChildDepth + 1;
    }

    /*
        方法二：层序遍历（广度优先搜索），计数即可
        结果：
            1 ms, 26.95%
            37.9 MB, 99.89%
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root); // 根节点入队
        int index = 0;
        while (!queue.isEmpty()) {
            // 当前层的节点个数
            int size = queue.size();
            // 将当前层的所有子节点全部入队，每次保证队列中包含本层的所有节点
            while (size > 0) {
                // 取出队列中的所有元素，将其子节点加入队列中
                Node node = queue.poll();
                queue.addAll(node.children);
                size--;
            }
            // 当前层遍历完毕
            index++;
        }
        return index;
    }
}
