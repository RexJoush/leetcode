package y2020.m12December.day69JewelsAndStones;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joush
 * @time 2020.12.15
 */

/*
    宝石与石头
    https://leetcode-cn.com/problems/jewels-and-stones/

    给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
    J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。

    示例 1:
        输入: J = "aA", S = "aAAbbbb"
        输出: 3
    示例 2:
        输入: J = "z", S = "ZZ"
        输出: 0

    注意:
        S 和 J 最多含有50个字母。
        J 中的字符不重复。

 */
public class JewelsAndStones {

    /*
        遍历法，将 J 中的所有字符存入 hash 表中，遍历 S，计数即可
        结果：
            2 ms, 48.93%
            36.8 MB, 88.31%
     */
    public int numJewelsInStones(String J, String S) {
        int result = 0;

        Set<Character> set = new HashSet<>();
        char[] chars = J.toCharArray();

        for (char ch : chars) {
            set.add(ch);
        }

        for (char c : S.toCharArray()){
            if (set.contains(c)){
                result++;
            }
        }
        return result;
    }

    /*
        正则表达式，将非 J 的字符匹配出来查找长度即可
        结果:
            9 ms, 5.52%
            38.5 MB, 5.01%
     */
    public int numJewelsInStones2(String J, String S) {
        return S.replaceAll("[^ " + J +"]","").length();
    }
}
