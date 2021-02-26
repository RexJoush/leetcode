package y2021.m02February.day120ExcelSheetColumnTitle;

/**
 * @author Joush
 * @time 2021.02.04
 */

/*
    Excel表列名称
    https://leetcode-cn.com/problems/excel-sheet-column-title/

    给定一个正整数，返回它在 Excel 表中相对应的列名称。
    例如，
        1 -> A
        2 -> B
        3 -> C
        ...
        26 -> Z
        27 -> AA
        28 -> AB
        ...
    示例 1:
        输入: 1
        输出: "A"
    示例 2:
        输入: 28
        输出: "AB"
    示例 3:
        输入: 701
        输出: "ZY"

 */
public class ExcelSheetColumnTitle {

    /*
        Excel 中的相当于26进制，将 10 进制转换为 26 进制即可
        结果：
            0 ms, 100.00%
            35.7 MB, 35.08%
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            // 取余，拼接，相当于除 k 取余法进制转换
            int c = n % 26;
            // 将 26 的特殊情况除去，正好除尽时，应拼接 Z
            if (c == 0) {
                c = 26;
                n -= 1;
            }
            sb.insert(0, (char) ('A' + c - 1));
            n /= 26;
        }
        return sb.toString();
    }
}
