package Lists;

/**
 * 将列表向右旋转k位，其中k为非负数。
 * 思路：首先遍历一遍，求出链表长度，接着求出实际旋转位数，然后定义两个指针，它们
 * 之间相差k位，遍历整个链表，找到要断链的位置，进行处理。
 */
public class RotateList_61 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) return head;//特殊情况，是否为空
        ListNode p=head;
        int len=0;
        while (p!=null){
            len++;
            p=p.next;
        }
        k=k%len;
        if(k==0||k==len) return head;//边界条件
        //定义两个指针,它们之间相差k个
        ListNode q=head;
        p=head;
        for(int i=0;i<k;i++){
            q=q.next;
        }
        while (q.next!=null){
            p=p.next;
            q=q.next;
        }
        ListNode temp=p.next;
        p.next=null;
        q.next=head;
        return temp;
    }
}
