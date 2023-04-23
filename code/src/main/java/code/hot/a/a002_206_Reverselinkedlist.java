package code.hot.a;


import code.hot.ListNode;

/***
 * 翻转链表, 没什么可说的,so easy
 */

public class a002_206_Reverselinkedlist {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head!=null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
