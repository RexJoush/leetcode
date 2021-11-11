package y2021.m11November.day398ConvertToHexadecimal;

/**
 * @author Rex Joush
 * @time 2021.11.09
 */

/*
    数字转换为十六进制数
    https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/

    给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
    注意:
        十六进制中所有字母(a-f)都必须是小写。
        十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
        给定的数确保在32位有符号整数范围内。
        不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
        示例 1：
            输入: 26
            输出: "1a"
        示例 2：
            输入: -1
            输出: "ffffffff"

 */
public class ConvertToHexadecimal {

    /*
        四位一组，转化成 16 进制即可
        结果：
            0 ms, 100.00%
            35.6 MB, 78.34%
     */
    public String toHex(int num) {

        if (num == 0) return "0";

        // 结果字符串，比 String 类效率高
        StringBuilder result = new StringBuilder();

        int[] numbers = new int[8];
        for (int i = 0; i < 8; i++) {
            // 计算每四位数字的 16 进制值
            numbers[i] = num >> (4 * (7 - i)) & 0x0000000f;
        }

        for (int number : numbers) {
            // 如果非首位的 0 或者 大于 0
            if (result.length() > 0 || number > 0) {
                // 拼接字符
                char digit = number < 10 ? (char) ('0' + number) : (char) ('a' + number - 10);
                result.append(digit);
            }
        }

        return result.toString();
    }

}
