package y2020.m10October.day24MajorityElement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joush
 * @time 2020.10.31
 */

/*

    多数元素
    https://leetcode-cn.com/problems/majority-element/

    给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
    你可以假设数组是非空的，并且给定的数组总是存在多数元素。

    示例 1:
        输入: [3,2,3]
        输出: 3
    示例 2:
        输入: [2,2,1,1,1,2,2]
        输出: 2

 */
public class MajorityElement {


    /*
        摩尔投票法
        核心就是对拼消耗。
        玩一个诸侯争霸的游戏，假设你方人口超过总人口一半以上，并且能保证每个人口出去干仗都能一对一同归于尽。最后还有人活下来的国家就是胜利。
        那就大混战呗，最差所有人都联合起来对付你（对应你每次选择作为计数器的数都是众数），或者其他国家也会相互攻击（会选择其他数作为计数器的数），但是只要你们不要内斗，最后肯定你赢。
        最后能剩下的必定是自己人。
     */
    public int majorityElement2(int[] nums) {

        // 第一个值定为最多的值
        int index = nums[0];
        // 定义出现次数
        int times = 0;

        for (int i = 0; i < nums.length; i++) {
            // 遇到相同的就 +1
            if (nums[i] == index){
                times++;
            }
            // 否则就 -1
            else {
                times--;
            }
            // 如果减到 0
            if (times <= 0){

                // 更换目标数字
                index = nums[i];

                // 并将次数置为1
                times = 1;
            }
        }

        // 返回目标数字
        return index;

    }



    /*
        计算每个数字出现的次数，找出最多的，15ms 击败27.82%
     */
    public int majorityElement(int[] nums) {

        // 定义一个map
        Map<Integer, Integer> map = new HashMap<>();

        // 计数每个数字出现的次数
        for (int num : nums) {
            if (!map.containsKey(num)){
                map.put(num,1);
            } else {
                map.replace(num, map.get(num) + 1);
            }
        }
        int max = 0;
        // 遍历集合，找到次数最多的
        for (Integer i : map.keySet()){
            if (max < map.get(i)){
                max = i;
            }

        }
        // 返回
        return max;

    }

}
