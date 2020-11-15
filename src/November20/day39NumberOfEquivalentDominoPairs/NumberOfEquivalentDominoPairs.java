package November20.day39NumberOfEquivalentDominoPairs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joush
 * @time 2020.11.15
 */

/*
    等价多米诺骨牌对的数量
    https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/

    给你一个由一些多米诺骨牌组成的列表 dominoes。
    如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
    形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
    在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。

    示例：
        输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
        输出：1

    提示：
        1 <= dominoes.length <= 40000
        1 <= dominoes[i][j] <= 9

 */
public class NumberOfEquivalentDominoPairs {

    /*
        即表示，如果两个值一样，也算作同样的骨牌，[1,2],[1,2],[2,2] 应该返回3
        暂未看懂，明天来看
     */
    public int numEquivDominoPairs2(int[][] dominoes) {
        int ans = 0;
        int[] cp = new int[100];
        for(int[] arr : dominoes){
            if (arr[0] > arr[1]){
                ans+=cp[arr[1]*10+arr[0]]++;
            }
            else {
                ans+=cp[arr[0]*10+arr[1]]++;
            }
        }
        return ans;

    }

    public int numEquivDominoPairs(int[][] dominoes) {

        int result = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < dominoes.length; i++){

            // 如果包含此键，那么查询结果值列表，是否包含当前的值，包含则加一
            if (map.containsKey(dominoes[i][1])){
                // 获取列表
                List<Integer> integers = map.get(dominoes[i][1]);
                // 查看是否存在当前值
                if (integers.contains(dominoes[i][0])){
                    result++;
                }
                else {
                    integers.add(dominoes[i][0]);
                    map.put(dominoes[i][1], integers);
                }
            }
            else {
                List<Integer> list = new ArrayList<>();
                list.add(dominoes[i][1]);
                map.put(dominoes[i][0],list);
            }
        }

        System.out.println(map);

        return result;
    }



}
