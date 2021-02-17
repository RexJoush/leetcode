package y2020.m11November.day31SeriesInPoker;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joush
 * @time 2020.11.07
 */

/*
    扑克牌中的顺子（剑指 Offer 61）
    https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/

    从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

    示例 1:
        输入: [1,2,3,4,5]
        输出: true
    示例 2:
        输入: [0,0,1,2,5]
        输出: true

    限制：
        数组长度为 5
        数组的数取值为 [0, 13]


 */
public class SeriesInPoker {

    /*
        如果有非 0 的对子，则直接返回 false，否则看最大值与最小值之差是否小于4，不够4了就用0补齐
     */
    public boolean isStraight(int[] nums) {
        // 去除重复
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){
                continue;
            }
            else {
                if (!set.add(nums[i])){
                    return false;
                }
            }
        }

        int max = -1;
        int min = 14;

        for (int i = 0; i < nums.length; i++){
            // 遇见0就过
            if (nums[i] == 0){
                continue;
            }
            // 记录最大值
            if (nums[i] > max){
                max = nums[i];
            }
            // 记录最小值
            if (nums[i] < min){
                min = nums[i];
            }
        }

        // 返回比较结果
        return max - min <= 4;

    }

}
