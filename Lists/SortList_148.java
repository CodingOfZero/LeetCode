package Lists;

/**
 * 链表排序
 * 思路：归并排序
 */
public class SortList_148 {
    /*public ListNode sortList(ListNode head) {
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
    }*/

    /**
     * 自顶向下，递归版的归并排序
     * 时间复杂度为O(nlogn)，空间为O(logn)
     */
    public ListNode sortList(ListNode head) {
        return sortListHelper(head,null);
    }
    private ListNode sortListHelper(ListNode head,ListNode tail){
        if(head==null) return null;
        //只有一个节点
        if(head.next==tail){
            head.next=null;
            return head;
        }
        ListNode fast=head,slow=head;
        while(fast!=tail){
            slow=slow.next;
            fast=fast.next;
            if(fast!=tail){
                fast=fast.next;
            }
        }
        ListNode mid=slow;
        ListNode left=sortListHelper(head,mid);
        ListNode right=sortListHelper(mid,tail);
        return merge(left,right);
    }
    //合并两个有序链表
    private ListNode merge(ListNode p,ListNode q){
        ListNode dummy=new ListNode();
        ListNode cur=dummy;
        while(p!=null&&q!=null){
            ListNode tmp;
            if(p.val<=q.val){
                tmp=p;
                p=p.next;
            }else{
                tmp=q;
                q=q.next;
            }
            tmp.next=null;
            cur.next=tmp;
            cur=cur.next;
        }
        if(p!=null){
            cur.next=p;
        }
        if(q!=null){
            cur.next=q;
        }
        return dummy.next;
    }

    /**
     * 以sublen为排序链表的长度,每段长度为sublen，最后一段小于等于sublen
     * 时间复杂度为O(nlogn) 空间为O(1)
     */
    public ListNode sortListLessSpace(ListNode head) {
        if(head==null||head.next==null) return head;
        int len=0;
        ListNode p=head;
        while(p!=null){
            len++;
            p=p.next;
        }
        ListNode dummy=new ListNode();
        dummy.next=head;
        for(int subLen=1;subLen<len;subLen=subLen*2){
            ListNode curr=dummy.next;
            ListNode prev=dummy;
            while (curr!=null){
                ListNode head1=curr;
                for(int i=1;i<subLen&&curr.next!=null;i++){
                    curr=curr.next;
                }

                ListNode head2=curr.next;
                curr.next=null;
                curr=head2;
                for(int i=1;i<subLen&&curr!=null&&curr.next!=null;i++){
                    curr=curr.next;
                }
                ListNode next=null;
                if(curr!=null){
                    next=curr.next;
                    curr.next=null;
                }
                //待处理
                ListNode sortedList=merge(head1,head2);
                prev.next=sortedList;
                while(prev.next!=null){
                    prev=prev.next;
                }
                curr=next;
            }
        }
        return dummy.next;
    }

}
