package y2021.m12December.day430NAryTreePostorderTraversal;

import datastructure.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.12.11
 */

/*
    N 叉树的后序遍历
    https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/

    给定一个 N 叉树，返回其节点值的 前序遍历 。
    N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。

    进阶：
        递归法很简单，你可以使用迭代法完成此题吗?

    示例 1：
                    1
                /   |   \
               3    2    4
             /   \
            5     6

        输入：root = [1,null,3,2,4,null,5,6]
        输出：[5,6,3,2,4,1]
    示例 2：
                         1
                /     /     \    \
               2     3       4    5
                   /   \     |   /  \
                  6     7    8  9   10
                        |    |  |
                       11   12  13
                        |
                       14
        输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
        输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]


    提示：
        N 叉树的高度小于或等于 1000
        节点总数在范围 [0, 10^4] 内

 */
public class NAryTreePostorderTraversal {

    /*
        递归后序遍历即可
        结果：
            1 ms, 50.10%
            39 MB, 80.00%
     */
    List<Integer> res = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        preOrder(root);
        return res;
    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            preOrder(child);
        }
        res.add(root.val);
    }

}
