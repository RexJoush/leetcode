package y2020.m11November.day32CheckIfNAndItsDoubleExist;

import java.util.*;

/**
 * @author Joush
 * @time 2020.11.08
 */

/*

    检查整数及其两倍数是否存在
    https://leetcode-cn.com/problems/check-if-n-and-its-double-exist/

    给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
    更正式地，检查是否存在两个下标 i 和 j 满足：
        i != j
        0 <= i, j < arr.length
        arr[i] == 2 * arr[j]
    示例 1：
        输入：arr = [10,2,5,3]
        输出：true
        解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5 。
    示例 2：
        输入：arr = [7,1,14,11]
        输出：true
        解释：N = 14 是 M = 7 的两倍，即 14 = 2 * 7 。
    示例 3：
        输入：arr = [3,1,7,11]
        输出：false
        解释：在该情况下不存在 N 和 M 满足 N = 2 * M 。
    提示：
        2 <= arr.length <= 500
        -10^3 <= arr[i] <= 10^3

 */
public class CheckIfNAndItsDoubleExist {

    public boolean checkIfExist(int[] arr) {
        // 空数组返回 false
        if (arr.length == 0){
            return false;
        }

        // 定义哈希表
        Set<Integer> set = new HashSet<>();

        // 将所有数字放进去
        for (int i : arr){
            // 即遇见两个0，则直接返回true，该题只有一个0，不算 true，两个0才算
            if (i == 0 && !set.add(i)){
                return true;
            }
            set.add(i);
        }

        // 查找看是否存在两倍的数字，
        for (int i : arr) {
            // 排除单个0的情况
            if (i != 0 && set.contains(i * 2)) {
                return true;
            }
        }

        // 都没有，返回 false
        return false;

    }

}
