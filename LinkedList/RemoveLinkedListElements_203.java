package Lists;

/**
 * 在链表中去重
 */
public class RemoveLinkedListElements_203 {
    public ListNode removeElements(ListNode head, int val) {
        while (head!=null&&head.val==val) head=head.next;
        if(head==null) return head;
        ListNode pre=head;
        ListNode post=head.next;
        while (post!=null){
            if(post.val==val){
                pre.next=post.next;
                post=post.next;
            }else{
                post=post.next;
                pre=pre.next;
            }
        }
        return head;
    }
}
