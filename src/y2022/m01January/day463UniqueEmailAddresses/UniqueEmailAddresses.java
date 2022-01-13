package y2022.m01January.day463UniqueEmailAddresses;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2022.01.13
 */

/*
    独特的电子邮件地址
    https://leetcode-cn.com/problems/unique-email-addresses/

    每个 有效电子邮件地址 都由一个 本地名 和一个 域名 组成，以 '@' 符号分隔。除小写字母之外，电子邮件地址还可以含有一个或多个 '.' 或 '+' 。
    例如，在 alice@leetcode.com中， alice 是 本地名 ，而 leetcode.com 是 域名 。
    如果在电子邮件地址的 本地名 部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则 不适用于域名 。
    例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。
    如果在 本地名 中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。同样，此规则 不适用于域名 。
    例如 m.y+name@email.com 将转发到 my@email.com。
    可以同时使用这两个规则。
    给你一个字符串数组 emails，我们会向每个 emails[i] 发送一封电子邮件。返回实际收到邮件的不同地址数目。

    示例 1：
        输入：emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
        输出：2
        解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
    示例 2：
        输入：emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
        输出：3

    提示：
        1 <= emails.length <= 100
        1 <= emails[i].length <= 100
        emails[i] 由小写英文字母、'+'、'.' 和 '@' 组成
        每个 emails[i] 都包含有且仅有一个 '@' 字符
        所有本地名和域名都不为空
        本地名不会以 '+' 字符作为开头

 */
public class UniqueEmailAddresses {

    /*
        方法一：正则表达式
            获取正确的邮箱地址，加入哈希表即可，返回哈希表的长度
            结果：
                43 ms, 5.71%
                40.2 MB, 7.42%
     */
    public int numUniqueEmails(String[] emails) {

        Set<String> set = new HashSet<>();

        for (String email : emails) {
            String username = email.split("@")[0];
            String hostname = email.split("@")[1];
            // 将点去掉，并获取到 + 号之前的
            String realName = username.replaceAll("\\.", "").split("[+]")[0];

            set.add(realName.concat("@").concat(hostname));
        }

        return set.size();
    }

    /*
        方法二：字符串拼接
            获取正确的邮箱地址，加入哈希表即可，返回哈希表的长度
            结果：
                8 ms, 88.76%
                38.8 MB, 60.38%
     */
    public int numUniqueEmails2(String[] emails) {

        Set<String> set = new HashSet<>();

        for (String email : emails) {
            // 获取到 @ 的索引
            int index = email.indexOf('@');
            // 获取 @ + 域名字符串
            String hostname = email.substring(index);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < index; i++) {
                char ch = email.charAt(i);
                // 遇到 + 号则直接忽略后面所有
                if (ch == '+') {
                    break;
                } else if (ch == '.') {
                    // 遇到 . 则忽略 .
                    continue;
                } else {
                    // 拼接当前字符
                    stringBuilder.append(ch);
                }
            }
            // 拼接域名
            stringBuilder.append(hostname);
            // 加入哈希表
            set.add(stringBuilder.toString());
        }
        return set.size();
    }
}
