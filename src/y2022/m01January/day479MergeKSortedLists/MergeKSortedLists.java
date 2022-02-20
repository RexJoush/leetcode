package y2022.m01January.day479MergeKSortedLists;

import datastructure.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Rex Joush
 * @time 2022.01.29
 */

/*
    合并K个升序链表
    https://leetcode-cn.com/problems/merge-k-sorted-lists/

    给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。

    示例 1：
        输入：lists = [[1,4,5],[1,3,4],[2,6]]
        输出：[1,1,2,3,4,4,5,6]
        解释：链表数组如下：
        [
          1->4->5,
          1->3->4,
          2->6
        ]
        将它们合并到一个有序链表中得到。
        1->1->2->3->4->4->5->6
    示例 2：
        输入：lists = []
        输出：[]
    示例 3：
        输入：lists = [[]]
        输出：[]

    提示：
        k == lists.length
        0 <= k <= 10^4
        0 <= lists[i].length <= 500
        -10^4 <= lists[i][j] <= 10^4
        lists[i] 按 升序 排列
        lists[i].length 的总和不超过 10^4

 */
public class MergeKSortedLists {

    /*
        方法三：优先队列（小根堆）
        创建一个优先队列，此队列保证每次队头元素为当前队列的最小元素
        因此每次取出最小元素接入最终结果链表即可
        结果：
            6 ms, 33.55%
            42.9 MB, 22.98%
     */
    public ListNode mergeKLists3(ListNode[] lists) {

        // 创建优先队列，此处优先队列传入对象时，需要传入 Comparator 对象帮助比较，lambda 写法
        PriorityQueue<ListNode> priorityQueue
                = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

        /*
        上面为 lambda 写法，下面这两种也可
            PriorityQueue<ListNode> priorityQueue
                    = new PriorityQueue<>(new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val - o2.val;
                }
            });

            PriorityQueue<ListNode> priorityQueue
                = new PriorityQueue<>((ListNode o1, ListNode o2) -> o1.val - o2.val);
         */


        // 将所有链表的队头结点放入优先队列，此时优先队列会将顺序排好
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.offer(node);
            }
        }

        // 结果链表
        ListNode head = new ListNode(0);
        ListNode p = head;

        // 如果优先队列不空，就一直循环
        while (!priorityQueue.isEmpty()) {
            // 取出队头元素，接入结果链表
            ListNode poll = priorityQueue.poll();
            p.next = poll;
            p = p.next;

            // 如果当前链表没空，将此链表的后一个节点入优先队列
            if (poll.next != null) {
                priorityQueue.offer(poll.next);
            }
        }
        // 返回结果即可
        return head.next;
    }

    /*
        方法二：分治法
        优化方法一的暴力合并
        考虑，每次合并两条，假如共有 8 条链表，则
            1 2 合并得到 A，3 4 合并得到 B，5 6 合并得到 C，7 8 合并得到 D
        继续合并
            A B 合并得到 M，C D 合并得到 N
        继续合并 M N 即可得到最终结果
        结果：
            1 ms, 100.00%
            43.2 MB, 15.60%
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        // 调用递归分支拼接即可
        return merge(lists, 0, lists.length - 1);
    }


    /*
        方法一：暴力合并，一次合并一条
        考虑，每次合并两条链表，最终合并所有链表
        结果：
            186 ms, 10.73%
            43.4 MB, 7.79%
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 空列表返回空
        if (lists.length == 0) {
            return null;
        }
        // 长度为一直接返回
        if (lists.length == 1) {
            return lists[0];
        }

        // 将前两个链表合并为一个
        ListNode result = mergeTwoList(lists[0], lists[1]);

        // 将剩余的链表一个一个合并
        for (int i = 2; i < lists.length; i++) {
            result = mergeTwoList(result, lists[i]);
        }
        // 返回结果
        return result;

    }


    /**
     * 合并两个链表
     *
     * @param lists 链表数组
     * @param l     左链表
     * @param r     右链表
     * @return 合并的结果
     */
    public ListNode merge(ListNode[] lists, int l, int r) {
        // 左右节点相同，直接返回
        if (l == r) {
            return lists[l];
        }
        // 左大于右，说明合并完毕，递归结束
        if (l > r) {
            return null;
        }
        // 从中间分开，递归合并两侧链表
        int mid = (l + r) >> 1;

        // 递归拼接左节点到中间的链表，以及右节点到中间的链表
        return mergeTwoList(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 合并两个升序链表为一个升序链表
     *
     * @param first  第一个升序链表
     * @param second 第二个升序链表
     * @return 合并完成的升序链表的头节点
     */
    public ListNode mergeTwoList(ListNode first, ListNode second) {
        ListNode head = new ListNode();
        ListNode p = head;

        // 顺序遍历两个链表，尾插法续表即可
        while (first != null && second != null) {
            if (first.val < second.val) {
                p.next = first;
                first = first.next;
            } else {
                p.next = second;
                second = second.next;
            }
            p = p.next;
        }

        // 拼接未完成的链
        while (first != null) {
            p.next = first;
            p = p.next;
            first = first.next;
        }

        while (second != null) {
            p.next = second;
            p = p.next;
            second = second.next;
        }
        // 返回头节点
        return head.next;
    }
}
