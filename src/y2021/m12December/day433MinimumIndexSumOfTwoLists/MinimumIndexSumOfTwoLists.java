package y2021.m12December.day433MinimumIndexSumOfTwoLists;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2021.12.14
 */

/*
    两个列表的最小索引总和
    https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/

    假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，
    每个餐厅的名字用字符串表示。
    你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。
    如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。

    示例 1:
        输入:
            ["Shogun", "Tapioca Express", "Burger King", "KFC"]
            ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
        输出: ["Shogun"]
        解释: 他们唯一共同喜爱的餐厅是“Shogun”。
    示例 2:
        输入:
            ["Shogun", "Tapioca Express", "Burger King", "KFC"]
            ["KFC", "Shogun", "Burger King"]
        输出: ["Shogun"]
        解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。

    提示:
        两个列表的长度范围都在 [1, 1000]内。
        两个列表中的字符串的长度将在[1，30]的范围内。
        下标从0开始，到列表的长度减1。
        两个列表都没有重复的元素。
 */
public class MinimumIndexSumOfTwoLists {

    /*
        遍历记录即可
        结果：
            8 ms, 82.43%
            39.2 MB, 13.31%
     */
    public String[] findRestaurant(String[] list1, String[] list2) {

        // 最小下标的值
        int min = Integer.MAX_VALUE;
        // 结果集合
        List<String> stringList = new ArrayList<>();

        // 将第一个列表变为 map 集合
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        // 遍历第二个列表
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                // 如果比 min 小，清空结果集合，更新最小值，加入当前元素
                if (i + map.get(list2[i]) < min) {
                    stringList.clear();
                    min = i + map.get(list2[i]);
                    stringList.add(list2[i]);
                }
                // 如果等于 min，把当前元素加入集合即可
                else if (i + map.get(list2[i]) == min) {
                    stringList.add(list2[i]);
                }
            }
        }
        String[] result = new String[stringList.size()];
        return stringList.toArray(result);
    }
}
