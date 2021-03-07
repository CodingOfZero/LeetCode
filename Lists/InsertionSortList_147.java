package Lists;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSortList_147 {
    //不是插入排序了,虽然快，但不符合题意
    public ListNode insertionSortList(ListNode head) {
        ListNode p=head;
        int len=0;
        while (p!=null){
            len++;
            p=p.next;
        }
        int[] value=new int[len];
        p=head;
        for(int i=0;i<len;i++){
            value[i]=p.val;
            p=p.next;
        }
        Arrays.sort(value);
        p=head;
        for(int i=0;i<len;i++){
            p.val=value[i];
            p=p.next;
        }
        return head;
    }
    //变换指针
    public ListNode insertionSortList_1(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode p=head;
        int len=0;
        while (p!=null){
            len++;
            p=p.next;
        }
        ListNode Dummy=new ListNode();
        Dummy.next=head;
        head=head.next;
        Dummy.next.next=null;

        ListNode cur=head;
        for(int i=1;i<len;i++){
            ListNode q=cur;
            cur=cur.next;
            q.next=null;

            ListNode pre=Dummy;
            ListNode phead=Dummy.next;
            while (phead!=null&&phead.val<=q.val){
                pre=phead;
                phead=phead.next;
            }
            q.next=phead;
            pre.next=q;

        }
        return Dummy.next;
    }
}
