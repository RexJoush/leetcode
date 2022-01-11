package y2021.m03March.day161CardGrouping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2021.03.17
 */

/*
    卡牌分组
    https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/

    给定一副牌，每张牌上都写着一个整数。
    此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
    每组都有 X 张牌。
    组内所有的牌上都写着相同的整数。
    仅当你可选的 X >= 2 时返回 true。

    示例 1：
        输入：[1,2,3,4,4,3,2,1]
        输出：true
        解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
    示例 2：
        输入：[1,1,1,2,2,2,3,3]
        输出：false
        解释：没有满足要求的分组。
    示例 3：
        输入：[1]
        输出：false
        解释：没有满足要求的分组。
    示例 4：
        输入：[1,1]
        输出：true
        解释：可行的分组是 [1,1]
    示例 5：
        输入：[1,1,2,2,2,2]
        输出：true
        解释：可行的分组是 [1,1]，[2,2]，[2,2]

    提示：
        1 <= deck.length <= 10000
        0 <= deck[i] < 10000

 */
public class CardGrouping {

    public static void main(String[] args) {
        new CardGrouping().hasGroupsSizeX(new int[]{1,1});
    }

    /*
        方法一：枚举
        先统计各个牌的数量，从 2 开始枚举 x，由题意可得，当 x 满足条件时
            须满足， x % deck.length = 0
            且， x % 每张牌的个数 = 0
        结果：
            9 ms, 37.28%
            39.3 MB, 90.4%
     */
    public boolean hasGroupsSizeX(int[] deck) {

        // 统计各个牌的数量
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : deck) {
            int value = map.getOrDefault(i, 0) + 1;
            map.put(i, value);
        }

        int n = deck.length;

        // 从 2 开始枚举
        for (int i = 2; i <= n; i++) {
            // 剪枝，当 i 无法整除长度时，肯定错误
            if (n % i == 0) {
                boolean flag = true;
                // 遍历整个集合
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    int value = entry.getValue();
                    // 如果某个不满足，则终止循环，i ++，继续枚举
                    if (value % i != 0) {
                        flag = false;
                        break;
                    }
                }
                // 满足则返回 true
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
        方法二：最大公约数
        由方法一可得，当 x 为所有个数的最大公约数时，即满足条件，所以只需求出所有个数的最大公约数即可
        结果：
            11 ms, 31.36%
            38.7 MB, 65.09%
     */
    public boolean hasGroupsSizeX2(int[] deck) {

        // 统计各个牌的数量
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : deck) {
            int value = map.getOrDefault(i, 0) + 1;
            map.put(i, value);
        }
        int g = -1;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (g == -1) {
                g = value;
            } else {
                g = gcd(g, value);
            }
        }
        // 如果最大公约数大于 2， 则返回 true
        return g >= 2;
    }

    /*
        辗转相除法求最大公约数
     */
    public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}
