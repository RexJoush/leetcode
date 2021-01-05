package January21.day90InvertBinaryTree;

/**
 * @author Joush
 * @time 2020.01.05
 */

/*
    翻转二叉树（剑指 Offer 27）
    https://leetcode-cn.com/problems/invert-binary-tree/

    翻转一棵二叉树。

    示例：
        输入：

             4
           /   \
          2     7
         / \   / \
        1   3 6   9
        输出：

             4
           /   \
          7     2
         / \   / \
        9   6 3   1

 */
public class InvertBinaryTree {

    /*
        简化写法
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /*
        递归交换左右子树即可
        结果：
            0 ms, 100.00%
            35.8 MB, 65.84%
     */
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    public void invert(TreeNode root) {
        // 节点为空，直接返回
        if (root == null) {
            return ;
        }
        // 左节点为空，就将左右子树交换，递归反转交换后的左子树
        if (root.left == null) {
            root.left = root.right;
            root.right = null;
            invert(root.left);
            return;
        }
        // 右节点为空，就将左右子树交换，递归反转交换后的右子树
        if (root.right == null){
            root.right = root.left;
            root.left = null;
            invert(root.right);
            return;
        }
        // 都不为空，就交换左右节点，在递归交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invert(root.left);
        invert(root.right);
    }

}
