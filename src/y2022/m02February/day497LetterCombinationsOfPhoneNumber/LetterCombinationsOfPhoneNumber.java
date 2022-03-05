package y2022.m02February.day497LetterCombinationsOfPhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2022.02.16
 */

/*
    电话号码的字母组合
    https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
    
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

        1 ->        2 -> abc    3 -> def
        4 -> ghi    5 -> jkl    6 -> mno
        7 -> pqrs   8 -> tuv    9 -> wxyz

    示例 1：
        输入：digits = "23"
        输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
    示例 2：
        输入：digits = ""
        输出：[]
    示例 3：
        输入：digits = "2"
        输出：["a","b","c"]


    提示：
        0 <= digits.length <= 4
        digits[i] 是范围 ['2', '9'] 的一个数字。

 */
public class LetterCombinationsOfPhoneNumber {

    /*
        回溯法，遍历所有情况即可
        结果：
            1 ms, 56.46%
            39.7 MB, 37.67%
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }

        // 建立映射关系
        Map<Character, String> map =
            Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz");

        int index = 0;
        StringBuilder combination = new StringBuilder();
        backtrack(result, map, digits, index, combination);

        return result;
    }

    private void backtrack(List<String> result, Map<Character, String> map, String digits, int index, StringBuilder combination) {
        // 找到所有字符了，拼接成字符串，加入结果集合
        if (index == digits.length()) {
            result.add(combination.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = map.get(digit);
        for (int i = 0; i < letters.length(); i++) {
            // 拼接当前字符串的第一个
            combination.append(letters.charAt(i));
            // 回溯
            backtrack(result, map, digits, index + 1, combination);
            // 撤销选择
            combination.deleteCharAt(index);
        }
    }
}
