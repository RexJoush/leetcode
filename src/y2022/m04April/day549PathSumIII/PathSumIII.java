package y2022.m04April.day549PathSumIII;

import datastructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2022.04.09
 */

/*
    路径总和 III
    https://leetcode-cn.com/problems/path-sum-iii/

    给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
    路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

    示例 1：
                    10
                /        \
               5          -3        其中 [5,3], [5,2,1], [-3,11] 均满足
             /   \          \
            3     2         11
           / \     \
          3   -2    1
        输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
        输出：3
        解释：和等于 8 的路径有 3 条，如图所示。
    示例 2：
        输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
        输出：3

    提示:
        二叉树的节点个数的范围是 [0,1000]
        -10^9<= Node.val <= 10^9
        -1000<= targetSum<= 1000

 */
public class PathSumIII {

    /*
        哈希表记录前缀和即可
            统计以每个节点为「路径结尾」的合法数量的话
            配合原本就是「从上往下」进行的数的遍历（最完整的路径必然是从原始根节点到当前节点的唯一路径）
            相当于只需要在完整路径中找到有多少个节点到当前节点的路径总和为 targetSum

            于是这个树上问题彻底转换一维问题：
                求解从原始起点（根节点）到当前节点 b 的路径中，
                有多少节点 a 满足 sum[a...b] = target
            由于从原始起点（根节点）到当前节点的路径唯一，因此这其实是一个「一维前缀和」问题。
            具体的
                我们可以在进行树的遍历时，记录下从原始根节点 root 到当前节点 cur 路径中，
                从 root 到任意中间节点 x 的路径总和，
                配合哈希表，快速找到满足以 cur 为「路径结尾」的
                使得路径总和为 targetSum 的目标「路径起点」有多少个。
            由于我们只能统计往下的路径，但是树的遍历会同时搜索两个方向的子树。
            因此我们应当在搜索完以某个节点为根的左右子树之后，
            应当回溯地将路径总和从哈希表中删除，防止统计到跨越两个方向的路径。
        结果：
            2 ms, 100.00%
            40.9 MB, 52.55%
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        //
        map.put(0, 1);
        dfs(root, root.val, targetSum);
        return res;
    }

    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public void dfs(TreeNode node, int val, int target) {
        // 当前前缀和等于目标值，则计数 +1
        if (map.containsKey(val - target)) {
            res += map.get(val - target);
        }
        // 记录到当前节点的前缀和
        map.put(val, map.getOrDefault(val, 0) + 1);
        // 递归左右子树
        if (node.left != null) {
            dfs(node.left, val + node.left.val, target);
        }
        if (node.right != null) {
            dfs(node.right, val + node.right.val, target);
        }
        // 回溯，回退到前一步时，那么到达当前节点的路径和的个数就要减 1
        map.put(val, map.getOrDefault(val, 0) - 1);
    }
}
