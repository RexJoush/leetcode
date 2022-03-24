package y2022.m02February.day508GroupAnagrams;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2022.02.27
 */

/*
    字母异位词分组
    https://leetcode-cn.com/problems/group-anagrams/

    给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

    字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

    示例 1:
        输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
    示例 2:
        输入: strs = [""]
        输出: [[""]]
    示例 3:
        输入: strs = ["a"]
        输出: [["a"]]

    提示：
        1 <= strs.length <= 10^4
        0 <= strs[i].length <= 100
        strs[i] 仅包含小写字母

 */
public class GroupAnagrams {

    public static void main(String[] args) {
        System.out.println(new GroupAnagrams().getWordHash("adkhajksdhkjasd"));
    }

    /*
        方法一：单词哈希
        将每个单词映射为一个哈希值，保证包含同样字母的单词哈希值相同

     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        if (strs.length == 0) {
            return result;
        }
        // 遍历每个单词
        for (String str : strs) {
            // 计算当前单词的哈希值
            String hash = getWordHash(str);
            List<String> list = null;
            // 如果包含当前的哈希值，获得当前哈希值对应的集合
            if (map.containsKey(hash)) {
                list = map.get(hash);
            } else {
                // 不包含当前哈希值，则新建 list
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(hash, list);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<BigInteger, List<String>> map = new HashMap<>();
        if (strs.length == 0) {
            return result;
        }
        // 遍历每个单词
        for (String str : strs) {
            // 计算当前单词的哈希值
            BigInteger hash = getWordHash2(str);
            List<String> list = null;
            // 如果包含当前的哈希值，获得当前哈希值对应的集合
            if (map.containsKey(hash)) {
                list = map.get(hash);
            } else {
                // 不包含当前哈希值，则新建 list
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(hash, list);
        }
        // 遍历 map 加入结果即可
        for (Map.Entry<BigInteger, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }
    /*
        计算单词的哈希值，考虑尽可能的减少哈希碰撞
        即质数哈希
        a = 2;
        b = 3;
        c = 5;
        d = 7;
        e = 11;
        f = 13;

     */
    // 数哈希
    private final int[] hashMap = new int[] {
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 81, 89, 97, 101
    };

    private final BigInteger[] hashBigMap = new BigInteger[] {
            new BigInteger("2"),
            new BigInteger("3"),
            new BigInteger("5"),
            new BigInteger("7"),
            new BigInteger("11"),
            new BigInteger("13"),
            new BigInteger("17"),
            new BigInteger("19"),
            new BigInteger("23"),
            new BigInteger("29"),
            new BigInteger("31"),
            new BigInteger("37"),
            new BigInteger("41"),
            new BigInteger("43"),
            new BigInteger("47"),
            new BigInteger("53"),
            new BigInteger("59"),
            new BigInteger("61"),
            new BigInteger("67"),
            new BigInteger("71"),
            new BigInteger("73"),
            new BigInteger("79"),
            new BigInteger("83"),
            new BigInteger("89"),
            new BigInteger("97"),
            new BigInteger("101")
    };

    // 计算哈希值
    private String getWordHash(String str) {

        StringBuilder hash = new StringBuilder();
        int[] map = new int[26];

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) {
                hash.append((char)(i + 'a')).append(map[i]);
            }
        }

        return hash.toString();
    }

    // 大数计算哈希值
    private BigInteger getWordHash2(String str) {

        int[] map = new int[26];
        BigInteger hash = new BigInteger("1");

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            hash = hash.multiply(hashBigMap[ch-'a']);
        }

        return hash;
    }
}
