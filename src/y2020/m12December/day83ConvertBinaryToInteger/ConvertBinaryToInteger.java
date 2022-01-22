package y2020.m12December.day83ConvertBinaryToInteger;

import datastructure.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2020.12.29
 */

/*
    二进制链表转整数
    https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
    
    给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
    请你返回该链表所表示数字的 十进制值 。

    示例 1：
        输入：head = [1,0,1]
        输出：5
        解释：二进制数 (101) 转化为十进制数 (5)
    示例 2：
        输入：head = [0]
        输出：0
    示例 3：
        输入：head = [1]
        输出：1
    示例 4：
        输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
        输出：18880
    示例 5：
        输入：head = [0,0]
        输出：0

    提示：
        链表不为空。
        链表的结点总数不超过 30。
        每个结点的值不是 0 就是 1。

 */
public class ConvertBinaryToInteger {
    /*
        遍历链表，将数字挨个放入list中，然后按权加和即可
        结果：
            1 ms, 18.41%
            35.7 MB, 82.12%
     */
    public int getDecimalValue(ListNode head) {
        int length = 0;
        int result = 0;
        List<Integer> list = new ArrayList<>();
        while (head != null){
            length++;
            list.add(head.val);
            head = head.next;
        }

        for (int i = 0; i < length; i++){
            result += list.get(i) * Math.pow(2, length - i - 1);
        }
        return result;
    }

    /*
        位运算，左移加上当前位即可
        0 ms, 100.00%
        35.9 MB, 54.87%
     */
    public int getDecimalValue2(ListNode head){
        int sum = 0;
        while (head != null) {
            sum = (sum << 1) + head.val;
            head = head.next;
        }
        return sum;
    }
}
