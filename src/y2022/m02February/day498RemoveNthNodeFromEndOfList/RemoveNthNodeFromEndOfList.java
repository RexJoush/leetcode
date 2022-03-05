package y2022.m02February.day498RemoveNthNodeFromEndOfList;

import datastructure.ListNode;

/**
 * @author Rex Joush
 * @time 2022.02.17
 */

/*
    删除链表的倒数第 N 个结点
    https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/

    给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

    示例 1：
            1 --> 2 --> 3 --> 4 --> 5
            1 --> 2 --> 3 --------> 5
        输入：head = [1,2,3,4,5], n = 2
        输出：[1,2,3,5]
    示例 2：
        输入：head = [1], n = 1
        输出：[]
    示例 3：
        输入：head = [1,2], n = 1
        输出：[1]

    提示：
        链表中结点的数目为 sz
        1 <= sz <= 30
        0 <= Node.val <= 100
        1 <= n <= sz

    进阶：
        你能尝试使用一趟扫描实现吗？

 */
public class RemoveNthNodeFromEndOfList {

    /*
        双指针，需建立一个空的头节点，避免边界条件
        结果：
            0 ms, 100.00%
            39.4 MB, 32.27%
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;

        // 将 first 向后移动 n 个节点
        while (n-- != 0) {
            first = first.next;
        }

        // first，second 同时向后移动，当 first 到链尾时，second 在倒数第 n 个节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 更新指向即可
        second.next = second.next.next;
        // 返回头节点
        return dummy.next;
    }

}
