package y2021.m01January.day106AddBinary;

/**
 * @author Joush
 * @time 2021.01.21
 */

/*
    二进制求和
    https://leetcode-cn.com/problems/add-binary/

    给你两个二进制字符串，返回它们的和（用二进制表示）。
    输入为 非空 字符串且只包含数字 1 和 0。

    示例 1:
        输入: a = "11", b = "1"
        输出: "100"
    示例 2:
        输入: a = "1010", b = "1011"
        输出: "10101"

    提示：
        每个字符串仅由字符 '0' 或 '1' 组成。
        1 <= a.length, b.length <= 10^4
        字符串如果不是 "0" ，就都不含前导零。

 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        new AddBinary().addBinary2(a, b);
    }

    /*
        将两个字符串转换成十进制数，求和后再转成二进制字符串
        会超时，当 a,b 足够长时会出现异常
     */
    public String addBinary(String a, String b) {

        // Integer.parseInt(value, radix) 第一个参数为值，第二个参数为进制
        return Integer.toBinaryString(
            Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    /*
        位运算，不断取出最后一位进行相加计算
        结果：
            3 ms, 53.54%
            38.2 MB, 55,31%
     */
    public String addBinary2(String a, String b) {
        // 结果字符串
        StringBuffer result = new StringBuffer();

        int n = Math.max(a.length(), b.length());
        // 标志进位
        int carry = 0;

        for (int i = 0; i < n; i++) {
            /*
                竖式计算
                每位的答案为 carry + a[i] + b[i]
                下一位的进位为 (carry + a[i] + b[i]) / 2
                如果 最后最高位不是 0，则补上一个 1
             */
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            result.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        // 补上进位的1
        if (carry > 0) {
            result.append('1');
        }
        // 取反
        result.reverse();
        // 返回结果
        return result.toString();
    }

}
