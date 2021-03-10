package y2021.m02February.day128LowestCommonAncestor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Joush
 * @time 2021.02.12
 */

/*
    二叉搜索树的最近公共祖先
    https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

    给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

    百度百科中最近公共祖先的定义为：
    “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
    满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
    例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
                     6
                 /      \
                2        8
             /    \    /   \
            0     4   7     9
                /   \
               3     5
    示例 1:
        输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
        输出: 6
        解释: 节点 2 和节点 8 的最近公共祖先是 6。
    示例 2:
        输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
        输出: 2
        解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。

    说明:
        所有节点的值都是唯一的。
        p、q 为不同节点且均存在于给定的二叉搜索树中。

 */

public class LowestCommonAncestor {

    /*
        一次遍历，当前节点如果比 p,q 都小，那就去右子树遍历，如果都大，那就去左子树
        如果比一个大一个小，那当前节点就是分叉点，返回即可.
        结果：
            6 ms, 100.00%
            39.2 MB, 74.85%;
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        // 当前节点比两个节点都大，就去左子树找
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 当前节点比两个节点都小
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // 当前节点不满足，即当前节点为所求结果
        return root;

    }


    /*
        两次遍历，记录找到两个节点的路径，然后找到两条路径的分叉点即可
        结果：
            7 ms, 33.07%
            39.4 MB, 43.65%
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 定义两个节点的路径列表
        List<TreeNode> pPath = new LinkedList<>();
        List<TreeNode> qPath = new LinkedList<>();
        // 找到两个节点
        findNode(root, p, pPath);
        findNode(root, q, qPath);

        // 记录较短的一个路径长度
        int low = Math.min(pPath.size(), qPath.size());
        // 记录较长的路径集合
        List<TreeNode> big = pPath.size() > qPath.size() ? pPath : qPath;

        // 找到第一个分叉点，即从头遍历，第一个不相等的节点
        for (int i = 0; i < low; i++) {
            if (pPath.get(i) != qPath.get(i)) {
                return pPath.get(i-1);
            }
        }
        // 当某个数组遍历结束时，返回较长数组的较短长度位置的值
        return big.get(low - 1);

    }

    /*
        找到根节点到当前节点的路径
     */
    public void findNode(TreeNode current,TreeNode target, List<TreeNode> path) {
        // 将当前节点加入
        path.add(current);
        if (current.val == target.val) {
            return;
        }
        if (current.val < target.val) {
            findNode(current.right, target, path);
        }
        else {
            findNode(current.left, target, path);
        }
    }

}
