package code.hot.g;

import code.hot.ListNode;

/**
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class a_46_2_add2_numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int  sum;
        ListNode pre = new ListNode();
        ListNode cur = pre;
        int ans = 0;
        while(l1!=null&&l2!=null){
            sum = l1.val+l2.val+ans;
            ans = sum/10;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            l1=l1.next;
            l2 = l2.next;
        }
        while(l1!=null){
            sum = l1.val+ans;
            ans = sum/10;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            l1=l1.next;
            l2 = l2.next;
        }
        while(l2!=null){
            sum = l2.val+ans;
            ans = sum/10;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            l1=l1.next;
            l2 = l2.next;
        }
        if(ans!=0){
            cur.next = new ListNode(ans);
        }
        return pre.next;
    }
}
