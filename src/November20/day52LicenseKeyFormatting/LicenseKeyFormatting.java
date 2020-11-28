package November20.day52LicenseKeyFormatting;

/**
 * @author Joush
 * @time 2020.11.28
 */

/*
    密钥格式化
    https://leetcode-cn.com/problems/license-key-formatting/

    有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。其中， N 个 '-' 将字符串分成了 N+1 组。
    给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。特别地，第一个分组包含的字符个数必须小于等于 K，
    但至少要包含 1 个字符。两个分组之间需要用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。
    给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。

    示例 1：
        输入：S = "5F3Z-2e-9-w", K = 4
        输出："5F3Z-2E9W"
        解释：字符串 S 被分成了两个部分，每部分 4 个字符；
              注意，两个额外的破折号需要删掉。
    示例 2：
        输入：S = "2-5g-3-J", K = 2
        输出："2-5G-3J"
        解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。

    提示:
        S 的长度可能很长，请按需分配大小。K 为正整数。
        S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-'
        S 非空

 */
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {

        // 建立结果字符串
        StringBuilder result = new StringBuilder();
        // 将所有的 - 删掉
        String s = S.replaceAll("-","");

        int index = 0;
        char[] chars = s.toUpperCase().toCharArray();
        // 算出第一个子段的长度
        int firstLength = s.length() % K;
        // 拼接第一个子段
        while (index < firstLength){
            result.append(chars[index++]);
        }
        // 如果第一个字段有字符，就加上一个短杠
        if (result.length() != 0){
            result.append('-');
        }
        // 每数 K 个字符就加一个短杠
        while (index < chars.length){
            for (int i = 0; i < K; i++){
                result.append(chars[index++]);
            }
            result.append('-');
        }
        // 防止出现没有字符的测试字符串
        if (result.length() == 0){
            return "";
        }
        // 返回前 n-1 个字符，除去最后那个短杠
        return result.substring(0,result.length() - 1);
    }
}
