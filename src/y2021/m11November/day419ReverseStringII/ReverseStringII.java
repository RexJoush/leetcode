package y2021.m11November.day419ReverseStringII;

/**
 * @author Rex Joush
 * @time 2021.11.30
 */

/*
    反转字符串 II
    https://leetcode-cn.com/problems/reverse-string-ii/

    给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
    如果剩余字符少于 k 个，则将剩余字符全部反转。
    如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。

    示例 1：
        输入：s = "abcdefg", k = 2
        输出："bacdfeg"
    示例 2：
        输入：s = "abcd", k = 2
        输出："bacd"

    提示：
        1 <= s.length <= 10^4
        s 仅由小写英文组成
        1 <= k <= 10^4

 */
public class ReverseStringII {

    /*
        遍历反转即可
        结果：
            2 ms, 16.29%
            38.8 MB, 5.02%
     */
    public String reverseStr(String s, int k) {
        int i = 0;
        while (i < s.length()) {

            // 获取当前子段的字符串
            String sub = "";
            if (i + 2 * k > s.length()) {
                sub = s.substring(i);
            } else {
                sub = s.substring(i, i + 2 * k);
            }

            String res = "";
            // 当前字符串在 0-k 之间
            if (sub.length() < k) {
                res = reverse(sub) + "";
            }
            // 当前字符串在 k-2k 之间
            else if (sub.length() < 2 * k) {
                res = reverse(sub.substring(0, k)) + sub.substring(k);
            } else {
                res = reverse(sub.substring(0, k)) + s.substring(i + k);
            }
            s = s.substring(0, i) + res;
            if (i + 2 * k > s.length()) {
                break;
            } else {
                i += (2 * k);
            }
        }
        return s;
    }

    /*
        遍历反转即可
        结果：
            0 ms, 100.00%
            38.3 MB, 83.60%
     */
    public String reverseStr2(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    public String reverse(String reverse) {
        StringBuilder stringBuilder = new StringBuilder(reverse);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

}
