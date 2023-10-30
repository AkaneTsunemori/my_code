package AUG.linked_list;

import code.hot.ListNode;


/**
 *          不可写成这样, 而需要把null作为一个有效的Node来看待,如下写法存在一个节点走了两步的情况导致部分案例无法通过
 *         while (pointerA!=pointerB) {
 *             if (pointerA == null) {
 *                 pointerA = headB;
 *             }
 *             if (pointerB == null) {
 *                 pointerB = headA;
 *             }
 *             pointerA = pointerA.next;
 *             pointerB = pointerB.next;
 *         }
 */
public class getIntersectionNode_160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        while (pointerA != pointerB) {
            if (pointerA == null) {
                pointerA = headB;
            } else {
                pointerA = pointerA.next;
            }
            if (pointerB == null) {
                pointerB = headA;
            } else {
                pointerB = pointerB.next;
            }
        }
        return pointerA;
    }
}
