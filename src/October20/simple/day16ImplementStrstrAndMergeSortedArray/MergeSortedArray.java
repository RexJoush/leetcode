package October20.simple.day16ImplementStrstrAndMergeSortedArray;

/**
 * @author Joush
 * @time 2020.10.23
 */

/*

    合并两个有序数组
    https://leetcode-cn.com/problems/merge-sorted-array/
    
    给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

    说明：
    初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
    你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

    示例：
        输入：
        nums1 = [1,2,3,0,0,0], m = 3
        nums2 = [2,5,6],       n = 3
        输出：[1,2,2,3,5,6]

    提示：
        -10^9 <= nums1[i], nums2[i] <= 10^9
        nums1.length == m + n
        nums2.length == n

 */
public class MergeSortedArray {


    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;

        // 从最后面开始，看哪个大就把那个放到最后
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }

        // 最后将剩下的放到前面

        //下面这个while没必要写，因为经过上面的while循环后无论是 i还是j 为0，这时候index 和i都是相等的。
        //while(i >= 0) A[index--] = A[i--];
        while (j >= 0)
            nums1[index--] = nums2[j--];


    }
}
