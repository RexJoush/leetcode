package simple.day02ReverseList;

/**
 * author: joush
 * time: 2020.10.09
 */

/*
    反转一个单链表。

    示例:

    输入: 1->2->3->4->5->NULL
    输出: 5->4->3->2->1->NULL
 */
public class Demo02ReverseList {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {

        // 如果为空链表或者单个节点链表，那么直接返回head
        if (head == null || head.next == null){
            return head;
        }

        // 定义头结点
        ListNode t = new ListNode();

        // 定义工作节点
        ListNode m;

        // 头结点指向第一个节点
        t.next = head;

        // 工作节点指向第二个节点
        m = head.next;

        while (head.next != null){

            //
            head.next = m.next;
            m.next = t.next;

            t.next = m;

            m = head.next;
        }

        return t.next;
    }

}