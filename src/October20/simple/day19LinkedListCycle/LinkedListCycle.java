package October20.simple.day19LinkedListCycle;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joush
 * @time 2020.10.26
 */

/*

    环形链表
    https://leetcode-cn.com/problems/linked-list-cycle/

    给定一个链表，判断链表中是否有环。
    如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    如果链表中存在环，则返回 true 。 否则，返回 false 。

    进阶：
        你能用 O(1)（即，常量）内存解决此问题吗？

    示例 1：
        输入：head = [3,2,0,-4], pos = 1
        输出：true
        解释：链表中有一个环，其尾部连接到第二个节点。
    示例 2：
        输入：head = [1,2], pos = 0
        输出：true
        解释：链表中有一个环，其尾部连接到第一个节点。
    示例 3：
        输入：head = [1], pos = -1
        输出：false
        解释：链表中没有环。


 */
public class LinkedListCycle {

    // 哈希表法
    public boolean hasCycle(ListNode head) {

        // 定义一个哈希 set，非重复
        Set<ListNode> set = new HashSet<>();

        /*
            遍历一遍，到某个节点，就将之放入表中，当出现重复时，就说明出现了环，return true
            遍历完整个表不存在重复遍历时，即没有环
         */
        while (head != null){

            // hashset 的 add 函数，返回一个 boolean 值，是否添加成功
            if (!set.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    // 快慢指针法
    public boolean hasCycle2(ListNode head) {

        // 空表，或仅有头结点，返回 false
        if (head == null || head.next == null){
            return false;
        }

        /*
            快慢指针法，两个指针，一个每次挪一个，一个每次挪两个。
            如果存在环，那么快慢指针总会碰到一起，
            如果不存在环，那么快指针则一直比慢指针快
         */

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast){
            // 快指针到了表尾
            if (fast == null || fast.next == null){
                return false;
            }
            // 两指针往后移
            else {
                fast = fast.next.next;
                slow = slow.next;
            }
        }
        // 快慢指针相遇了
        return true;
    }

}
