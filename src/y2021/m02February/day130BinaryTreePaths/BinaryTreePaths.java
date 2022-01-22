package y2021.m02February.day130BinaryTreePaths;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2021.02.14
 */

/*
    二叉树的所有路径
    https://leetcode-cn.com/problems/binary-tree-paths/

    给定一个二叉树，返回所有从根节点到叶子节点的路径。
    说明: 叶子节点是指没有子节点的节点。

    示例:
        输入:

           1
         /   \
        2     3
         \
          5

        输出: ["1->2->5", "1->3"]
        解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3

 */
public class BinaryTreePaths {

    /*
        深度优先遍历即可
        结果：
            1 ms, 100.00%
            38.5 MB, 84.31%
     */
    public List<String> binaryTreePaths(TreeNode root) {

        List<String> paths = new ArrayList<String>();
        findPath(root, "", paths);
        return paths;
    }

    public void findPath(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuilder pathSB = new StringBuilder(path);
            // 结果字符串加入当前节点
            pathSB.append(Integer.toString(root.val));

            // 当前节点是叶子节点
            if (root.left == null && root.right == null) {
                // 把路径加入到答案中
                paths.add(pathSB.toString());
            } else {
                // 当前节点不是叶子节点，继续递归遍历
                pathSB.append("->");
                findPath(root.left, pathSB.toString(), paths);
                findPath(root.right, pathSB.toString(), paths);
            }
        }
    }
}
