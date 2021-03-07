package Lists;

/**
 * 删除链表的第k个节点
 */
public class RemoveNthNodeFromEndofList_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null||head.next==null||n==0) return null;
        ListNode front=head;
        ListNode last=head;
        for(int i=1;i<n;i++){
            if(front!=null)
                front=front.next;
            else
                return null;
        }
        if(front==last){
            front=front.next;
            while (front.next!=null){
                front=front.next;
                last=last.next;
            }
            last.next=null;
        }else{
            while (front.next!=null){
                front=front.next;
                last=last.next;
            }
            if(last.next!=null){
            last.val=last.next.val;
            last.next=last.next.next;
            }
        }
        return head;
    }

}
