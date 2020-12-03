package December20.day57PowerfulIntegers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2020.12.03
 */

/*
    强整数
    https://leetcode-cn.com/problems/powerful-integers/

    给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。
    返回值小于或等于 bound 的所有强整数组成的列表。
    你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。

    示例 1：
        输入：x = 2, y = 3, bound = 10
        输出：[2,3,4,5,7,9,10]
        解释：
        2 = 2^0 + 3^0
        3 = 2^1 + 3^0
        4 = 2^0 + 3^1
        5 = 2^1 + 3^1
        7 = 2^2 + 3^1
        9 = 2^3 + 3^0
        10 = 2^0 + 3^2
    示例 2：
        输入：x = 3, y = 5, bound = 15
        输出：[2,4,6,8,10,14]

    提示：
        1 <= x <= 100
        1 <= y <= 100
        0 <= bound <= 10^6

 */
public class PowerfulIntegers {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {

        List<Integer> list = new ArrayList<>();
        if (bound == 0){
            return list;
        }

        // 暴力法，因为 bound 在 10^6 以内，而 2^20 已经大于 10^6，所以在 20 以内即可
        for (int i = 0; i < 20 && Math.pow(x, i) <= bound; i++){
            for (int j = 0; j < 20 && Math.pow(y, j) <= bound; j++){

                // 计算，如果小于 bound 且 list 不包含，则加入集合
                int value = (int) (Math.pow(x,i) + Math.pow(y, j));
                if (value <= bound && !list.contains(value)){
                    list.add(value);
                }
            }
        }
        // 返回 list
        return list;
    }

}
