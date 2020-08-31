package Lists;

/**
 * 将位置为奇数的结点放在位置是偶数的结点之前，
 * 注意：指的是结点位置的奇偶而非结点值的奇偶
 */
public class OddEvenLinkedList_328 {
    public ListNode oddEvenList(ListNode head) {
        ListNode Odd=new ListNode();
        ListNode Even=new ListNode();
        ListNode Dummy=Even;
//        if(head.next==null||head.next.next==null) return head;
        ListNode p=head;
        int i=1;
        while (p!=null){
            ListNode q=p;
            p=p.next;
            q.next=null;
            if(i==1){
                Odd.next=q;
                Odd=Odd.next;
                i=0;
            }else {
                Even.next=q;
                Even=Even.next;
                i=1;
            }
        }
        Odd.next=Dummy.next;
        Dummy.next=null;
        return head;
    }
}
