package November20.day40ValidPalindrome;

/**
 * @author Joush
 * @time 2020.11.16
 */

/*
    验证回文串
    https://leetcode-cn.com/problems/valid-palindrome/

    给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    说明：本题中，我们将空字符串定义为有效的回文串。

    示例 1:
        输入: "A man, a plan, a canal: Panama"
        输出: true
    示例 2:
        输入: "race a car"
        输出: false

 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s.length() == 0){
            return true;
        }
        // 忽略大小写
        char[] chars = s.toLowerCase().toCharArray();

        int l = 0;
        int r = chars.length - 1;

        // 双指针判断
        while (l < r){

            /*
                此处可以使用 Character 类的方法
                boolean isLetterOrDigit(); // 是否是字母或数字
             */

            // 字符在数字或字母范围内，同时保证 l<r, 避免整个字符串无字母的情况下，出现越界异常
            while (l < r && !Character.isLetterOrDigit(chars[l])){
                l++;
            }

//            while (!((chars[l] >= 'a' && chars[l] <= 'z') || (chars[l] >= '0' && chars[l] <= '9')) && l < r){
//                l++;
//            }
            // 字符在数字或字母范围内
            while (l < r && !Character.isLetterOrDigit(chars[r])){
                r--;
            }
//            while (!((chars[r] >= 'a' && chars[r] <= 'z') || (chars[r] >= '0' && chars[r] <= '9')) && l < r){
//                r--;
//            }

            // 如果不一样，就返回 false
            if (chars[l++] != chars[r--]){
                return false;
            }

        }
        // 返回 true
        return true;
    }

}
