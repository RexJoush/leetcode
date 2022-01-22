package y2022.m01January.day470SumOfRootToLeafBinaryNumbers;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2022.01.20
 */

/*
    从根到叶的二进制数之和
    https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers/

    给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
    对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
    返回这些数字之和。题目数据保证答案是一个 32 位 整数。

    示例 1：
                1
             /     \
            0       1
          /   \   /   \
         0     1 0     1
        输入：root = [1,0,1,0,1,0,1]
        输出：22
        解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
    示例 2：
        输入：root = [0]
        输出：0
    示例 3：
        输入：root = [1]
        输出：1
    示例 4：
        输入：root = [1,1]
        输出：3

    提示：
        树中的结点数介于 1 和 1000 之间。
        Node.val 为 0 或 1 。

 */
public class SumOfRootToLeafBinaryNumbers {

    /*
        递归计算，当前节点的值等于前面的值 * 2 + 当前节点的值
        结果：
            0 ms, 100.00%
            37.8 MB, 53.58%
     */
    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    int res = 0;

    public void dfs(TreeNode node, int pre) {
        if (node == null) {
            return;
        }

        // 计算当前节点的值
        pre = pre * 2 + node.val;

        // 遇到叶子节点，计算当前的路径和，加入总和
        if (node.left == null && node.right == null) {
            res += pre;
            return;
        }
        // 递归左右子树
        dfs(node.left, pre);
        dfs(node.right, pre);
    }
}
