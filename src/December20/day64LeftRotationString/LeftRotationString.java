package December20.day64LeftRotationString;

/**
 * @author Joush
 * @time 2020.12.10
 */

/*
    左旋转字符串（剑指 Offer 58 - II）
    https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/

    字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
    比如，输入字符串"abcdefg"和数字 2，该函数将返回左旋转两位得到的结果"cdefgab"。

    示例 1：
        输入: s = "abcdefg", k = 2
        输出: "cdefgab"
    示例 2：
        输入: s = "lrloseumgh", k = 6
        输出: "umghlrlose"

    限制：
        1 <= k < s.length <= 10000

 */
public class LeftRotationString {

    /*
        将字符串前子序列和后子序列进行反转，最后将总字符串进行反转即可
        结果
            3 ms, 21.17%
            38.3 MB, 72.27%

     */
    public String reverseLeftWords(String s, int n) {
        return reverseString(reverseString(s.substring(0,n)) + reverseString(s.substring(n)));
    }

    /*
        直接截取子字符串进行拼接
        结果
            0 ms, 100.00%
            38.4 MB, 60.34%
     */
    public String reverseLeftWords2(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    /*
        字符串反转函数，将字符串前后对调
     */
    public String reverseString(String s){
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;

        while (i < j){
            char ch = chars[i];
            chars[i++] = chars[j];
            chars[j--] = ch;
        }

        return new String(chars);
    }

}
