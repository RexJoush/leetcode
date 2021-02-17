package y2021.m01January.day113PathSum;

/**
 * @author Joush
 * @time 2021.01.28
 */

/*
    路径总和
    https://leetcode-cn.com/problems/path-sum/

    给你二叉树的根节点 root 和一个表示目标和的整数 targetSum，判断该树中是否存在 根节点到叶子节点 的路径。
    这条路径上所有节点值相加等于目标和 targetSum。
    叶子节点 是指没有子节点的节点。

    示例 1：
                   [5]
                 /      \
               [4]       8
             /         /    \
           [11]       13     4
           /  \               \
          7   [2]              1
        输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
        输出：true
    示例 2：
                1
               / \
              2   3
        输入：root = [1,2,3], targetSum = 5
        输出：false
    示例 3：
        输入：root = [1,2], targetSum = 0
        输出：false

    提示：
        树中节点的数目在范围 [0, 5000] 内
        -1000 <= Node.val <= 1000
        -1000 <= targetSum <= 1000

 */
public class PathSum {

    /*
        递归思想
        功能为，从当前节点到叶子节点是否满足路径和为 sum
        假定，从根节点到当前节点的值为 val
        问题转化，是否存在从当前节点到叶子节点的路径和为 sum - val
        递归，若当前节点就是叶子节点
               那么我们直接判断 sum 是否等于 val 即可（因为路径和已经确定，就是当前节点的值，我们只需要判断该路径和是否满足条件）。
             若当前节点不是叶子节点
                我们只需要递归地询问它的子节点是否能满足条件即可。

        结果：
            0 ms, 100.00%
            38.5 MB, 23.94%
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 空树直接放回 false
        if (root == null) {
            return false;
        }
        // 叶子节点，判断 sum 等于 val
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        // 否则递归判断左右子树即可
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);

    }

}
