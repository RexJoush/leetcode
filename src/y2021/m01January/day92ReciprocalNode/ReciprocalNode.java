package y2021.m01January.day92ReciprocalNode;

/**
 * @author Joush
 * @time 2021.01.07
 */

/*
    链表中倒数第k个节点（剑指 Offer 22）
    https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/

    输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
    例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

    示例：
        给定一个链表: 1->2->3->4->5, 和 k = 2.
        返回链表 4->5.

 */
public class ReciprocalNode {
    /*
        两个指针，第一个先走 k 个节点，然后第二个指针从头开始和第二个指针一起走，当第一个到末尾时第二个就在倒数第 k 个
        结果：
            0 ms, 100.00%
            36.2 MB, 69.71%
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p = head;
        ListNode result = head;
        // 第一个先走 k 个节点
        for (int i = 0; i < k; i++) {
            p = p.next;
        }
        // 第一个到末尾时，第二个在倒数第 k 个
        while (p != null){
            p = p.next;
            result = result.next;
        }

        return result;
    }
}
