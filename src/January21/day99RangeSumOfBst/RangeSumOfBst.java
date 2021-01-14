package January21.day99RangeSumOfBst;

/**
 * @author Joush
 * @time 2021.01.14
 */

/*
    二叉搜索树的范围和
    https://leetcode-cn.com/problems/range-sum-of-bst/

    给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。

    示例 1：
            10
           /   \
          5     15
         / \     \
        3   7     18
        输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
        输出：32
    示例 2：
              10
             /   \
            5     15
           / \    / \
          3   7  13 18
         /   /
        1   6
        输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
        输出：23

    提示：
        树中节点数目在范围 [1, 20000] 内
        1 <= Node.val <= 10^5
        1 <= low <= high <= 10^5
        所有 Node.val 互不相同

 */
public class RangeSumOfBst {
    public int rangeSumBST(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return result;
    }
    int result = 0;

    /*
        遍历二叉搜索树即可，因为二叉搜索树的特性，无需遍历整棵树
        当根节点比 low 低时，遍历右树，当根节点比 high 高时，遍历左树
        结果：
            0 ms, 100.00%
            46.7 MB, 10.11%
     */
    public void dfs(TreeNode root, int low, int high) {
        if (root == null)
            return;
        if (root.val < low) {
            dfs(root.right, low, high);
        }
        else if (root.val > high) {
            dfs(root.left, low, high);
        } else {
            result += root.val;
            dfs(root.right, low, high);
            dfs(root.left, low, high);
        }

    }
}
