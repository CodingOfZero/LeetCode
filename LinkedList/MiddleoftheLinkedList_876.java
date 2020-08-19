package Lists;

/**
 * 返回链表的中间结点
 */
public class MiddleoftheLinkedList_876 {
    public ListNode middleNode(ListNode head) {
        int len=0;
        ListNode p=head;
        while (p!=null) {
            len++;
            p=p.next;
        }
        for(int i=0;i<len/2;i++){
            head=head.next;
        }
        return head;
    }
}
