package y2021.m03March.day155LeafSimilarTrees;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.03.11
 */

/*
    叶子相似的树
    https://leetcode-cn.com/problems/leaf-similar-trees/

    请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
                3
            /       \
           5         1
         /   \       |  \
        6     2      9   8
            /  \
           7    4
    举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
    如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
    如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
    
    示例 1：
                    3                           3
                /       \                   /       \
               5         1                 5         1
             /   \       |  \            /   \     /   \
            6     2      9   8          6     7   4     2
                /  \                                  /  \
               7    4                                9    8
        输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
        输出：true
    示例 2：
        输入：root1 = [1], root2 = [1]
        输出：true
    示例 3：
        输入：root1 = [1], root2 = [2]
        输出：false
    示例 4：
        输入：root1 = [1,2], root2 = [2,2]
        输出：true
    示例 5：
                1           1
              /   \       /   \
             2     3     3     2
        输入：root1 = [1,2,3], root2 = [1,3,2]
        输出：false
     
    
    提示：
        给定的两棵树可能会有 1 到 200 个结点。
        给定的两棵树上的值介于 0 到 200 之间。

 */
public class LeafSimilarTrees {

    /*
        遍历第一棵树，找到所有的叶子节点，存入链表中，同样的结果，遍历第二棵树，看两棵树遍历的结果是否相同
        结果：
            0 ms, 100.00%
            36.3 MB, 23.53%
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();

        dfs(root1, list1);
        dfs(root2, list2);

        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;

    }

    /*
        先序遍历找到所有的叶子节点，存入链表
     */
    public void dfs(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }

        dfs(root.left, result);

        // 叶子节点
        if (root.left == null && root.right == null) {
            result.add(root.val);
        }

        dfs(root.right, result);
    }

}
