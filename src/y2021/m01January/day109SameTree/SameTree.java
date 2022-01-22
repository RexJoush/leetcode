package y2021.m01January.day109SameTree;

import datastructure.TreeNode;

/**
 * @author Joush
 * @time 2021.01.24
 */
public class SameTree {

    /*
        递归遍历两棵树即可
        结果：
            0 ms, 100.00%
            35.9 MB, 29.43%
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 两个均为空就返回 true
        if (p == null && q == null) {
            return true;
        }
        // 某个为空就返回 false
        if (p == null || q == null){
            return false;
        }
        // 值不同返回 false
        if (p.val != q.val){
            return false;
        }
        // 递归判断左右子树
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
