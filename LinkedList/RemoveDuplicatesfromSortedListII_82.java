package Lists;

/**
 *去除所有重复数字
 */

public class RemoveDuplicatesfromSortedListII_82 {
    public ListNode deleteDuplicates_1(ListNode head) {
        ListNode preHead =new ListNode();
        preHead.next=head;
        ListNode p=preHead;
        while (p.next!=null){
            ListNode q=p.next.next;
            while(q!=null&&q.val==p.next.val)
                q=q.next;
            if(p.next.next==q){//如果q未发生变化，则说明p指向的节点只出现一次
                p=p.next;
            }else{
                p.next=q;
            }
        }
        return preHead.next;
    }
    //使用这种
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return head;
        ListNode preHead =new ListNode(0,head);
        ListNode pre=preHead;
        ListNode mid=head;
        ListNode cur=head.next;
        while(cur!=null){
            int count=0;
            while (cur!=null&&cur.val==mid.val){
                count++;
                cur=cur.next;
            }
            if(count>0){
                pre.next=cur;
                mid=cur;
                if(cur!=null) cur=cur.next;
            }else{
                pre=pre.next;
                mid=mid.next;
                if(cur!=null)cur=cur.next;
            }
        }
        return preHead.next;
    }
}
