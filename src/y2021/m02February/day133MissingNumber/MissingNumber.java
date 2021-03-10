package y2021.m02February.day133MissingNumber;

/**
 * @author Joush
 * @time 2021.02.17
 */

/*
    丢失的数字
    https://leetcode-cn.com/problems/missing-number/

    给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。

    进阶：
        你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?

    示例 1：
        输入：nums = [3,0,1]
        输出：2
        解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
    示例 2：
        输入：nums = [0,1]
        输出：2
        解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
    示例 3：
        输入：nums = [9,6,4,2,3,5,7,0,1]
        输出：8
        解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
    示例 4：
        输入：nums = [0]
        输出：1
        解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。

    提示：
        n == nums.length
        1 <= n <= 104
        0 <= nums[i] <= n
        nums 中的所有数字都 独一无二

 */
public class MissingNumber {

    /*
        位运算，对一个数字进行两次相同的异或结果为本数字，即 n^x^x = n
        所以将 0-n 的所有数字进行异或，在将此结果与数组中的值进行异或
        最后的值因为缺失的数字只进行了一次异或，所以结果就是缺失的值
        结果：
            1 ms, 50.47%
            39.1 MB, 27.55%
     */
    public int missingNumber2(int[] nums) {
        int missing = nums.length;
        // 遍历的同时，和 i 进行异或，秩序遍历一次即可
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
    /*
        n 个数字相加的和可以通过等差数列求和公式计算出来，再挨个减去数组中的值，结果即为缺失的数字
        结果：
            0 ms, 100.00%
            39 MB, 56.43%
     */
    public int missingNumber(int[] nums) {
        // 计算数组的前 n 项和
        long sum = (long) (1 + nums.length) * nums.length / 2;
        // 减去数组中的值
        for (int num : nums) {
            sum -= num;
        }
        // 返回剩下的值即可
        return (int) sum;
    }
}
