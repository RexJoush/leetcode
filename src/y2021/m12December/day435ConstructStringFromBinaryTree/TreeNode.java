package y2021.m12December.day435ConstructStringFromBinaryTree;

/**
 * @author Rex Joush
 * @time 2021.12.16
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
