package y2022.m02February.day491SortList;

import datastructure.ListNode;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Rex Joush
 * @time 2022.02.10
 */

/*
    排序链表
    https://leetcode-cn.com/problems/sort-list/
    
    你链表的头结点 head，请将其按 升序 排列并返回 排序后的链表 。

    示例 1：
            4 --> 2 --> 1 --> 3
            1 --> 2 --> 3 --> 4
        输入：head = [4,2,1,3]
        输出：[1,2,3,4]
    示例 2：
            -1 --> 5 --> 3 --> 4 --> 0
            -1 --> 0 --> 3 --> 4 --> 5
        输入：head = [-1,5,3,4,0]
        输出：[-1,0,3,4,5]
    示例 3：
        输入：head = []
        输出：[]


    提示：
        链表中节点的数目在范围[0, 5 * 10^4]内
        -10^5 <= Node.val <= 10^5

    进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

 */
public class SortList {

    /*
        方法三：自底向上的归并排序
            自顶向下的归并排序需要划分子链表，自底向上的归并排序则认为已经划分完成
            只需判断每次合并的边界即可，此时可以使得空间复杂度降为 O(1)
        结果：
            8 ms, 41.83%
            49 MB, 21.47%
     */
    public ListNode sortList3(ListNode head) {

        if (head == null) {
            return null;
        }

        // 计算节点个数
        int n = 0;
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }

        ListNode dummyNode = new ListNode(0, head);
        dummyNode.next = head;

        // 每次合并子链表，每循环一次，长度加长为之前的两倍，当长度超过 n 时，结束循环
        for (int subLength = 1; subLength < n; subLength <<= 1) {
            ListNode prev = dummyNode;
            ListNode curr = dummyNode.next; // 记录链表被拆分的位置

            while (curr != null) { // 被拆分完毕之前

                // 1.拆分长度为 subLength 的链表 1
                ListNode head1 = curr; // 第一个链表的头节点
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }

                // 2.拆分长度为 subLength 的链表 2
                ListNode head2 = curr.next; // 第二个链表的头
                curr.next = null; // 断开第一个链表的链尾
                curr = head2; // 赋值第二个链表的头部
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                // 3.断开，第二个链表的 next 链接
                ListNode next = null;
                if (curr != null) {
                    next = curr.next; // 记录结束位置
                    curr.next = null; // 断开
                }

                // 4.合并两个 subLength 长度的链表
                prev.next = merge(head1, head2); // prev.next 指向排好序链表的头部

                // 将 prev 移动到 subLength * 2 的位置
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next; // 赋值 curr 为拆分的两个链表的结束位置

            }
        }

        return dummyNode.next;
    }

    /*
        方法二：自顶向下的归并排序
            划分两个子链表，对两个子链表进行排序，合并两个有序的子链表
            但此空间消耗为 O(log(n))，递归的栈空间。因此依然不满足题意
        结果：
            6 ms, 81.23%
            48.6 MB, 34.07%
     */
    public ListNode sortList2(ListNode head) {
        return sortList(head, null);
    }

    /**
     * 将链表进行头尾之间的值排序
     *
     * @param head 头节点
     * @param tail 尾节点
     * @return 排序完毕后的头节点
     */
    private ListNode sortList(ListNode head, ListNode tail) {

        // 空节点，返回 null
        if (head == null) {
            return null;
        }
        // 仅一个节点，置空 next，返回
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        //
        ListNode slow = head;
        ListNode fast = head;

        // 快慢指针，快指针到尾部时，慢指针在中点位置
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode mid = slow;

        // 合并两个子链表
        return merge(sortList(head, mid), sortList(mid, tail));
    }

    /**
     * 合并两个有序链表
     *
     * @param head1 链表 1 的头节点
     * @param head2 链表 2 的头节点
     * @return 合并完毕的头节点
     */
    private ListNode merge(ListNode head1, ListNode head2) {

        ListNode dummyNode = new ListNode(0);
        ListNode p = dummyNode;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                p.next = head1;
                head1 = head1.next;
            } else {
                p.next = head2;
                head2 = head2.next;
            }
            p = p.next;
        }

        if (head1 == null) p.next = head2;
        if (head2 == null) p.next = head1;
        return dummyNode.next;
    }

    /*
        方法一：使用数组
            将所有节点放入数组中，快速排序后，恢复链表
        结果：
            11 ms, 21.49%
            49.4 MB, 9.08%
     */
    public ListNode sortList(ListNode head) {

        if (head == null) {
            return null;
        }

        ArrayList<ListNode> list = new ArrayList<>();

        // 将节点放入数组
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        // 排序
        list.sort(Comparator.comparingInt(o -> o.val));

        // 恢复链表
        head = list.get(0);
        ListNode res = head;

        for (int i = 1; i < list.size(); i++) {
            head.next = list.get(i);
            head = head.next;
        }
        head.next = null;

        return res;
    }
}
