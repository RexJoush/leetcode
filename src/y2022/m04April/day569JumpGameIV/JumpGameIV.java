package y2022.m04April.day569JumpGameIV;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.04.29
 */

/*
    跳跃游戏 IV
    https://leetcode.cn/problems/jump-game-iv/

    给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
    每一步，你可以从下标 i 跳到下标 i + 1 、i - 1 或者 j ：
        i + 1 需满足：i + 1 < arr.length
        i - 1 需满足：i - 1 >= 0
        j 需满足：arr[i] == arr[j] 且 i != j
        请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
    注意：任何时候你都不能跳到数组外面。

    示例 1：
        输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
        输出：3
        解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
    示例 2：
        输入：arr = [7]
        输出：0
        解释：一开始就在最后一个元素处，所以你不需要跳跃。
    示例 3：
        输入：arr = [7,6,9,6,9,6,9,7]
        输出：1
        解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。

    提示：
        1 <= arr.length <= 5 * 10^4
        -10^8 <= arr[i] <= 10^8

 */
public class JumpGameIV {

    /*
        BFS，此题有点类似树的层序遍历，找出到达目标点最低的层数即可
        根节点为当前访问的节点 index，共有多支子树，index + 1, index - 1, nums[i] = nums[index]
        多叉树是有向图，本体为无向图，因此使用 visited 数组标识访问过的节点
        结果：
            44 ms, 87.38%
            56.1 MB, 40.69%
     */
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        // 记录相同值有哪些下标
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 对 map 中的 key 对应的 value 重新计算，如果不存在 key，则新加入 map
            map.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }

        // 记录访问数组
        boolean[] visited = new boolean[n];
        // 层序遍历的队列
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        int result = 0;
        while (!queue.isEmpty()) {
            // 二叉树的层序遍历
            int size = queue.size();
            while (size-- > 0) {
                int index = queue.poll();
                // 满足条件，返回
                if (index == n - 1) {
                    return result;
                }
                // 把相同的值的节点加入 queue
                if (map.containsKey(arr[index])) {
                    for (int i : map.get(arr[index])) {
                        queue.offer(i);
                        visited[i] = true;
                    }
                    // 剪枝，与这个值相同的都处理了
                    map.remove(arr[index]);
                }
                //  + 1入队
                if (index + 1 < n && !visited[index + 1]) {
                    queue.offer(index + 1);
                    visited[index + 1] = true;
                }
                // -1 入队
                if (index - 1 >= 0 && !visited[index - 1]) {
                    queue.offer(index - 1);
                    visited[index - 1] = true;
                }
            }
            // 层数 +1
            result++;
        }
        return -1;
    }
}
