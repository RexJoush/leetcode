package y2021.m12December.day420DiameterOfBinaryTree;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2021.12.01
 */

/*
    二叉树的直径
    https://leetcode-cn.com/problems/diameter-of-binary-tree/
    
    给定一棵二叉树，你需要计算它的直径长度。
    一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
    这条路径可能穿过也可能不穿过根结点。

    示例 :
        给定二叉树
                  1
                 / \
                2   3
               / \
              4   5
        返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
        注意：两结点之间的路径长度是以它们之间边的数目表示。

 */
public class DiameterOfBinaryTree {

    /*
        深度优先搜索
        首先一条路径等于经过该路径的节点数减 1，所以求路径等效于求经过节点数的最大值减 1
        任一条路径可以看作是从该点出发，从其左右吧子树向下遍历得到的。
        所以即算法流程为
            当前节点的左右最大深度和 = max(L, R) + 1
        当前节点的最长路径值为
            d = L + R + 1
        所以，计算每个节点的 d，并找出最大值即可
        结果：
            0 ms, 100.00%
            38.2 MB, 83.36%
     */
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        dfs(root);
        return ans - 1;
    }

    int ans;

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0; // 空结点
        }
        int L = dfs(root.left);
        int R = dfs(root.right);

        // 更新最大值
        ans = Math.max(ans, L + R + 1);
        // 返回当前节点的左右节点较大值 + 1
        return Math.max(L, R) + 1;
    }

}
