package y2021.m03March.day168VerifyingAnAlienDictionary;

import java.util.Comparator;

/**
 * @author Rex Joush
 * @time 2021.03.24
 */

/*
    验证外星语词典
    https://leetcode-cn.com/problems/verifying-an-alien-dictionary/

    某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
    给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。

    示例 1：
        输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
        输出：true
        解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
    示例 2：
        输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
        输出：false
        解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
    示例 3：
        输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
        输出：false
        解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。

    提示：
        1 <= words.length <= 100
        1 <= words[i].length <= 20
        order.length == 26
        在 words[i] 和 order 中的所有字符都是英文小写字母。

 */
public class VerifyingAnAlienDictionary {

    /*
        自定义排序方法，顺序比较判断
        结果：
            0 ms, 100.00%
            38.5 MB, 15.29%
     */
    public boolean isAlienSorted(String[] words, String order) {
        this.dictionary = order;
        for (int i = 0; i < words.length - 1; i++) {
            // 如果不满足排序，则返回 false
            if (myCompare(words[i], words[i + 1]) > 0) {
                return false;
            }
        }
        // 均满足，则返回 true
        return true;
    }

    private String dictionary;

    // 自定义排序方法
    private int myCompare(String o1, String o2) {

        char[] array1 = o1.toCharArray();
        char[] array2 = o2.toCharArray();

        int i = 0;
        // 顺序比较两个字符串的大小
        while (i < array1.length && i < array2.length) {
            // 如果字符相同，则跳过当前字符，比较下一个
            if (array1[i] == array2[i]) {
                i++;
                continue;
            }
            // res 为两个字符在字典中的次序之差
            return dictionary.indexOf(array1[i]) - dictionary.indexOf(array2[i]);
        }
        // 此时，表示某个字符串比较完毕，如果 o1 先完毕，则表示 o2 大，反之，o1 大
        if (i == array1.length && i == array2.length) {
            return 0;
        } else if (i == array1.length) {
            return -1;
        } else {
            return 1;
        }
    }
}
