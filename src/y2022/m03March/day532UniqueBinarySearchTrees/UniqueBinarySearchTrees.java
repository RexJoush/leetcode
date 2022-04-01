package y2022.m03March.day532UniqueBinarySearchTrees;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.03.23
 */

/*
    不同的二叉搜索树
    https://leetcode-cn.com/problems/unique-binary-search-trees/

    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
    返回满足题意的二叉搜索树的种数。

    示例 1：
            1       1         2        3        3
             \       \       / \      /        /
              3       2     1   3    1        2
             /         \              \      /
            2           3              2    1
        输入：n = 3
        输出：5
    示例 2：
        输入：n = 1
        输出：1

    提示：
        1 <= n <= 19

 */
public class UniqueBinarySearchTrees {

    /*
        递归过程
        对于一个二叉搜索树，中序遍历是一个递增序列，比如 [1,2,3,4,5,6]
            对于所有不同的二叉搜索树，若不同，则所有数字依次当根节点可保证不重复
            而，根节点左侧，又是一个子序列，递归的此过程，右侧也同理
        即，若以 4 为根节点，左边 3 个节点假设有 n 种二叉树，右边的两个节点假设有 m 个二叉树
            则 4 的根节点就有 m * n 种结果

        遍历 nums 数组，以 i 当树根节点，左边 i-1 个节点，右边 n-i 节点，计算可能的二叉搜索树的数量即可
        结果：
            2449 ms, 5.01%
            38.4 MB, 6.57%
     */
    public int numTrees(int n) {
        // 0 和 1 只有一种情况
        if (n == 0 || n == 1) {
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // 用 第 i 个节点当作根节点时
            // 计算左边个数
            int left = numTrees(i - 1);
            int right = numTrees(n-i);
            count += left * right;
        }
        return count;
    }

    /*
        上面的解法出现了非常多的重复计算
        即 numTrees(2) = 2 是固定的，所以我们用哈希表记录下来，直接取用即可
        结果：
            0 ms, 100.00%
            38.1 MB, 46.63%
     */
    Map<Integer, Integer> map = new HashMap<>();
    public int numTrees2(int n) {
        // 0 和 1 只有一种情况
        if (n == 0 || n == 1) {
            return 1;
        }
        // 如果已经计算过了，就直接返回
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // 用 第 i 个节点当作根节点时
            // 计算左边个数
            int left = numTrees(i - 1);
            int right = numTrees(n-i);
            count += left * right;
        }
        // 记录情况
        map.put(n, count);
        return count;
    }
}
