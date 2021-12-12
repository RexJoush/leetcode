package y2021.m11November.day418MinimumAbsoluteDifferenceInBST;

/**
 * @author Rex Joush
 * @time 2021.11.29
 */

/*
    二叉搜索树的最小绝对差
    https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/

    给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。

    差值是一个正数，其数值等于两值之差的绝对值。


    示例 1：
               4
             /   \
            2     6
           / \
          1   3
        输入：root = [4,2,6,1,3]
        输出：1
    示例 2：
               1
             /   \
            0     48
                 / \
                12  49
        输入：root = [1,0,48,null,null,12,49]
        输出：1

    提示：
        树中节点的数目范围是 [2, 10^4]
        0 <= Node.val <= 10^5

 */
public class MinimumAbsoluteDifferenceInBST {

    /*
        中序遍历，然后计算差值即可
        结果：
            0 ms, 100.00%
            37.7 MB, 95.78%
     */
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    int min = Integer.MAX_VALUE;
    int base = -100001;

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);
        // 如果当前值减去之前的值小于最小值，则更新最小值
        if (node.val - base < min) {
            // 如果相同，则不更新
            min = node.val - base;
        }
        // 将当前值设为下一次的 base
        base = node.val;

        dfs(node.right);
    }

}
