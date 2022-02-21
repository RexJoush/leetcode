package y2022.m01January.day481MultiplyStrings;

/**
 * @author Rex Joush
 * @time 2022.01.31
 */

/*
    字符串相乘
    https://leetcode-cn.com/problems/multiply-strings/

    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。

    示例 1:
        输入: num1 = "2", num2 = "3"
        输出: "6"
    示例 2:
        输入: num1 = "123", num2 = "456"
        输出: "56088"

    提示：
        1 <= num1.length, num2.length <= 200
        num1 和 num2 只能由数字组成。
        num1 和 num2 都不包含任何前导零，除了数字 0 本身。

 */
public class MultiplyStrings {

    /*
        竖式乘法计算。
        考虑两个数字 nums[n], nums[m] 相乘，结果的长度为 m + n 或 m + n - 1
        因此结果长度最长为 m + n
        因为，res = nums[i] * nums[j] 所得结果应在最终结果的 ans[i + j + 1] 的位置
        如果结果 res > 10, 进位应位于 ans[i + j] 的位置。
        最终将 ans 数组转换成字符串即可
        结果：
            2 ms, 92.87%
            40.1 MB, 22.23%
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        int[] ans = new int[m + n];

        // 计算成绩
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                // 将结果加入即可
                ans[i + j + 1] += x * y;
            }
        }

        // 从后往前计算进位
        for (int i = m + n - 1; i > 0; i--) {
            ans[i - 1] += ans[i] / 10;
            ans[i] %= 10;
        }
        // 判断第一位有没有进位
        int index = ans[0] == 0 ? 1 : 0;

        // 拼接结果
        StringBuilder res = new StringBuilder();
        while (index < m + n) {
            res.append(ans[index]);
            index++;
        }
        return res.toString();
    }
}
