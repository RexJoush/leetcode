package y2020.m11November.day29MinimumDepthOfBinaryTree;

import datastructure.TreeNode;

public class QueueNode {
    TreeNode node;
    int depth;

    public QueueNode(TreeNode node, int depth) {
        this.node = node;
        this.depth = depth;
    }
}
