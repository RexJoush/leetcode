package y2022.m01January.day468MaximizeSumOfArrayAfterKNegations;

/**
 * @author Rex Joush
 * @time 2022.01.18
 */

/*
    K 次取反后最大化的数组和
    https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/

    给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
    选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
    重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
    以这种方式修改数组后，返回数组 可能的最大和 。

    示例 1：
        输入：nums = [4,2,3], k = 1
        输出：5
        解释：选择下标 1 ，nums 变为 [4,-2,3] 。
    示例 2：
        输入：nums = [3,-1,0,2], k = 3
        输出：6
        解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
    示例 3：
        输入：nums = [2,-3,-1,5,-4], k = 2
        输出：13
        解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。

    提示：
        1 <= nums.length <= 10^4
        -100 <= nums[i] <= 100
        1 <= k <= 10^4

 */
public class MaximizeSumOfArrayAfterKNegations {

    /*
        贪心思想，计数排序
        将数组升序排序，将所有的 -x 变成 x，则总和会增大 2x
        如果所有的负数均变成了正数，则考虑三种情况
            - 如果数组中存在 0，那么即可将剩余的次数消耗完毕
            - 如果数组中不存在 0 且剩余的次数为偶数，那么也可以直接消耗完毕
            - 如果数组中不存在 0 且剩余的次数为奇数，那么将最小的整数取反
        排序时，考虑到数字的范围在 -100 - 100 之间，所以通过计数排序的方式，可以降低排序的时间消耗
        结果：
            1 ms, 99.62%
            37.6 MB, 98.40%
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        int[] number = new int[201];

        // 统计各个数字的整数，数字整体平移至 0-200 区间
        for (int num : nums) {
            number[num + 100]++;
        }

        int i = 0;
        while (k > 0) {
            // 找到当前的最小值
            while (number[i] == 0) {
                i++;
            }
            // 如果最小值为非负数，k 直接根据奇偶判断，剪枝
            if (i >= 100) {
                // 奇数
                if (k % 2 == 1) {
                    k = 0;
                } else {
                    break;
                }
            }

            number[i]--; // 此数字个数- 1
            number[200 - i]++; // 其相反数个数 +1
            k--;
        }

        // 求和即可
        int sum = 0;
        for (int j = 0; j < number.length; j++) {
            // j - 100 是数字大小，number[j] 是该数字出现次数.
            sum += (j - 100) * number[j];
        }
        return sum;
    }
}
