package y2022.m04April.day559ShortestPalindrome;

/**
 * @author Rex Joush
 * @time 2022.04.19
 */

/*
    最短回文串
    https://leetcode.cn/problems/shortest-palindrome/
    
    给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。
    找到并返回可以用这种方式转换的最短回文串。

    示例 1：
        输入：s = "aacecaaa"
        输出："aaacecaaa"
    示例 2：
        输入：s = "abcd"
        输出："dcbabcd"
    
    提示：
        0 <= s.length <= 5 * 10^4
        s 仅由小写英文字母组成

 */
public class ShortestPalindrome {

    /*
        字符串哈希
            分析：
                需要在给定的 s 前面添加 s' 使得 s' + s 为一个回文串
                显然题目意思为，找到最短的 s' 使得满足题意
                由于一定可以将 s 去掉第一个字符后的子串反序添加到 s 前面得到一个回文串
                    举例， s = abc, 可以将 bc 反序 cb 添加到串首构成回文串 cbabc
                因此, |s'| < |s|, 其中 |s| 表示 s 的长度
                下面我们即可得到 s 分为两部分
                    长度为 s1 = |s| - |s'| 的前半部分 (如例中的 a)
                    长度为 s2 = |s'| 的后半部分(如例中的 bc)
                因为 s' + s 为回文串，那么 s' 必定与 s2 相同，且 s1 为回文串
                因此要找到最短的 s' 等价于找到最长的 s1
                即，在给定的串 s 中找到最长回文前缀 s1, 剩余的部分反序即为答案 s'
            解答：
                依然是遍历 s 的前部分串，找到最长的回文前缀
                通过将字符串映射为一个哈希值来进行判断是否为回文串
            结果：
                2 ms, 95.09%
                41.6 MB, 38.90%
     */
    public String shortestPalindrome(String s) {
        int n = s.length();
        // 找两个大质数来保证取模后减少哈希碰撞的概率
        int base = 131, mod = 1000000007;
        int left = 0;
        int right = 0;
        int mul = 1;
        int result = -1;

        for (int i = 0; i < n; ++i) {
            left = (int) (((long) left * base + s.charAt(i)) % mod);
            right = (int) ((right + (long) mul * s.charAt(i)) % mod);
            if (left == right) {
                result = i;
            }
            mul = (int) ((long) mul * base % mod);
        }
        // 得到 s2 部分
        String add = (result == n - 1 ? "" : s.substring(result + 1));
        // 取反得到 s'
        StringBuffer ans = new StringBuffer(add).reverse();
        // 接上 s 返回
        ans.append(s);
        return ans.toString();
    }
}
