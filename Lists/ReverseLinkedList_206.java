package Lists;

/**
 * 反转链表
 */
public class ReverseLinkedList_206 {

    public static ListNode reverseList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode p=head,q=head.next;
        head=head.next.next;
        p.next=null;
        q.next=p;
        while (head!=null){
            p=q;
            q=head;
            head=head.next;//先前发生的错误，将这句放到了下句之后
            q.next=p;
        }
        return q;
    }
    public static void main(String[] args) {
        int[] a={1,2,4};
        ListNode l1=new ListNode(1);
        ListNode headl1=l1;
        for(int i=1;i<a.length;i++){
            ListNode t=new ListNode(a[i]);
            l1.next=t;
            l1=l1.next;
        }

        ListNode res=reverseList(headl1);
        while (res!=null){
            System.out.println(res.val);
            res=res.next;
        }
    }
}
