package code.hot.d;

import code.hot.ListNode;

public class a_022_160_intersection_node {
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        ListNode exchangeA = headB;
        ListNode exchangeB = headA;

        while(headA!=headB){
            if(headA==null){
                headA = exchangeA;
            }
            if(headB==null){
                headB = exchangeB;
            }
            headA = headA.next;
            headB = headB.next;

        }
        return headA;
    }
}
