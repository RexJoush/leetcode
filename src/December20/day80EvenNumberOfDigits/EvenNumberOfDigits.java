package December20.day80EvenNumberOfDigits;

/**
 * @author Joush
 * @time 2020.12.26
 */

/*
    统计位数为偶数的数字
    https://leetcode-cn.com/problems/find-numbers-with-even-number-of-digits/

    给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。

    示例 1：
        输入：nums = [12,345,2,6,7896]
        输出：2
        解释：
        12 是 2 位数字（位数为偶数）
        345 是 3 位数字（位数为奇数）
        2 是 1 位数字（位数为奇数）
        6 是 1 位数字 位数为奇数）
        7896 是 4 位数字（位数为偶数）
        因此只有 12 和 7896 是位数为偶数的数字
    示例 2：
        输入：nums = [555,901,482,1771]
        输出：1
        解释：
        只有 1771 是位数为偶数的数字。

    提示：
        1 <= nums.length <= 500
        1 <= nums[i] <= 10^5

 */
public class EvenNumberOfDigits {

    /*
        遍历数组，每个数字计算位数即可，注意，通过 / 10 来进行计数
        结果：
            1 ms, 100.00%
            38.1 MB, 65.00%
     */
    public int findNumbers(int[] nums) {
        int result = 0;
        int temp = 0;
        for (int num : nums){
            while (num / 10 != 0){
                temp++;
                num /= 10;
            }
            if (temp % 2 != 0){
                result++;
            }
            temp = 0;

        }
        return result;
    }

    /*
        将每个数字转换成字符串，计算长度
        结果：
            2 ms, 60.24%
            38.2 MB, 50.85%
     */
    public int findNumbers2(int[] nums) {
        int result = 0;
        int temp = 0;

        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0) {
                result++;
            }
        }
        return result;
    }
}
