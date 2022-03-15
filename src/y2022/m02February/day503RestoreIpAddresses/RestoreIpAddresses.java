package y2022.m02February.day503RestoreIpAddresses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.02.22
 */

/*
    复原 IP 地址
    https://leetcode-cn.com/problems/restore-ip-addresses/

    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
    例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
    给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。

    示例 1：
        输入：s = "25525511135"
        输出：["255.255.11.135","255.255.111.35"]
    示例 2：
        输入：s = "0000"
        输出：["0.0.0.0"]
    示例 3：
        输入：s = "101023"
        输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

    提示：
        1 <= s.length <= 20
        s 仅由数字组成

 */
public class RestoreIpAddresses {

    /*
        回溯法
        结果：
            102 ms, 54.88%
            38.8 MB, 59.28%
     */
    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) {
            return new ArrayList<>();
        }
        char[] chars = s.toCharArray();
        List<String> result = new ArrayList<>();
        backTrace(chars, 0, 0, true, new ArrayList<>(), result);
        return result;
    }


    /**
     * 回溯过程
     *
     * @param chars  字符数组
     * @param index  当前遍历到的数字索引
     * @param base   前面已经计算的值
     * @param flag   前面是否将数字加入集合，即是否放置 点
     * @param temp   临时数组，记录路径
     * @param result 结果数组
     */
    private void backTrace(char[] chars, int index, int base, boolean flag, List<Integer> temp, List<String> result) {
        // 四个数字，同时遍历到最后，满足条件，加入结果集合
        if (temp.size() == 4 && index == chars.length) {
            result.add(temp.get(0) + "." + temp.get(1) + "." + temp.get(2) + "." + temp.get(3));
            return;
        }
        if (temp.size() == 4 && index < chars.length) {
            return;
        }
        if (index >= chars.length) {
            return;
        }

        // 没放点，同时前面为 0
        if (base == 0 && !flag) {
            return;
        }

        // 25525511135
        // 当前的 value
        int value = base * 10 + chars[index] - '0';

        if (value > 255) {
            return;
        }

        // 放点，加入数字
        temp.add(value);
        backTrace(chars, index + 1, 0, true, temp, result);
        // 不放点
        temp.remove(temp.size() - 1);
        backTrace(chars, index + 1, value, false, temp, result);

    }

}
