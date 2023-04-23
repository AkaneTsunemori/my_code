package code.hot.g;

import code.hot.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过递归的方式解决
 */
public class a_41_148_sort_list {
    public ListNode sortList(ListNode head) {
        ListNode pre = head;
        while (head.next != null) {
            head = head.next;
        }
        ListNode tail = head;
        return sort(pre, tail);
    }

    public ListNode sort(ListNode head, ListNode tail) {
        if (head == tail) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        ListNode midNext = mid.next;
        mid.next = null;
        ListNode node1 = sort(head, mid);
        ListNode node2 = sort(midNext, tail);
        return merge(node1, node2);
    }

    public ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        if (node1.val > node2.val) {
            node2.next = merge(node1, node2.next);
            return node2;
        } else {
            node1.next = merge(node1.next, node2);
            return node1;
        }
    }

}
