package y2022.m01January.day462ReverseOnlyLetters;

/**
 * @author Rex Joush
 * @time 2022.01.12
 */

/*
    仅仅反转字母
    https://leetcode-cn.com/problems/reverse-only-letters/

    给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。

    示例 1：
        输入："ab-cd"
        输出："dc-ba"
    示例 2：
        输入："a-bC-dEf-ghIj"
        输出："j-Ih-gfE-dCba"
    示例 3：
        输入："Test1ng-Leet=code-Q!"
        输出："Qedo1ct-eeLg=ntse-T!"

    提示：
        S.length <= 100
        33 <= S[i].ASCIIcode <= 122
        S 中不包含 \ or "

 */
public class ReverseOnlyLetters {

    /*
        双指针法，前后指针，如果是字母就交换，否则挪动指针即可
        结果：
            0 ms, 100.00%
            36.6 MB, 48.51%
     */
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();

        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // 如果两边均为字符，则交换
            if (Character.isLetter(chars[left]) && Character.isLetter(chars[right])) {
                char c = chars[right];
                chars[right] = chars[left];
                chars[left] = c;
                left++;
                right--;
            }
            // 左边是字符，右边向前挪动
            else if (Character.isLetter(chars[left])) {
                right--;
            }
            // 右边是字符，左边向右移动
            else if (Character.isLetter(chars[right])) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        return new String(chars);
    }
}
