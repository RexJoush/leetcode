package y2021.m03March.day156MiddleOfTheLinkedList;

import datastructure.ListNode;

/**
 * @author Rex Joush
 * @time 2021.03.12
 */

/*
    链表的中间结点
    https://leetcode-cn.com/problems/middle-of-the-linked-list/

    给定一个头结点为 head 的非空单链表，返回链表的中间结点。
    如果有两个中间结点，则返回第二个中间结点。
    
    示例 1：
        输入：[1,2,3,4,5]
        输出：此列表中的结点 3 (序列化形式：[3,4,5])
        返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
        注意，我们返回了一个 ListNode 类型的对象 ans，这样：
        ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
    示例 2：
        输入：[1,2,3,4,5,6]
        输出：此列表中的结点 4 (序列化形式：[4,5,6])
        由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
    
    提示：
        给定链表的结点数介于 1 和 100 之间。

 */
public class MiddleOfTheLinkedList {

    /*
        方法一：数组
            节点个数小于 100 个，将所有节点放入数组中，返回中间的即可
            结果：
                0 ms, 100.00%
                36 MB, 22.64%
     */
    public ListNode middleNode(ListNode head) {
        ListNode[] nodes = new ListNode[100];
        int count = 0;
        while (head != null) {
            nodes[count++] = head;
            head = head.next;
        }
        return nodes[count / 2];
    }

    /*
        方法一：快慢指针
            一个指针每次走一步，另一个指针每次走两步
            当第二个指针到末尾时，第一个指针正好在中间
            结果：
                0 ms, 100.00%
                36 MB, 22.64%
     */
    public ListNode middleNode2(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
