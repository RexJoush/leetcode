package January21.day91MergeTwoBinaryTrees;

/**
 * @author Joush
 * @time 2021.01.06
 */

/*
    合并二叉树
    https://leetcode-cn.com/problems/merge-two-binary-trees/

    给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
    你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

    示例 1:
        输入:
            Tree 1                     Tree 2
                  1                         2
                 / \                       / \
                3   2                     1   3
               /                           \   \
              5                             4   7
        输出:
        合并后的树:
                 3
                / \
               4   5
              / \   \
             5   4   7

    注意: 合并必须从两个树的根节点开始。

 */
public class MergeTwoBinaryTrees {
    /*
        递归合并即可，注意是新创建一棵树，不是用原来的树
        结果：
            0 ms, 100.00%
            38.5 MB, 95.12%
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        // t1 空就返回 t2
        if (t1 == null){
            return t2;
        }
        // t2 空就返回 t1
        if (t2 == null){
            return t1;
        }
        // 都不空，建立新节点
        TreeNode root = new TreeNode(t1.val + t2.val);
        // 递归合并子节点
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);

        // 返回新的树
        return root;

    }
}
