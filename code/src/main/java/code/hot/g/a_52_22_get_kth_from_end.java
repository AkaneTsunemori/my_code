package code.hot.g;

import code.hot.ListNode;

public class a_52_22_get_kth_from_end {
    public ListNode getKthFromEnd(ListNode head, int k){
        ListNode fast = head;
        ListNode slow = head;
        while(--k>0){
            fast = fast.next;
        }
        while(fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
