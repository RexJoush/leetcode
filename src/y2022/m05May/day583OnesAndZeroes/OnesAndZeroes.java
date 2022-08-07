package y2022.m05May.day583OnesAndZeroes;

/**
 * @author Rex Joush
 * @time 2022.05.13
 */

/*
    一和零
    https://leetcode.cn/problems/ones-and-zeroes/
    
    给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
    请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。 
    如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
    
    示例 1：
        输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
        输出：4
        解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
        其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
    示例 2：
        输入：strs = ["10", "0", "1"], m = 1, n = 1
        输出：2
        解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
    
    提示：
        1 <= strs.length <= 600
        1 <= strs[i].length <= 100
        strs[i] 仅由 '0' 和 '1' 组成
        1 <= m, n <= 100

 */
public class OnesAndZeroes {

    /*
        空间优化，去掉物品维度，从大到小算，同时在遍历物品的过程中进行计算，进一步提高效率
        结果：
            16 ms, 97.89%
            41.3 MB, 28.80%
     */
    public int findMaxForm3(String[] strs, int m, int n) {

        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            // 先统计个数
            int zero = 0;
            int one = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            // dp 递推
            for (int j = m; j >= zero; j--) {
                for (int k = n; k >= one; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zero][k - one] + 1);
                }
            }
        }

        return dp[m][n];
    }

    /*
        多维背包，限制条件有两个，即 0 的数量和 1 的数量
        定义 dp[i][j][k] 表示前 i 个句子 0 的数量为 j, 1 的数量为 k 的最大方案数
        dp[i][j][k] = dp[i-1][j-str0[i]][k-str1[i]], 其中 str0,1 分别表示 str[i] 中的 0 和 1 的个数
        结果：
            33 ms, 27.81%
            67.3 MB, 12.60%
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l][m + 1][n + 1];

        // 第一个句子
        int[] str = getZeroOneNumber(strs[0]);
        for (int j = str[0]; j <= m; j++) {
            for (int k = str[1]; k <= n; k++) {
                dp[0][j][k] = 1;
            }
        }
        // 后续的句子
        for (int i = 1; i <= l; i++) {
            str = getZeroOneNumber(strs[i]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int a = dp[i - 1][j][k];
                    int b = j >= str[0] && k >= str[1] ? dp[i - 1][j - str[0]][k - str[1]] + 1 : 0;
                    dp[i][j][k] = Math.max(a, b);
                }
            }
        }
        return dp[l - 1][m][n];
    }

    /*
        第一次和后续并起来
        结果：
            34 ms, 27.48%
            66.6 MB, 22.40%
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l + 1][m + 1][n + 1];

        // 后续的句子
        for (int i = 1; i <= l; i++) {
            int[] str = getZeroOneNumber(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int a = dp[i - 1][j][k];
                    int b = j >= str[0] && k >= str[1] ? dp[i - 1][j - str[0]][k - str[1]] + 1 : 0;
                    dp[i][j][k] = Math.max(a, b);
                }
            }
        }
        return dp[l][m][n];
    }

    private int[] getZeroOneNumber(String str) {
        int zero = 0;
        int one = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                zero++;
            } else {
                one++;
            }
        }
        return new int[]{zero, one};
    }

}
