package y2021.m03March.day150PositionsOfLargeGroups;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.03.06
 */

/*
    较大分组的位置
    https://leetcode-cn.com/problems/positions-of-large-groups/

    在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
    例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
    分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。
    上例中的 "xxxx" 分组用区间表示为 [3,6] 。
    我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
    找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
    
    示例 1：
        输入：s = "abbxxxxzzy"
        输出：[[3,6]]
        解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
    示例 2：
        输入：s = "abc"
        输出：[]
        解释："a","b" 和 "c" 均不是符合要求的较大分组。
    示例 3：
        输入：s = "abcdddeeeeaabbbcd"
        输出：[[3,5],[6,9],[12,14]]
        解释：较大分组为 "ddd", "eeee" 和 "bbb"
    示例 4：
        输入：s = "aba"
        输出：[]
     
    提示：
        1 <= s.length <= 1000
        s 仅含小写英文字母

 */
public class PositionsOfLargeGroups {

    /*
        遍历判断即可
        结果：
            1 ms, 100.00%
            38.7 MB, 29.20%
     */
    public List<List<Integer>> largeGroupPositions(String s) {

        List<List<Integer>> result = new ArrayList<>();

        char[] chars = s.toCharArray();
        char base = chars[0];
        int count = 1;
        int start = 1;
        for (int i = 1; i < chars.length; i++) {
            // 当前字符和前面字符相同，则 count++;
            if (chars[i] == base) {
                count++;
            } else {
                // 不一样时，start 置为新单词的位置
                if (count >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(i);
                    result.add(list);
                }
                // 开始置为新开始
                start = i + 1;
                // count 置为 1
                count = 1;
                // 更新 base
                base = chars[i];
            }
        }
        // 处理最后一个
        if (count >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(chars.length);
            result.add(list);
        }

        return result;
    }
}
