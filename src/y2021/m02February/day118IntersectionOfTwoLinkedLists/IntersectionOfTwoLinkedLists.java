package y2021.m02February.day118IntersectionOfTwoLinkedLists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2021.02.02
 */

/*
    相交链表
    https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    
    编写一个程序，找到两个单链表相交的起始节点。
    
    如下面的两个链表：
        在节点 c1 开始相交。
                 a1 - a2 - |
                           c1 - c2 -c3
            b1 - b2 - b3 - |

    示例 1：
        A:     4 - 1 - |
                       8 - 4 - 5
        B: 5 - 0 - 1 - |
        输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
        输出：Reference of the node with value = 8
        输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
    
    示例 2：
        A: 0 - 9 - 1 - |
                       2 - 4
        B:         3 - |
        输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
        输出：Reference of the node with value = 2
        输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
    示例 3：
        A: 2 - 6 - 4
        B: 1 - 5
        输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
        输出：null
        输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
        解释：这两个链表不相交，因此返回 null。
    
    注意：
        1.如果两个链表没有交点，返回 null.
        2.在返回结果后，两个链表仍须保持原有的结构。
        3.可假定整个链表结构中没有循环。
        4.程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

 */
public class IntersectionOfTwoLinkedLists {
    /*
        双指针法
        两个指针遍历两个链表，当某一个到达末尾时，就从另一个链表头开始遍历
        当两个指针相遇时，表示遇到了第一个公共节点

        通俗理解，两人速度一致，路程一致，那么肯定同时到达终点，如果到达终点的最后一段路程相同
        那么一定会在某个点相遇后手拉手到达终点，相遇的点即为所求

        结果：
            1 ms, 99.99%
            41.1 MB, 74.78%
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        // 如果为空返回 null
        if (headA == null || headB == null)
            return null;
        // 记录两个头结点
        ListNode pA = headA;
        ListNode pB = headB;

        // 相等时相遇
        while (pA != pB) {
            // 如果到达末尾，则置为另一条链的头结点，否则往后移
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        // 返回相交的节点
        return pA;
    }

    /*
        hash 表法
        遍历链表 A 并将每个结点存储在哈希表中。
        然后检查链表 B 中的每一个结点 b 是否在哈希表中。若在，则 b 为相交结点

        结果：
            1237 ms, 5.19%
            41.9 MB, 11.56%
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        List<ListNode> list = new ArrayList<>();
        // 将 A 链表的所有节点放入 hash 表中
        while (headA != null) {
            list.add(headA);
            headA = headA.next;
        }
        // 如果表中不含 B 表的当前节点，则往后遍历
        while (headB != null && !list.contains(headB)) {
            headB = headB.next;
        }
        return headB;

    }
}
