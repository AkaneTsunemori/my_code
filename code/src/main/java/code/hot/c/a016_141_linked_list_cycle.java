package code.hot.c;

import code.hot.ListNode;

public class a016_141_linked_list_cycle {
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow!=fast){
            if(fast.next==null||fast.next.next==null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        return true;

    }
}
