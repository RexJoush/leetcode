package simple.day07LongestCommonPrefix;

/**
 * @author Joush
 * @time 2020.10.14
 */
/*

    最长公共前缀
    https://leetcode-cn.com/problems/longest-common-prefix/
   
    编写一个函数来查找字符串数组中的最长公共前缀。
    如果不存在公共前缀，返回空字符串 ""。
    示例 1:
        输入: ["flower","flow","flight"]
        输出: "fl"
    示例 2:
        输入: ["dog","racecar","car"]
        输出: ""
        解释: 输入不存在公共前缀。
    说明:
        所有输入只包含小写字母 a-z 。

 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
//        String a = "abcd";
//        System.out.println(a.charAt(5));
        String[] strs =  new String[]{"ab","a"};
        longestCommonPrefix(strs);
    }

    public static String longestCommonPrefix(String[] strs) {
        // 如果空数组，返回空
        if (strs.length == 0){
            return "";
        }
        // 如果为1，返回第一个串
        if (strs.length == 1){
            return strs[0];
        }

        String result = "";
        int j = 0;
        while (true){
            //
            for (int i = 0; i < strs.length - 1; i++){

                // 如果当前进行到某个最短的字符串的结尾，那么直接返回
                if (j > (strs[i].length() - 1) || j > (strs[i+1].length() - 1)){
                    return result;
                }
                // 如果当前字符串为空，直接返回
                if (strs[i].length() == 0 ) return result;

                // 获取当前字符串的j号字母
                char ch = strs[i].charAt(j);
                // 获取下一个字符串的j号字母
                char ch2 = strs[i + 1].charAt(j);
                if (ch != ch2){
                    // 当不等时，返回当前的字符串
                    return result;
                }
            }
            // 第 j 列字母相等，添加到结果字符串，j++
            result += strs[0].charAt(j);
            j++;
        }

    }

}
