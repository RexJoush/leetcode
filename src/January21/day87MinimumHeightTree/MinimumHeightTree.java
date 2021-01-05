package January21.day87MinimumHeightTree;

/**
 * @author Joush
 * @time 2021.01.02
 */

/*
    最小高度树
    https://leetcode-cn.com/problems/minimum-height-tree-lcci/

    给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。

    示例:
    给定有序数组: [-10,-3,0,5,9],
    一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

              0
             / \
           -3   9
           /   /
         -10  5

 */
public class MinimumHeightTree {

    /*
        递归创建即可
        结果：
             0 ms, 100.00%
             38 MB, 90.85%
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return createTree(nums, 0, nums.length - 1);
    }

    /*
        递归函数
     */
    public TreeNode createTree(int[] nums, int start, int end){
        // 二分法终止条件
        if (start > end) {
            return null;
        }
        // 不断寻找中点定为根节点
        int mid = (end - start) / 2 + start;
        // 创建节点
        TreeNode root = new TreeNode(nums[mid]);

        // 递归创建左右子树
        root.left = createTree(nums, start, mid - 1);
        root.right = createTree(nums, mid + 1, end);

        return root;
    }
}
