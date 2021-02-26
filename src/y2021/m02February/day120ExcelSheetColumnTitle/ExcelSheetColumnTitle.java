package y2021.m02February.day120ExcelSheetColumnTitle;

/**
 * @author Joush
 * @time 2021.02.21
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

    public static void main(String[] args) {

    }


    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n / 26 != 0){
            // char c = char ((n % 26) - int('A'));
////            char ( - 'A');
        }
    }
}
