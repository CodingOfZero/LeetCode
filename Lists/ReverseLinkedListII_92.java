package Lists;

/**
 * 将链接列表从位置m反转到n。
 */
public class ReverseLinkedListII_92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode Dummy=new ListNode(-1);
        ListNode pre=null;
        ListNode rTail=null;
        ListNode rHead=Dummy;
        Dummy.next=head;
        for(int i=1;i<m;i++){
            if(i==m-1)   rHead=head;
            head=head.next;
        }

        for(int i=0;i<n-m+1;i++){
            ListNode p=head;
            head=head.next;
            p.next=pre;
            if(pre==null) rTail=p;
            pre=p;
        }
        if(rTail!=null)
            rTail.next=head;
        rHead.next=pre;
        return Dummy.next;
    }
}
