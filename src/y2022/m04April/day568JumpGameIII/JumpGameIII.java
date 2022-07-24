package y2022.m04April.day568JumpGameIII;

/**
 * @author Rex Joush
 * @time 2022.04.28
 */

/*
    跳跃游戏 III
    https://leetcode.cn/problems/jump-game-iii/
    
    这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
    请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
    注意，不管是什么情况下，你都无法跳到数组之外。

    示例 1：
        输入：arr = [4,2,3,0,3,1,2], start = 5
        输出：true
        解释：
        到达值为 0 的下标 3 有以下可能方案：
        下标 5 -> 下标 4 -> 下标 1 -> 下标 3
        下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
    示例 2：
        输入：arr = [4,2,3,0,3,1,2], start = 0
        输出：true
        解释：
        到达值为 0 的下标 3 有以下可能方案：
        下标 0 -> 下标 4 -> 下标 1 -> 下标 3
    示例 3：
        输入：arr = [3,0,2,1,2], start = 2
        输出：false
        解释：无法到达值为 0 的下标 1 处。

    提示：
        1 <= arr.length <= 5 * 10^4
        0 <= arr[i] < arr.length
        0 <= start < arr.length

 */
public class JumpGameIII {

    /*
        回溯法，遍历所有的可能地址即可
        结果：
            2 ms, 97.91%
            53.1 MB, 28.33%
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return dfs(arr, visited, start);
    }

    private boolean dfs(int[] arr, boolean[] visited, int index) {
        // 越界或已访问返回 false
        if (index >= arr.length || index < 0 || visited[index]) {
            return false;
        }
        // 当前值为 0 返回 true
        if (arr[index] == 0) {
            return true;
        }
        // 标识当前点到达过
        visited[index] = true;
        // 递归遍历即可
        return dfs(arr, visited, index - arr[index]) || dfs(arr, visited, index + arr[index]);
    }

}
