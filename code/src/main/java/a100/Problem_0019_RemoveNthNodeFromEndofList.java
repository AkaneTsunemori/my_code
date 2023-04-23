package a100;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 */
public class Problem_0019_RemoveNthNodeFromEndofList {

	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			n--;
			if (n == -1) {
				pre = head;
			}
			if (n < -1) {
				pre = pre.next;
			}
			cur = cur.next;
		}
		if (n > 0) {
			return head;
		}
		if (pre == null) {
			return head.next;
		}
		pre.next = pre.next.next;
		return head;
	}

}
