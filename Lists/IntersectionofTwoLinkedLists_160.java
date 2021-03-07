package Lists;

/**
 * 如果存在，则返回两个链表第一个汇聚的结点
 */
public class IntersectionofTwoLinkedLists_160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode result=null;
        if(headA==null||headB==null) return result;
        int lenA=0;
        int lenB=0;
        int dis=0;
        ListNode tempA=headA;
        ListNode tempB=headB;
        while(tempA!=null){
            lenA++;
            tempA=tempA.next;
        }
        while (tempB!=null){
            lenB++;
            tempB=tempB.next;
        }
        if(lenA<lenB){
            dis=lenB-lenA;
            ListNode temp;
            temp=headB;
            headB=headA;
            headA=temp;
        }else
            dis=lenA-lenB;
        for(int i=0;i<dis;i++){
            headA=headA.next;
        }
        while (headA!=null&&headB!=null&&headA!=headB){
            headA=headA.next;
            headB=headB.next;
        }
        result=headA;
        return result;
    }
}
