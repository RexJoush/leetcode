package y2021.m03March.day172FindCommonCharacters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.03.28
 */

/*
    查找共用字符
    https://leetcode-cn.com/problems/find-common-characters/

    给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。

    示例 1：
        输入：words = ["bella","label","roller"]
        输出：["e","l","l"]
    示例 2：
        输入：words = ["cool","lock","cook"]
        输出：["c","o"]
    
    提示：
        1 <= words.length <= 100
        1 <= words[i].length <= 100
        words[i] 由小写英文字母组成

 */
public class FindCommonCharacters {

    /*
        统计字符，如果字符 c 在所有字符串中出现了 k 次，那么结果则包含 k 个 c
        统计所有字符串中出现的字符的最小个数即可
        结果：
            3 ms, 80.64%
            38.8 MB, 90.40%
     */
    public List<String> commonChars(String[] words) {

        int[] minFreq = new int[26];

        Arrays.fill(minFreq, Integer.MAX_VALUE);
        for (String word : words) {
            int[] freq = new int[26];
            // 统计每个单词中字符出现的
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                freq[ch - 'a']++;
            }
            // 更新最小值
            for (int i = 0; i < 26; i++) {
                minFreq[i] = Math.min(minFreq[i], freq[i]);
            }
        }

        // 记录结果
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minFreq[i]; j++) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;

    }

}
