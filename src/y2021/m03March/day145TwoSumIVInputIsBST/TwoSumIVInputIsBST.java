package y2021.m03March.day145TwoSumIVInputIsBST;

import datastructure.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2021.03.01
 */

/*
    两数之和 IV - 输入 BST
    https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/

    给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，
    则返回 true。

    示例 1：
                   5
                /     \
               3       6
             /   \
            2     4
        输入: root = [5,3,6,2,4,null,7], k = 9
        输出: true
    示例 2：
                   5
                /     \
               3       6
             /   \      \
            2     4      7
        输入: root = [5,3,6,2,4,null,7], k = 28
        输出: false
    示例 3：
        输入: root = [2,1,3], k = 4
        输出: true
    示例 4：
        输入: root = [2,1,3], k = 1
        输出: false
    示例 5：
        输入: root = [2,1,3], k = 3
        输出: true

    提示:
        二叉树的节点个数的范围是  [1, 10^4].
        -10^4 <= Node.val <= 10^4
        root 为二叉搜索树
        -10^5 <= k <= 10^5

 */
public class TwoSumIVInputIsBST {

    /*
        二叉搜索树的先序遍历序列是一个单调不减序列，因此先序遍历后判断是否存在即可
        结果：
            45 ms, 5.18%
            39.3 MB, 57.04
     */
    public boolean findTarget(TreeNode root, int k) {
        dfs(root);
        int left = 0;
        int right = list.size() - 1;
        // 遍历此列表，左右向中间逼近
        while (left < right){
            int sum = list.get(left) + list.get(right);
            if (sum == k)
                return true;
            if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        // 遍历完毕返回 false
        return false;
    }
    List<Integer> list = new LinkedList<>();
    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }

    /*
        方法二：HashSet，遍历的过程中判断是否存在
        结果：
            3 ms, 73.10%
            39.3 MB, 57.51%
     */
    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return find(root, k, set);
    }

    private boolean find(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        // 如果 set 集合中包括当前的两数和，返回 true
        if (set.contains(k - root.val)) {
            return true;
        }
        // 将当前元素加入 set 集合
        set.add(root.val);
        // 递归判断左右子树
        return find(root.left, k, set) || find(root.right, k, set);
    }

}
