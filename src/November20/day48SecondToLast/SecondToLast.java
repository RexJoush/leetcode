package November20.day48SecondToLast;

/**
 * @author Joush
 * @time 2020.11.24
 */

/*
     二叉树中第二小的节点
     https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/

    给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
    更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
    给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

    示例 1：
        输入：root = [2,2,5,null,null,5,7]
        输出：5
        解释：最小的值是 2 ，第二小的值是 5 。
    示例 2：
        输入：root = [2,2,2]
        输出：-1
        解释：最小的值是 2, 但是不存在第二小的值。

    提示：
        树中节点数目在范围 [1, 25] 内
        1 <= Node.val <= 2^31 - 1
        对于树中每个节点 root.val == min(root.left.val, root.right.val)

 */
public class SecondToLast {

    public int findSecondMinimumValue(TreeNode root) {

        return dfs(root, root.val);

    }

    /*
        因为根节点是最小的，所以查找所以子树中最小的值即可
     */
    public int dfs(TreeNode root, int minimum) {
        // 叶子端点
        if (root == null) {
            return -1;
        }
        // 如果当前结点值>根节点，那么不用再遍历它的子节点，直接返回该值
        if (root.val > minimum) {
            return root.val;
        }

        // 否则，即当前结点值等于根节点,则需要在两棵子树找目标值结点
        int l = dfs(root.left, minimum);
        int r = dfs(root.right, minimum);

        // 如果两棵子树均存在大于最小值的节点，那么返回较小的那一个
        if (l != -1 && r != -1) {
            return Math.min(l, r);
        } else {//否则，其余情况均返回较大的那一个
            return Math.max(l, r);
        }
    }

}
