package December20.day59TwiceOfOthers;


/**
 * @author Joush
 * @time 2020.12.05
 */

/*
    至少是其他数字两倍的最大数
    https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/
    
    在一个给定的数组nums中，总是存在一个最大元素 。
    查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
    如果是，则返回最大元素的索引，否则返回-1。

    示例 1:
        输入: nums = [3, 6, 1, 0]
        输出: 1
        解释: 6 是最大的整数, 对于数组中的其他整数, 6 大于数组中其他元素的两倍。6 的索引是 1 , 所以我们返回 1.
    示例 2:
        输入: nums = [1, 2, 3, 4]
        输出: -1
        解释: 4 没有超过 3 的两倍大, 所以我们返回 -1.

    提示:
        1.nums 的长度范围在[1, 50].
        2.每个 nums[i] 的整数范围在 [0, 100].

 */
public class TwiceOfOthers {

    /*
        暴力法，第一遍查找最大的元素，然后第二遍挨个比较
     */
    public int dominantIndex(int[] nums) {
        /*
            找到最大的元素及下标值
         */
        int max = nums[0];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]){
                max = nums[i];
                index = i;
            }
        }
        // 判断是否大于所有的元素 2 倍
        for (int i = 0; i < nums.length; i++) {
            // 跳过自身
            if (i == index) {
                continue;
            }
            if (max < 2 * nums[i]){
                return -1;
            }
        }
        // 满足情况，返回下标
        return index;
    }

}
