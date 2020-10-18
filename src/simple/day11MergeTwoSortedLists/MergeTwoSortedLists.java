package simple.day11MergeTwoSortedLists;

/**
 * @author Joush
 * @time 2020.10.18
 */

/*

    合并两个有序链表
    https://leetcode-cn.com/problems/merge-two-sorted-lists/
    
    将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

    示例：
        输入：1->2->4, 1->3->4
        输出：1->1->2->3->4->4

 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // l1 为空，返回l2
        if (l1 == null){
            return l2;
        }
        // l2 为空，返回l1
        if (l2 == null){
            return l1;
        }

        //
        ListNode head = new ListNode();
        ListNode p = head;

        while (l1 != null && l2 != null){
            // l1 比 l2 小
            if (l1.val < l2.val){
                // 将 l1 接上，并将 l1 向后挪
                p.next = l1;
                l1 = l1.next;
            }
            // l2 比 l1 小
            else {
                // 将 l1 接上，并将 l1 向后挪
                p.next = l2;
                l2 = l2.next;
            }
            // 主链往后挪
            p = p.next;
        }

        // l1 没接完
        if (l1 != null){
            p.next = l1;
            l1 = l1.next;
            p = p.next;
        }
        // l2 没接完
        if (l2 != null){
            p.next = l2;
            l2 = l2.next;
            p = p.next;
        }
        return head.next;
    }
}
