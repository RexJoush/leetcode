package y2022.m01January.day470SumOfRootToLeafBinaryNumbers;

/**
 * @author Rex Joush
 * @time 2022.01.20
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
