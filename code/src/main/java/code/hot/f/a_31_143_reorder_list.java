package code.hot.f;

import code.hot.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class a_31_143_reorder_list {
    public void reorderList(ListNode head) {
        Map<Integer,ListNode> map =new HashMap<>();
        ListNode cur = head;
        int index = -1;
        while(cur!=null){
            map.put(++index,cur);
            cur = cur.next;
        }
        int l = 0;
        while(l<index){
            ListNode left = map.get(l);
            ListNode insert = map.get(index);
            ListNode rigth = left.next;
            left.next = insert;
            insert.next = rigth;
            l++;
            index--;
        }
        map.get(l).next=null;
    }
}
