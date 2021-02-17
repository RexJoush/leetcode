package y2021.m01January.day104FlippingAnImage;

/**
 * @author Joush
 * @time 2021.01.19
 */
public class FlippingAnImage {

    /*
        遍历即可，双指针进行交换，同时取反
        结果：
            0 ms, 100.00%
            38.4 MB, 92.53%
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] result = new int[A.length][A[0].length];

        for (int i = 0; i < A[0].length; i++) {
            int l = 0;
            int r = A.length - 1;
            while (l <= r){
                // 双指针左右同时遍历，记录右边节点的值
                int temp = A[i][r];
                // 如果左边是为 0，则右侧变为 0，取反则置为 1，否则置为 0
                if (A[i][l] == 0) result[i][r] = 1;
                else result[i][r] = 0;

                // 右侧如果为 0，左侧置为 1，否则置为 0
                if (temp == 0) result[i][l] = 1;
                else result[i][l] = 0;
                // 指针左移右移
                l++;
                r--;
            }
        }
        // 返回结果
        return result;
    }
}
