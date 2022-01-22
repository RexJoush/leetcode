package datastructure;

/**
 * @author Rex Joush
 * 链表的节点结构
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
