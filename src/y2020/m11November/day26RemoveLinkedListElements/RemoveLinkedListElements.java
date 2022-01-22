package y2020.m11November.day26RemoveLinkedListElements;

import datastructure.ListNode;

/**
 * @author Joush
 * @time 2020.11.02
 */

/*

    移除链表元素
    https://leetcode-cn.com/problems/remove-linked-list-elements/

    删除链表中等于给定值 val 的所有节点。

    示例:
        输入: 1->2->6->3->4->5->6, val = 6
        输出: 1->2->3->4->5

 */
public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {

        // 防止链表开头即有当前要删除的值
        while (head != null && head.val == val){
            head = head.next;
        }

        // 如果当前为空，直接返回
        if (head == null){
            return null;
        }

        // 定义工作变量
        ListNode temp = head;
        ListNode index = new ListNode(1);
        // 从头开始捋
        while (temp.next != null){

            // 遇见一样的，就删除
            if (temp.next.val == val){
                index = temp.next;
                temp.next = index.next;
            } else {
                temp = temp.next;
            }
        }

        // 链表返回
        return head;
    }
}
