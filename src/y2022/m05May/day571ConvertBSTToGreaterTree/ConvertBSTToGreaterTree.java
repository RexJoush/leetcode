package y2022.m05May.day571ConvertBSTToGreaterTree;

import datastructure.TreeNode;

/**
 * @author Rex Joush
 * @time 2022.05.01
 */

/*
    把二叉搜索树转换为累加树
    https://leetcode.cn/problems/convert-bst-to-greater-tree/
    
    给出二叉搜索树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
    使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
    提醒一下，二叉搜索树满足下列约束条件：
    节点的左子树仅包含键 小于 节点键的节点。
    节点的右子树仅包含键 大于 节点键的节点。
    左右子树也必须是二叉搜索树。

    示例 1：
                      4(30)
                   /         \
                 1(36)      6(21)
               /     \     /     \
              0(26) 2(35) 5(26)   7(15)
                       \           \
                      3(33)        8(8)

    输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
    输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
    示例 2：
        输入：root = [0,null,1]
        输出：[1,null,1]
    示例 3：
        输入：root = [1,0,2]
        输出：[3,3,2]
    示例 4：
        输入：root = [3,2,4,1]
        输出：[7,9,4,10]


    提示：
        树中的节点数介于 0 和 10^4 之间。
        每个节点的值介于 -10^4 和 10^4 之间。
        树中的所有值 互不相同 。
        给定的树为二叉搜索树。

 */
public class ConvertBSTToGreaterTree {

    /*
        因为时二叉搜索树，右侧比中间大，左侧比中间小
        因此先遍历右子树，再遍历根节点，最后遍历左子树，记录节点和即可
        结果：
            0 ms, 100.00%
            41.7 MB, 38.37%
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        RDL(root);
        return root;
    }

    public void RDL(TreeNode node) {
        if (node == null) {
            return;
        }
        // 先遍历右子树
        RDL(node.right);
        // 计算和
        sum += node.val;
        // 赋值
        node.val = sum;
        // 左子树
        RDL(node.left);
    }
}
