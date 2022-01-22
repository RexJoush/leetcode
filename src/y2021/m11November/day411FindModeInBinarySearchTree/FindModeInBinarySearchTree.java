package y2021.m11November.day411FindModeInBinarySearchTree;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.11.22
 */

/*
    二叉搜索树中的众数
    https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/

    给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
    假定 BST 有如下定义：
    结点左子树中所含结点的值小于等于当前结点的值
    结点右子树中所含结点的值大于等于当前结点的值
    左子树和右子树都是二叉搜索树
    例如：
    给定 BST [1,null,2,2],

       1
        \
         2
        /
       2
    返回[2].

    提示：如果众数超过1个，不需考虑输出顺序

 */
public class FindModeInBinarySearchTree {

    /*
        中序遍历
            BST 的中序遍历结果是一个非递减数列，因此遍历完成后，统计该序列即可
            将每个数字的个数记录进哈希表中，即可找到最高的数字个数
            此时，空间复杂度为 O(n)
        优化后，
            考虑，不存储哈希表，因为非递减序列的每个相同的数字都相邻排列
            在遍历序列时，记录当前元素和当前元素的个数，同时记录最大值。
        继续优化，
            考虑不存储当前序列，在遍历时进行记录即可将空间优化为 O(1)

        结果：
            0 ms, 100.00%
            38.9 MB, 53.67%
     */
    public int[] findMode(TreeNode root) {
        order(root);
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    // 结果列表
    List<Integer> result = new ArrayList<>();
    // 序列中的当前值
    int base = 0;
    // 计数
    int count = 0;
    // 众数的个数
    int maxCount = 0;

    public void order(TreeNode root) {
        if (root == null) {
            return;
        }

        // 递归左子树
        order(root.left);

        // 更新操作
        // 1.判断当前值是否增加，如果没改变，则 count++
        if (root.val == base) {
            count++;
        } else {
            // 2.否则将此时的 base 置为当前值，count 置为 1
            count = 1;
            base = root.val;
        }

        // 3.判断当前值的个数是否等于众数的个数，等于则加入结果
        if (count == maxCount) {
            result.add(base);
        }
        // 4.如果当前值的个数大于最大值，则将结果清空，将此时的最大值更新
        if (count > maxCount) {
            maxCount = count;
            result.clear();
            result.add(base);
        }
        // 递归右子树
        order(root.right);
    }
}
