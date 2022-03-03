package y2022.m02February.day490LinkedListCycleII;

import datastructure.ListNode;

import java.util.HashSet;

/**
 * @author Rex Joush
 * @time 2022.02.09
 */

/*
    环形链表 II
    https://leetcode-cn.com/problems/linked-list-cycle-ii/

    给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回null。
    如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
    注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    不允许修改 链表。
    
    示例 1：
    
        输入：head = [3,2,0,-4], pos = 1
        输出：返回索引为 1 的链表节点
        解释：链表中有一个环，其尾部连接到第二个节点。
    示例2：
    
        输入：head = [1,2], pos = 0
        输出：返回索引为 0 的链表节点
        解释：链表中有一个环，其尾部连接到第一个节点。
    示例 3：
    
    
        输入：head = [1], pos = -1
        输出：返回 null
        解释：链表中没有环。
    
    提示：
        链表中节点的数目范围在范围 [0, 104] 内
        -105 <= Node.val <= 105
        pos 的值为 -1 或者链表中的一个有效索引

 */
public class LinkedListCycleII {

    /*
        方法二：快慢指针法
                    ____o__
                   |       |     s-x 即，从链表头部到入环点为 a
                   |       |     x-o 即，从入环点到相遇点为 b
           s------x|       |     o-x 即，从相遇点再到入环点为 c
                   |_______|

            考虑，当慢指针走到 x 时，快指针已经入环。
            当两个指针从 x 同时出发，慢指针绕环一圈，则快指针绕环两圈，两指针一定会相遇。
            所以，此时快指针已经在环中，则两指针一定会在 慢指针入环的第一圈 相遇

            推导过程
                1.快指针的路程时慢指针的两倍
                2.慢指针走过的距离为
                    a + b
                3.快指针走过的距离为
                    a + n(b+c) + b, 即快指针走过了 n 圈后，又走过了 b 与 慢指针相遇
                可得以下等式
                    2(a + b) = a + n(b + c) + b
               =>   a = c + (n - 1)(b + c)
               因此，此等式说明，c 与 a 相差 n 圈的环长
               即，当 快慢指针相遇时，从此位置和链表头部开始走，必定会在入环点相遇

        结果：
            0 ms, 100.00%
            41.8 MB, 13.79%
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            // 当快慢指针相遇时，新指针开始往后走，头指针也往后走，相遇时即为入环点
            if (fast == slow) {
                ListNode temp = slow;
                // 相遇时，即为入环点
                while (temp != head) {
                    temp = temp.next;
                    head = head.next;
                }
                // 返回入环点即可
                return temp;
            }
        }
        return null;
    }

    /*
        方法一：哈希表法
            遍历链表，将遇到的节点放入哈希表，如果再次遇到，则返回该节点，否则返回 null
        结果：
            3 ms, 20.98%
            42 MB, 6.80%
     */
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();

        while (head != null) {
            // 遇到包含的，直接返回
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}
