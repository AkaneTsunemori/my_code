package code.hot.e;

import code.hot.ListNode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class a_24_23merge_k_sorted_lists {

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null){
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((o1,o2)->o1.val-o2.val);
        for (ListNode listNode : lists) {
            if(listNode!=null)
                priorityQueue.add(listNode);
        }
        if(priorityQueue.isEmpty()){
            return null;
        }
        ListNode pre = new ListNode();
        ListNode res = pre;
        while(!priorityQueue.isEmpty()){
            ListNode poll = priorityQueue.poll();
            pre.next = poll;
            if(poll.next!=null){
                priorityQueue.add(poll.next);
            }
            pre = poll;
        }
        return res.next;
    }
}
