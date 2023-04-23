package a100;

public class Problem_0002_AndTwoNumbers {
    /**
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int valL1 ;
        int valL2 ;
        int carray = 0;
        int curVal;
        ListNode pre = new ListNode(0);
        ListNode res = pre;
        while (l1 != null || l2 != null) {
            valL1 = l1 == null ? 0 : l1.val;
            valL2 = l2 == null ? 0 : l2.val;
            int sum = valL1 + valL2 + carray;
            carray = (sum) / 10;
            curVal = (sum) % 10;
            ListNode curNode = new ListNode(curVal);
            pre.next = curNode;
            pre = curNode;
            l1 =l1==null?null: l1.next;
            l2 =l2==null?null: l2.next;
        }
        if(carray!=0){
            pre.next = new ListNode(carray);
        }
        return res.next;

    }
    class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }

}
