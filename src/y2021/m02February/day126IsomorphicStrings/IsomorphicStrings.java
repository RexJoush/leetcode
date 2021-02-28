package y2021.m02February.day126IsomorphicStrings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joush
 * @time 2021.02.10
 */

/*
    同构字符串
    https://leetcode-cn.com/problems/isomorphic-strings/

    给定两个字符串 s 和 t，判断它们是否是同构的。
    如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
    每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
    不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。

    示例 1:
        输入：s = "egg", t = "add"
        输出：true
    示例 2：
        输入：s = "foo", t = "bar"
        输出：false
    示例 3：
        输入：s = "paper", t = "title"
        输出：true

    提示：
        可以假设 s 和 t 长度相同。

 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        String s = "egadc";
        String t = "adcda";
        System.out.println(new IsomorphicStrings().isIsomorphic(s, t));
    }
    /*
        一对一建立映射关系即可，注意要建立双向映射
        结果：
            22 ms, 36.16%
            38.4 MB, 68.68%
     */
    public boolean isIsomorphic(String s, String t) {

        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            char value = t.charAt(i);

            // 如果没有映射，建立双向映射
            if (!map1.containsKey(key) && !map2.containsKey(value)) {
                map1.put(key, value);
                map2.put(value, key);
            }
            // 如果有映射，检查是否匹配
            else {
                try {
                    if (!(map1.get(key) == value && map2.get(value) == key)) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }
}
