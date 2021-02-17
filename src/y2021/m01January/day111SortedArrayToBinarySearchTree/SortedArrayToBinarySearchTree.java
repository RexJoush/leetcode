package y2021.m01January.day111SortedArrayToBinarySearchTree;

/**
 * @author Joush
 * @time 2021.01.26
 */

/*
    将有序数组转换为二叉搜索树
    https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/

    将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
    本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

    示例:
        给定有序数组: [-10,-3,0,5,9],
        一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

              0
             / \
           -3   9
           /   /
         -10  5

 */
public class SortedArrayToBinarySearchTree {
    /*
        中序遍历，递归创建即可。不断找到中间节点的位置进行创建
        结果：
            0 ms, 100.00%
            38.5 MB, 14,27%
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return createTree(nums, 0, nums.length - 1);
    }

    public TreeNode createTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 取中间节点
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        // 递归创建即可
        node.left = createTree(nums, left, mid - 1);
        node.right = createTree(nums, mid + 1, right);

        return node;
    }

}
