package y2021.m12December.day427SubtreeOfAnotherTree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2021.12.08
 */

/*
    另一棵树的子树
    https://leetcode-cn.com/problems/subtree-of-another-tree/

    给你两棵二叉树 root 和 subRoot 。
    检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true；
    否则，返回 false。
    二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。
    tree 也可以看做它自身的一棵子树。

    示例 1：
            3
         /     \
        4       5       4
       / \             /  \
      1   2           1    2
    输入：root = [3,4,5,1,2], subRoot = [4,1,2]
    输出：true
    示例 2：
                3
             /     \
            4       5       4
           / \             /  \
          1   2           1    2
             /
            0
    输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
    输出：false

    提示：
        root 树上的节点数量范围是 [1, 2000]
        subRoot 树上的节点数量范围是 [1, 1000]
        -10^4 <= root.val <= 10^4
        -10^4 <= subRoot.val <= 10^4

 */
public class SubtreeOfAnotherTree {

    /*
        方法一：暴力遍历
        查看子树是否与树的某一子树是同一棵树
        结果：
            3 ms, 83.89%
            38.1 MB, 95.74%
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        // 判断字数是否与母树相同，或者子树与某个子树相同
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    /*
        判断两棵树是否为同一棵树
     */
    public boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }

        return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }

    /*
        方法二：树哈希
        考虑把每个子树都映射成一个唯一的值
        那么只需判断，root 的某个子树哈希是否 subRoot 的哈希值一样即可

        定义哈希函数
            f(node) = node.val + 31 * f(node.left) * p(x1) + 179 * f(node.right) * p(x2)
            解释：当前节点的哈希值 =
                        当前节点的值 +
                        31 * 左子树的哈希值 * 某个素数 +
                        179 * 右子树的哈希值 * 另外一个素数

            此处，31，179 可以任意定义。
            考虑，此处的哈希值有可能会出现冲突，但冲突几率已经很小，若想进一步减小冲突的发生，可以考虑双哈希，三哈希，四哈希等。
            即 f(node) = f1(node) + f2(node) + f3(node) + ...
        结果：
            3 ms, 84.00%
            38.9 MB, 99.00%
     */

    // 用来存储 root 数的哈希值列表
    Set<Integer> hash = new HashSet<>();

    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        // 将母树的哈希值存入 set 集合中
        dfs(root);
        // 计算子树的哈希值
        int sub = getHash(subRoot);
        return hash.contains(sub);
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        hash.add(getHash(node));
        dfs(node.right);
    }

    public int getHash(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.val + 31 * getHash(node.left) + 37 * getHash(node.right);
    }
}
