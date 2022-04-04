package y2021.m04April.day176FindTheDuplicateNumber;

/**
 * @author Rex Joush
 * @time 2021.04.01
 */

/*
    寻找重复数
    https://leetcode-cn.com/problems/find-the-duplicate-number/

    给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。

    示例 1：
        输入：nums = [1,3,4,2,2]
        输出：2
    示例 2：
        输入：nums = [3,1,3,4,2]
        输出：3

    提示：
        1 <= n <= 10^5
        nums.length == n + 1
        1 <= nums[i] <= n
        nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次

    进阶：
        如何证明 nums 中至少存在一个重复的数字?
        你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？

 */
public class FindTheDuplicateNumber {

    /*
        Floyd 判圈算法
            将给定的数组抽象成链表，利用 环形链表II 的思路，使用快慢指针法
        结果：
            4 ms, 94.18%
            58.4 MB, 56.08%
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        // 两针相遇
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // 将慢指针置为初始值
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
