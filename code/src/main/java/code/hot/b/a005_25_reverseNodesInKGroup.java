package code.hot.b;


import code.hot.ListNode;

public class a005_25_reverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp =head;
        for(int i = 2;i<=5;++i){
            head.next = new ListNode(i);
            head = head.next;
        }
        ListNode listNode = reverseKGroup(temp, 2);
        System.out.println("===");
    }
    public static ListNode reverseKGroup(ListNode head, int k) {

//        用四个指针记录四个位置
        ListNode pre = new ListNode(0);
        ListNode resPre = pre;
        ListNode start = head;
        ListNode end = start;
        ListNode next = null;
        while(end!=null){
            for(int i = 1;i<k;++i){
                end = end.next;
                if(end==null)return resPre.next;
            }
            next = end.next;
            ListNode[] listNodes = reverseSubGroup(start, k);
            start = listNodes[0];
            end = listNodes[1];
            pre.next = start;
            end.next = next;
            pre = end;
            start = next;
            end = start;
        }
        return resPre.next;


    }

    public static ListNode[] reverseSubGroup(ListNode head, int k) {
        ListNode pre = null;
        ListNode hair = head;
        while (k-- > 0) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return new ListNode[]{pre,hair};
    }


}
