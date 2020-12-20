package December20.day74SymmetricTree;

/**
 * @author Joush
 * @time 2020.12.20
 */

/*
    给定一个二叉树，检查它是否是镜像对称的。
    例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
        1
       / \
      2   2
     / \ / \
    3  4 4  3
    但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
        1
       / \
      2   2
       \   \
       3    3

    进阶：
        你可以运用递归和迭代两种方法解决这个问题吗？

 */
public class SymmetricTree {

    /*
        递归搜索该树，并经进行比对
        注意，比对的时候，不是本节点的左右子树相等
        是本节点的左子树和兄弟节点的右子树，以及本节点的右子树和兄弟节点的左子树相等才算对称
        结果
            0 ms, 100.00%
            36.4 MB, 86.00%
     */
    public boolean isSymmetric(TreeNode root) {
        // 根节点为空，返回 true
        if (root == null){
            return true;
        }
        // 进入递归
        return isMirror(root.left, root.right);
    }

    boolean isMirror(TreeNode left, TreeNode right){

        // 左边和右边节点均为空，对称，返回 true
        if (null == left && null == right){
            return true;
        }

        // 左边和右边有一方不为空，不对称，返回 false
        if (null == left || null == right){
            return false;
        }

        // 值不相等，返回 false
        if (left.val != right.val){
            return false;
        }

        // 递归判断 左子树的左节点和右子树的右节点，左子树的右节点和右子树的左节点
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
