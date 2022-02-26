package y2022.m02February.day486RotateList;

import datastructure.ListNode;

import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.02.05
 */

/*
    旋转链表
    https://leetcode-cn.com/problems/rotate-list/

    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动k个位置。

    示例 1：
                   1 -> 2 -> 3 -> 4 -> 5
        rotate 1   5 -> 1 -> 2 -> 3 -> 4
        rotate 2   4 -> 5 -> 1 -> 2 -> 3

        输入：head = [1,2,3,4,5], k = 2
        输出：[4,5,1,2,3]
    示例 2：
                   0 -> 1 -> 2
        rotate 1   2 -> 0 -> 1
        rotate 2   1 -> 0 -> 2
        rotate 3   0 -> 1 -> 2
        rotate 4   2 -> 0 -> 1
        输入：head = [0,1,2], k = 4
        输出：[2,0,1]

    提示：
        链表中节点的数目在范围 [0, 500] 内
        -100 <= Node.val <= 100
        0 <= k <= 2 * 10^9

 */
public class RotateList {

    public static void main(String[] args) {

        System.out.println(0 % 5);
    }

    /*
        将链表接起来，移动 k 次后，断开即可
        结果：
            0 ms, 100.00%
            41.2 MB, 5.06%
     */
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 将此节点移动到链表尾部
        ListNode p = head;
        // 计算有几个节点
        int n = 1;

        // 找到链尾，并接到头
        while (p.next != null) {
            p = p.next;
            n++;
        }
        // 计算头节点移动多少次
        int count = n - k % n;
        if (count == n) {
            return head;
        }

        // 接到头部
        p.next = head;
        while (count > 0) {
            // 将头节点往后移动 count 次
            p = p.next;
            count--;
        }
        // 把链断开
        ListNode ans = p.next;
        p.next = null;
        return ans;
    }
}
