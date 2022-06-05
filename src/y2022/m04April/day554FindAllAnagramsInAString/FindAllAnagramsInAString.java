package y2022.m04April.day554FindAllAnagramsInAString;


import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.04.14
 */

/*
    找到字符串中所有字母异位词
    https://leetcode.cn/problems/find-all-anagrams-in-a-string/

    给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
    异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。

    示例1:
        输入: s = "cbaebabacd", p = "abc"
        输出: [0,6]
    解释:
        起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
        起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
    示例 2:
        输入: s = "abab", p = "ab"
        输出: [0,1,2]
    解释:
        起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
        起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
        起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。

    提示:
        1 <= s.length, p.length <= 3 * 104
        s 和 p 仅包含小写字母

 */
public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        System.out.println(new FindAllAnagramsInAString().findAnagrams3("cbaebabacd", "abc"));
    }

    /*
        方法三：优化的窗口
            初始化 p 的字符数组然后维护数组每个元素不小于 0。 开始向右滑动窗口，减去并相应字符，
            如果频率小于 0 就收缩左侧边界直到频率不再小于 0。窗口长度与 p 长度一致时达成条件
        结果：
            4 ms, 98.33%
            42.3 MB, 62.28%
     */
    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        char[] sCharArray = s.toCharArray();
        char[] pCharArray = p.toCharArray();
        // 统计需要的字符及其数量
        int[] need = new int[26];
        for (char c : pCharArray) {
            need[c - 'a']++;
        }

        // 移动窗口
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            // 右侧字符向右移动，新字符 -1
            char rightChar = sCharArray[right];
            --need[rightChar - 'a'];
            // 当前字符的个数为负数时，左针一直向右移动，直到当前字符的个数为非负数
            while (need[rightChar - 'a'] < 0) {
                char leftChar = sCharArray[left];
                ++need[leftChar - 'a'];
                ++left;
            }
            // 如果窗口长度 == s.length()，那么就说明复核结果
            if (right - left + 1 == p.length()) {
                result.add(left);
            }
            right++;  // 移动右边界
        }
        return result;
    }

    /*
        方法二：优化的滑动窗口，使用数组，思路和方法一一样
        结果：
            7 ms, 86.33%
            42.5 MB, 35.56%
     */
    public List<Integer> findAnagrams2(String s, String p) {
        int pLength = p.length();
        int sLength = s.length();

        // 存在 p 长于 s 的测试用例
        if (pLength > sLength) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        // 存放两个词频数组
        int[] sChar = new int[26];
        int[] pChar = new int[26];

        for (int i = 0; i < pLength; ++i) {
            ++sChar[s.charAt(i) - 'a'];
            ++pChar[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(sChar, pChar)) {
            result.add(0);
        }

        for (int i = 0; i < sLength - pLength; ++i) {
            // 窗口左侧 -1
            --sChar[s.charAt(i) - 'a'];
            // 窗口右侧 +1
            ++sChar[s.charAt(i + pLength) - 'a'];
            // 判断相等
            if (Arrays.equals(sChar, pChar)) {
                result.add(i + 1);
            }
        }
        return result;
    }

    /*
        方法一：滑动窗口暴力匹配
        每次往后移动一个单位，判断当前窗口的值是否满足条件，满足就加入集合，不满足就往后移动
        结果：
            42 ms, 19.43%
            41.8 MB, 99.12%
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 存在 p 长于 s 的测试用例
        if (p.length() > s.length()) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        char[] pChars = p.toCharArray();

        // 统计目标字符串的字符个数
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char pChar : pChars) {
            Integer times = targetMap.getOrDefault(pChar, 0) + 1;
            targetMap.put(pChar, times);
        }
        // 标记于目标串的距离，记录窗口中是否包含所有字串的字符
        int left = 0;
        int right = p.length();
        // 记录当前 map 的值
        Map<Character, Integer> currentMap = new HashMap<>();
        for (int i = 0; i < right; i++) {
            Integer times = currentMap.getOrDefault(chars[i], 0) + 1;
            currentMap.put(chars[i], times);
        }

        while (right < chars.length) {
            // 如果当前 mpa 匹配 目标 map，加入结果
            if (isAnagrams(currentMap, targetMap)) {
                result.add(left);
            }
            // 将窗口最左侧的字符移除
            Integer integer = currentMap.get(chars[left]);
            if (integer == 1) {
                currentMap.remove(chars[left]);
            } else {
                currentMap.put(chars[left], integer - 1);
            }
            // 将窗口下一个字符加入
            int i = currentMap.getOrDefault(chars[right], 0) + 1;
            currentMap.put(chars[right], i);
            right++;
            left++;
        }
        // 判断最后一个
        if (isAnagrams(currentMap, targetMap)) {
            result.add(left);
        }
        return result;
    }

    /*
        判断当前 map 是否匹配目标 map
     */
    public boolean isAnagrams(Map<Character, Integer> currentMap, Map<Character, Integer> targetMap) {
        // map 长度不一致，直接返回
        if (currentMap.size() != targetMap.size()) {
            return false;
        }

        // 遍历当前 map，匹配目标 map
        for (Map.Entry<Character, Integer> entry : currentMap.entrySet()) {
            Integer curr = targetMap.get(entry.getKey());
            // 目标 map 不包含当前字符，不匹配直接返回 false
            if (curr == null) {
                return false;
            }
            // 当前 map 和目标 map 值不一样，直接返回 false
            if (!curr.equals(entry.getValue())) {
                return false;
            }
        }
        // 匹配，返回 true
        return true;
    }
}
