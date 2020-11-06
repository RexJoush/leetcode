package November20.day30PalindromeLinkedList;

import java.util.Stack;

/**
 * @author Joush
 * @time 2020.11.06
 */

/*

    回文链表
    https://leetcode-cn.com/problems/palindrome-linked-list/

    请判断一个链表是否为回文链表。

    示例 1:
        输入: 1->2
        输出: false
    示例 2:
        输入: 1->2->2->1
        输出: true

    进阶：
        你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

 */
public class PalindromeLinkedList {

    /*
        数一遍链表的节点数，将前半部分节点压入栈中，再与后半部分挨个比较，时间复杂度 O(n), 空间复杂度 O(n)
     */
    public boolean isPalindrome(ListNode head) {

        // 如果空或者单节点，返回 ture
        if (head == null || head.next == null){
            return true;
        }
        // 定义计数指针
        ListNode p = head;
        // 定义长度为0
        int length = 0;

        // 计数，查看有多少个节点
        while (p != null){
            length++;
            p = p.next;
        }
        // 定义标志位，节点为单数个还是偶数个
        boolean flag = length % 2 != 0;

        // 长度折半
        length /= 2;

        // 定义一个栈
        Stack<ListNode> stack = new Stack<>();

        // 将前半部分节点进栈
        while (length != 0){
            stack.push(head);
            head = head.next;
            length--;
        }
        // 如果奇数个节点，就往后挪一个
        if (flag){
            head = head.next;
        }

        // 弹栈开始比较，如果不一样就直接返回 false
        while (!stack.empty()){
            if (stack.pop().val != head.val){
                return false;
            } else {
                head = head.next;
            }
        }
        // 栈比较完了，是回文链表，返回 true
        return true;

    }
}
