package October20.day08SortedMergeLcci;

/**
 * @author Joush
 * @time 2020.10.15
 */

/*

    合并排序的数组
    https://leetcode-cn.com/problems/sorted-merge-lcci/
    
    给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
    初始化 A 和 B 的元素数量分别为 m 和 n。

    示例:
        输入:
        A = [1,2,3,0,0,0], m = 3
        B = [2,5,6],       n = 3

        输出: [1,2,2,3,5,6]
        说明:
        A.length == n + m
 */
public class SortedMergeLcci {

    public static void merge(int[] A, int m, int[] B, int n) {

        // 如果 B 空数组，直接返回
        if (n == 0) {
            return;
        }
        // 如果 A 空数组，则将 B 复制到 A 中，返回
        if (m == 0) {
            if (n >= 0)
                System.arraycopy(B, 0, A, 0, n);
            return;
        }

        // 插入排序的思想，将 B 的每一个值插入到A中，最后返回 A
        for (int i = 0; i < n; i++) {
            int j = m - 1;

            while (j >= 0 && A[j] > B[i]) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = B[i];
            m++;
        }
    }


    // 大哥解法
    public void merge2(int[] A, int m, int[] B, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;

        // 从最后面开始，看哪个大就把那个放到最后
        while (i >= 0 && j >= 0) {
            if (A[i] >= B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }

        // 最后将剩下的放到前面

        //下面这个while没必要写，因为经过上面的while循环后无论是 i还是j 为0，这时候index 和i都是相等的。
        //while(i >= 0) A[index--] = A[i--];
        while (j >= 0)
            A[index--] = B[j--];

    }

}
