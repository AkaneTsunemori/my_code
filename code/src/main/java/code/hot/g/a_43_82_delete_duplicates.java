package code.hot.g;

import code.hot.ListNode;

public class a_43_82_delete_duplicates {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode unique = pre;
        ListNode cur = head;
        while(cur!=null){
            ListNode start = cur;
            while(cur.next!=null&&cur.val==cur.next.val){
                cur= cur.next;
            }
            ListNode end = cur;
            if(start!=end){
                unique.next = end.next;
                cur = end.next;
            }else {
                unique.next = end;
                cur = end.next;
                unique = unique.next;
            }
        }
        return pre.next;
    }
}
