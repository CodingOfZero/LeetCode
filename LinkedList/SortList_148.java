package Lists;

/**
 * 链表排序
 * 思路：归并排序
 */
public class SortList_148 {
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) return head;//特殊情况
        int len=0;
        ListNode p=head;
        while (p!=null){
            len++;
            p=p.next;
        }
        return sortListHelper(head,len);
    }
    private ListNode sortListHelper(ListNode p, int len) {
        int mi=len/2;
        if(mi==0){
            p.next=null;
            return p;
        }
        ListNode q=p;
        for(int i=0;i<mi;i++)
            q=q.next;
        ListNode node1=sortListHelper(p,mi);
        ListNode node2=sortListHelper(q,len-mi);//对前后子列表分别排序
        return mergeList(node1,node2);
    }
    private ListNode mergeList(ListNode p,ListNode q){
        ListNode Dummy=new ListNode();
        ListNode current=Dummy;
        while (p!=null&&q!=null){
            if(p.val<=q.val){
                current.next=p;
                current=current.next;
                p=p.next;
            }else{
                current.next=q;
                current=current.next;
                q=q.next;
            }
        }
        if(p!=null){
            current.next=p;
            current=current.next;
            p=p.next;
        }
        if(q!=null){
            current.next=q;
            current=current.next;
            q=q.next;
        }
        return Dummy.next;
    }
}
