package y2021.m01January.day108RemoveDuplicatesSortedList;

/**
 * @author Joush
 * @time 2021.01.23
 */

/*
    删除排序链表中的重复元素
    https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/

    给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

    示例 1:
        输入: 1->1->2
        输出: 1->2
    示例 2:
        输入: 1->1->2->3->3
        输出: 1->2->3

 */
public class RemoveDuplicatesSortedList {

    /*
        建立一个新链表，遍历主链表，将所有不同的节点接到新链表上，返回即可
        结果：
            0 ms, 100.00%
            37.8 MB, 70.84%
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 空链表或者单节点链表直接返回
        if (head == null || head.next == null){
            return head;
        }
        // 定义结果链表
        ListNode result = head;
        // 工作指针 p 和 q
        ListNode p = head.next;
        ListNode q = result;
        // p 如果与当前的 q 相等，则向后移，否则接上连接即可
        while (p != null){
            // 找到与结果节点不相同的节点
            while (p.val == q.val){
                p = p.next;
                if (p == null){
                    break;
                }
            }
            // 接入结果链表
            q.next = p;
            // 结果连别向后移
            q = q.next;
        }
        // 返回结果链表
        return result;
    }
}
