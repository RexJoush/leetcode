package y2021.m02February.day121ExcelSheetColumnNumber;

/**
 * @author Joush
 * @time 2021.02.05
 */

/*
    Excel表列序号
    https://leetcode-cn.com/problems/excel-sheet-column-number/

    给定一个Excel表格中的列名称，返回其相应的列序号。
    例如，
        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
        ...

    示例 1:
        输入: "A"
        输出: 1
    示例 2:
        输入: "AB"
        输出: 28
    示例 3:
        输入: "ZY"
        输出: 701

 */
public class ExcelSheetColumnNumber {

    /*
        与上一题类似，将26进制转换为10进制即可
        结果：
            1 ms, 100.00%
            38.4 MB, 58.74%
     */
    public int titleToNumber(String s) {
        int power = s.length();

        int result = 0;

        for (int i = 0; i < power; i++) {
            // 获取当前字符代表的数字
            int index = s.charAt(i) - 'A' + 1;
            // 按权加和即可
            result = result * 26 + index;
        }
        return result;
    }
}
