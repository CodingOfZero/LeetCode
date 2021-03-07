package Lists;

/**
 * 链表的每两个结点进行交换
 * 思路，先拆成两条单链，再进行组合
 */
public class SwapNodesinPairs_24 {
    //新建头结点，进行处理，操作简单
    public ListNode swapPairs(ListNode head) {
        if(head==null) return head;
        ListNode root=new ListNode(-1);
        root.next=head;
        ListNode pre=root;
        ListNode cur=head;
        ListNode post=head.next;
        while (cur!=null&&post!=null){
            //swap
            post=post.next;
            cur.next.next=cur;
            pre.next=cur.next;
            cur.next=post;

            //next
            pre=pre.next;
            cur=pre.next;

            post=post==null?null:post.next;
            cur=cur==null?null:cur.next;
            pre=pre.next;
        }
        return root.next;
    }

    public ListNode swapPairs_1(ListNode head) {
        if(head==null||head.next==null) return head;
        //由奇数与偶数不同位置组成的两条链表
        ListNode p=head,pHead=head;
        ListNode q=head.next,qHead=head.next;
        while (p!=null&&q!=null){
            p.next=q.next;
            p=p.next;
            if(p!=null)
                q.next=p.next;
            q=q.next;
        }
        //组合两条链
        ListNode pre=null;
        ListNode result=qHead;
        while (pHead!=null&&qHead!=null){
            ListNode p1=pHead;
            pHead=pHead.next;

            p1.next=qHead.next;
            qHead.next=p1;
            pre=p1;//记录前驱
            qHead=qHead.next.next;

        }
        if(pHead!=null) pre.next=pHead;
        return result;
    }

}
