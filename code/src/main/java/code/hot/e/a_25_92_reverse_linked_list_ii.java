package code.hot.e;

import code.hot.ListNode;

public class a_25_92_reverse_linked_list_ii {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        reverseBetween(head,1,4);

    }
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode hair = new ListNode();
        hair.next = head;
        ListNode pre = hair;
        int count = right - left;
        while (--left > 0) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode end = cur;
        while (count-- >= 0) {
            end = end.next;
        }
        ListNode nextL = end.next;
        ListNode temp = new ListNode();
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = temp;
            temp = cur;
            cur = next;
        }
        pre.next.next = nextL;
        pre.next = cur;
        return hair.next;
    }
}
