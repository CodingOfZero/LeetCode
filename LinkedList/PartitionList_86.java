package Lists;

/**
 * 题目：
 * 给定一个链接列表和一个值 x，对其进行分区，使所有小于 x 的节点排在大于或等于 x 的节点之前。
 * 你应该保留两个分区中每个节点的原始相对顺序。
 *
 */
public class PartitionList_86 {
    public ListNode partition(ListNode head, int x) {
        ListNode leftDummyHead=new ListNode();
        ListNode rightDummyHead=new ListNode();
        ListNode left=leftDummyHead;
        ListNode right=rightDummyHead;
        while (head!=null){//分为小于x与大于或等于x的链表
            if(head.val<x){
                left.next=head;
                left=left.next;
                head=head.next;
                left.next=null;
            }else {
                right.next=head ;
                right=right.next;
                head=head.next;
                right.next=null;
            }
        }
        //将大于等于x组成的链表连接到小于x的后面
        left.next=rightDummyHead.next;
        return leftDummyHead.next;
    }
    public ListNode partition_1(ListNode head, int x) {
        if(head==null) return null;
        ListNode less=new ListNode();
        ListNode high=new ListNode();
        ListNode pHigh=high;
        ListNode result=less;
        while (head!=null){//分为小于x与大于或等于x的链表
            ListNode q=head;
            head=head.next;
            q.next=null;
            if(q.val<x){
                less.next=q;
                less=less.next;
            }else {
                pHigh.next=q;
                pHigh=pHigh.next;
            }
        }
        //将大于等于x组成的链表连接到小于x的后面
        less.next=high.next;
        return result.next;
    }
}
