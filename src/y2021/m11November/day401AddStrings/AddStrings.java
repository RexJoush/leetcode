package y2021.m11November.day401AddStrings;

/**
 * @author Rex Joush
 * @time 2021.11.12
 */

/*
    字符串相加
    https://leetcode-cn.com/problems/add-strings/

    给定两个字符串形式的非负整数num1 和num2，计算它们的和并同样以字符串形式返回。

    你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。

    示例 1：
        输入：num1 = "11", num2 = "123"
        输出："134"
    示例 2：
        输入：num1 = "456", num2 = "77"
        输出："533"
    示例 3：
        输入：num1 = "0", num2 = "0"
        输出："0"

    提示：
        1 <= num1.length, num2.length <= 10^4
        num1 和num2 都只包含数字0-9
        num1 和num2 都不包含任何前导零
 */
public class AddStrings {

    /*
        模拟正常的计算加法的过程即可
        结果：
            2 ms, 94.61%
            38.4 MB, 62.20%
     */
    public String addStrings(String num1, String num2) {

        StringBuilder stringBuilder = new StringBuilder();

        // 将长的字符串放在 num1 上
        if (num1.length() < num2.length()) {
            addStrings(num2, num1);
            return "";
        }

        char[] num1Chars = num1.toCharArray();
        char[] num2Chars = num2.toCharArray();
        int i = num1Chars.length - 1;
        int j = num2Chars.length - 1;
        int carry = 0;  // 进位值

        // 遍历两个串
        while (j >= 0) {
            // 本位两位和 + 进位
            int sum = (num1Chars[i] - '0') + (num2Chars[j] - '0') + carry;
            // int basic = sum;  // 本位值
            // 产生进位
            if (sum >= 10) {
                // 进位置为 1
                carry = 1;
                stringBuilder.append(sum % 10);
            } else {
                carry = 0;
                stringBuilder.append(sum);
            }
            j--;
            i--;
        }
        // 遍历长串的剩余部分
        while (i >= 0) {

            int sum = (num1Chars[i] - '0') + carry;
            if (sum >= 10) {
                // 进位置为 1
                carry = 1;
                stringBuilder.append(sum % 10);
            } else {
                carry = 0;
                stringBuilder.append(sum);
            }
            i--;
        }
        // 补上最后的进位
        if (carry != 0) {
            stringBuilder.append(1);
        }
        return stringBuilder.reverse().toString();

    }

}
