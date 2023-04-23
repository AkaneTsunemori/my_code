package code.hot.f;

import code.hot.ListNode;

public class a_32_19_remove_Nth_from_end {
    public ListNode removeNthFromEnd(ListNode head,int n){
        ListNode pre = new ListNode();
        pre.next = head;
        int count = 1;
        while(count!=n){
            head = head.next;
            count++;
        }
        ListNode bres = pre;
        while(head.next!=null){
            head=head.next;
            bres= bres.next;
        }
        bres.next = bres.next.next;
        return pre.next;
    }
}
