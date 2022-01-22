package y2022.m01January.day472AddTwoNumbers;

import datastructure.ListNode;

/**
 * @author Rex Joush
 * @time 2022.01.22
 */

/*
    两数相加
    https://leetcode-cn.com/problems/add-two-numbers/

    给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    示例 1：
            2 --> 4 --> 3
            5 --> 6 --> 4
          ———————————————
            7 --> 0 --> 8
        输入：l1 = [2,4,3], l2 = [5,6,4]
        输出：[7,0,8]
        解释：342 + 465 = 807.
    示例 2：
        输入：l1 = [0], l2 = [0]
        输出：[0]
    示例 3：
        输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        输出：[8,9,9,9,0,0,0,1]

    提示：
        每个链表中的节点数在范围 [1, 100] 内
        0 <= Node.val <= 9
        题目数据保证列表表示的数字不含前导零

 */
public class AddTwoNumbers {

    /*
        正常计算结果即可
        结果：
            1 ms, 100.00%
            38.6 MB, 47.39%
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode();
        ListNode p = head;
        int pre = 0; // 进位

        // 计算
        while (l1 != null && l2 != null) {
            // 计算结果
            int val = l1.val + l2.val + pre;
            // 新建当前位的节点
            ListNode node = new ListNode(val % 10);
            pre = val / 10; // 更新进位
            // 接上头节点
            p.next = node;
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        // l1 未结束，拼接 l1
        while (l1 != null) {
            int val = l1.val + pre;
            ListNode node = new ListNode(val % 10);
            pre = val / 10; // 更新进位
            p.next = node;
            p = p.next;
            l1 = l1.next;
        }

        // l2 未结束，拼接 l2
        while (l2 != null) {
            int val = l2.val + pre;
            ListNode node = new ListNode(val % 10);
            pre = val / 10; // 更新进位
            p.next = node;
            p = p.next;
            l2 = l2.next;
        }

        // 最后还有进位，拼接上
        if (pre != 0) {
            p.next = new ListNode(pre);
        }

        return head.next;
    }
}
