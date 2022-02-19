package y2022.m01January.day473MedianOfTwoSortedArrays;

/**
 * @author Rex Joush
 * @time 2022.01.23
 */

/*
    寻找两个正序数组的中位数
    https://leetcode-cn.com/problems/median-of-two-sorted-arrays/

    给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。
    请你找出并返回这两个正序数组的 中位数 。
    算法的时间复杂度应该为 O(log (m+n)) 。

    示例 1：
        输入：nums1 = [1,3], nums2 = [2]
        输出：2.00000
        解释：合并数组 = [1,2,3] ，中位数 2
    示例 2：
        输入：nums1 = [1,2], nums2 = [3,4]
        输出：2.50000
        解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

    提示：
        nums1.length == m
        nums2.length == n
        0 <= m <= 1000
        0 <= n <= 1000
        1 <= m + n <= 2000
        -106 <= nums1[i], nums2[i] <= 106

 */
public class MedianOfTwoSortedArrays {

    /*
        方法一：合并两个数组，合并的时候，因为两个数组是升序排列，所以按升序合并即可。
               合并后长度（length）为奇数，则取第 (length + 1) / 2 个，若为偶数，取 length / 2 和 length / 2 + 1 个数
        方法二：考虑不合并两个数组，只需循环遍历到中位数的值即可。
               下面即为此方法的答案。
        结果：
            1 ms, 100.00%
            41.9 MB, 21.59%
        由于以上两种方法的时间复杂度均为 o(m + n)
        不满足题目要求的时间复杂度 log(m + n)
        因此，考虑方法三
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 获取总长度
        int length = nums1.length + nums2.length;

        // 中位数
        int median = -1;
        // 中位数右边的值
        int median2 = -1;
        int i = 0; // 遍历数组 nums1
        int j = 0; // 遍历数组 nums2

        // 开始遍历
        for (int index = 0; index <= length / 2; index++) {

            median = median2;

            // 首先保证，第一个数组不遍历到底
            // 其次，第二个数组也不遍历到底
            // 如果第一个数组的数字较小，则第一个数字往后移动，否则，第二个数字往后移动
            if (i < nums1.length && (j >= nums2.length || nums1[i] < nums2[j])) {
                median2 = nums1[i++];
            } else {
                median2 = nums2[j++];
            }
        }

        // 若为偶数，取两个值的平均值
        if ((length & 1) == 0) {
            return (median + median2) / 2.0;
        }
        // 奇数，返回中间值即可。
        return median2;
    }

    /*
        方法三：过于难以理解，查看官方的视频题解吧。。。
        https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }
}
