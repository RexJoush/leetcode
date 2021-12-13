package y2021.m12December.day421StudentAttendanceRecordI;

/**
 * @author Rex Joush
 * @time 2021.12.02
 */

/*
    学生出勤记录 I
    https://leetcode-cn.com/problems/student-attendance-record-i/

    给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。
    记录中只含下面三种字符：
        'A'：Absent，缺勤
        'L'：Late，迟到
        'P'：Present，到场
    如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
        按 总出勤 计，学生缺勤（'A'）严格 少于两天。
        学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
        如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。

    示例 1：
        输入：s = "PPALLP"
        输出：true
        解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
    示例 2：
        输入：s = "PPALLL"
        输出：false
        解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。

    提示：
        1 <= s.length <= 1000
        s[i] 为 'A'、'L' 或 'P'

 */
public class StudentAttendanceRecordI {

    /*
        根据定义遍历判断即可
        结果：
            0 ms, 100.00%
            36.3 MB, 82.97%
     */
    public boolean checkRecord(String s) {
        int late = 0;
        int absent = 0;

        char[] chars = s.toCharArray();

        for (char aChar : chars) {
            // 缺勤，缺勤加 1
            if (aChar == 'A') {
                absent++;
                // 迟到清零
                late = 0;
            }
            // 迟到，迟到加 1
            else if (aChar == 'L') {
                late++;
            }
            // 正常出勤，迟到清理
            else {
                late = 0;
            }

            // 连续三天迟到，返回 false
            if (late >= 3) {
                return false;
            }
            // 缺勤超过两天，返回 false
            if (absent >= 2) {
                return false;
            }
        }
        return true;
    }

}
