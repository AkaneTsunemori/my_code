package code.hot.f;

import code.hot.ListNode;

public class a_27_142_linked_list_cycle_ii {
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null||head.next.next==null){
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(slow!=fast){
            if(fast.next==null||fast.next.next==null){
                return null;
            }
            slow=slow.next;
            fast = fast.next.next;
        }
        slow = head;
        while(slow!=fast){
            if(slow.next==null)return null;
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
