package y2021.m01January.day112BalancedBinaryTree;

/**
 * @author Joush
 * @time 2021.01.27
 */

/*
    平衡二叉树
    https://leetcode-cn.com/problems/balanced-binary-tree/

    给定一个二叉树，判断它是否是高度平衡的二叉树。
    本题中，一棵高度平衡二叉树定义为：
    一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。

    示例 1：
        输入：root = [3,9,20,null,null,15,7]
        输出：true
    示例 2：
        输入：root = [1,2,2,3,3,null,null,4,4]
        输出：false
    示例 3：
        输入：root = []
        输出：true

    提示：
        树中的节点数在范围 [0, 5000] 内
        -10^4 <= Node.val <= 10^4

 */
public class BalancedBinaryTree {

    /*
        根据定义进行递归判断即可
        结果：
            1 ms, 99.65%
            38.3 MB, 88.15%
     */
    public boolean isBalanced(TreeNode root) {
        // 如果根节点的高度大于0，则表示平衡
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        // 如果为空，则返回高度为 0
        if (root == null) {
            return 0;
        }
        // 求左子树高度
        int leftHeight = height(root.left);
        // 求右子树高度
        int rightHeight = height(root.right);

        // 如果左右子树高度相差超过 1，就返回高度为-1，表示不平衡，此处包含了递归的左右子树不平衡的情况
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            // 否则就返回子树的节点较高的 +1
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
