package y2021.m03March.day173BinaryPrefixDivisibleByFive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.03.29
 */

/*
    可被 5 整除的二进制前缀
    https://leetcode-cn.com/problems/binary-prefix-divisible-by-5/

    给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
    返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。

    示例 1：
        输入：[0,1,1]
        输出：[true,false,false]
        解释：输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
    示例 2：
        输入：[1,1,1]
        输出：[false,false,false]
    示例 3：
        输入：[0,1,1,1,1,1]
        输出：[true,false,false,false,true,false]
    示例 4：
        输入：[1,1,1,0,1]
        输出：[false,false,false,false,false]

    提示：
        1 <= A.length <= 30000
        A[i] 为 0 或 1

 */
public class BinaryPrefixDivisibleByFive {

    /*
        按权加和判断即可，因为长度有 3000 位，所以一定会溢出，只需考虑每次的值对 5 取余 即可
        结果：
            3 ms, 97.91%
            39.3 MB, 9.06%
     */
    public List<Boolean> prefixesDivBy5(int[] nums) {

        List<Boolean> result = new LinkedList<>();

        int prefix = 0;
        for (int num : nums) {
            // 每次新的值等于前面的值 * 2 + 当前的值，同时对 5 取模
            prefix = ((prefix << 1) + num) % 5;
            // 当前为 0 时，即能被 5 整除
            result.add(prefix == 0);
        }

        return result;
    }

    /*
        DFA 有限状态自动机
        所有的状态共有 5 种，即 0，1，2，3，4

            0 --0--> 0 // 此处 00 = 0 % 5 = 0
            0 --1--> 1 // 此处 01 = 1 % 5 = 1

            1 --0--> 2 // 此处 10 = 2 % 5 = 2
            1 --1--> 1 // 此处 11 = 3 % 5 = 3

            2 --0--> 1 // 此处 100 = 4 % 5 = 4
            2 --1--> 0 // 此处 101 = 5 % 5 = 0

            3 --0--> 1 // 此处 110 = 6 % 5 == 1
            3 --1--> 1 // 此处 111 = 7 % 5 == 2

            4 --0--> 3 // 此处 1000 = 8 % 5 = 3
            4 --1--> 4 // 此处 1001 = 9 % 5 = 4
     */
    public List<Boolean> prefixesDivBy52(int[] nums) {
        List<Boolean> res = new ArrayList<>();
        if (nums.length < 1) return res;
        int[][] StateSet = new int[][]{
                {0, 1}, // 0 遇见 0, 1 的两个状态
                {2, 3}, // 1 遇见 0, 1 的两个状态
                {4, 0},
                {1, 2},
                {3, 4}
        };
        int state = 0; // 初始为 0
        for (int num : nums) {
            // 当前状态等于 之前的状态遇到 0 或 1 后的结果
            state = StateSet[state][num];  //转换后的状态
            // 仅状态 0 满足条件
            if (state == 0) {
                res.add(Boolean.TRUE);
            } else {
                res.add(Boolean.FALSE);
            }
        }

        return res;
    }
}
