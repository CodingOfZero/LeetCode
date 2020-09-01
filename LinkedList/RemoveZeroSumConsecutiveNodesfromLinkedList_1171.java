package Lists;

/**
 * 删除和为0的结点
 */
public class RemoveZeroSumConsecutiveNodesfromLinkedList_1171 {

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode Dummy=new ListNode();
        Dummy.next=head;
        ListNode pre=Dummy;

        while(head!=null){
            int sum=0;
            ListNode p=head;
            while (p!=null){
                sum+=p.val;
                if(sum==0){
                    pre.next=p.next;
                    head=pre.next;
                    break;
                }
                p=p.next;
            }
            if(p==null){
                pre=pre.next;
                head=head.next;
            }
        }
        return Dummy.next;
    }
}
