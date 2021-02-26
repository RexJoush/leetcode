package y2021.m02February.day124HappyNumber;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joush
 * @time 2021.02.08
 */

/*
    快乐数
    https://leetcode-cn.com/problems/happy-number/

    编写一个算法来判断一个数 n 是不是快乐数。
    「快乐数」定义为：
        对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
        然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
        如果 可以变为 1，那么这个数就是快乐数。
        如果 n 是快乐数就返回 true ；不是，则返回 false 。

    示例 1：
        输入：19
        输出：true
        解释：
        12 + 92 = 82
        82 + 22 = 68
        62 + 82 = 100
        12 + 02 + 02 = 1
    示例 2：
        输入：n = 2
        输出：false

    提示：
        1 <= n <= 2^31 - 1

 */
public class HappyNumber {

    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappy(2));
    }

    /*
        一直寻找即可，共分三种情况
            1.循环到某一刻，等于1，返回 true
            2.无限循环，数字进入某个环重复下去，返回 false
            3.无限循环，数字趋近于无穷大，此种情况其实不会发生

        结果：
            1 ms, 99.67%
            35.7 MB, 16.10%
     */
    public boolean isHappy(int n) {
        // 设置哈希表，存储是否循环
        Set<Integer> set = new HashSet<>();
        int temp = getNext(n);

        // 找到 1 停止
        while (temp != 1) {
            // 如果进入环，则返回 false
            if (set.contains(temp)){
                return false;
            }
            // 否则加入集合，继续循环
            set.add(temp);
            // 更新 temp
            temp = getNext(temp);
        }
        return true;
    }

    /**
     * 获取该数的各位平方和
     * @param n 要计算的数
     * @return 平方和
     */
    public int getNext(int n) {
        int result = 0;
        while (n != 0) {
            result += (n % 10) * (n % 10);
            n /= 10;
        }
        return result;
    }
}
