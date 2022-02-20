package y2022.m01January.day477ThreeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.01.27
 */

/*
    三数之和
    https://leetcode-cn.com/problems/3sum/

    给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    注意：答案中不可以包含重复的三元组。

    示例 1：
        输入：nums = [-1,0,1,2,-1,-4]
        输出：[[-1,-1,2],[-1,0,1]]
    示例 2：
        输入：nums = []
        输出：[]
    示例 3：
        输入：nums = [0]
        输出：[]

    提示：
        0 <= nums.length <= 3000
        -10^5 <= nums[i] <= 10^5

 */
public class ThreeSum {

    /*
        方法一：三重循环暴力查找
            此种情况下，考虑极端情况，数组中全都是 [0, 0, 0, 0, ..., 0, 0, 0]
            此时，找到所有的三元组之后，需要去重，效率很低。
        方法二：排序
            考虑先排序，即始终保证，a < b < c, 满足 a + b + c = 0。
            这样，遍历所有三元组，无需考虑去重的情况。
        方法三：排序 + 双指针
            首先排序，那么考虑，当 a 确定时，找到 b + c = -a 的 b,c 时，
            因为 c 一定是更大的值，所以，考虑双指针，左边开始寻找 b，右边开始寻找 c
            此时，复杂度即降为 O(n2)
        结果：
            21 ms, 62.49%
            45.4 MB, 17.04%
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 先排序
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        // 枚举 a
        for (int first = 0; first < n; first++) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; second++) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a + b + c = 0 并且 b < c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                // 满足条件，加入结果集合
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
