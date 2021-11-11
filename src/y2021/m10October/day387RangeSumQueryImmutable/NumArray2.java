package y2021.m10October.day387RangeSumQueryImmutable;

/**
 * @author Rex Joush
 * @time 2021.10.29
 */
public class NumArray2 {

    private int[] sums;

    /*
        在存储 nums 时，每个位置存储当前位置的前缀和
        所以计算 nums[i] + ... + nums[j] 时，只需返回 sum[j + 1] - sum[i] 即可
     */
    public NumArray2(int[] nums) {
        sums = new int[nums.length + 1];
        // 每次计算前缀和即可
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    /*
        返回 sums[right + 1] - sums[left]
        结果：
            7 ms, 100.00%
            41.2 MB, 58.47%
     */
    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }

}
