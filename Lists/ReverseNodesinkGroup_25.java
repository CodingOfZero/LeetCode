package Lists;

import java.util.ArrayList;

/**
 *给定一个链接列表，每次反转链接列表的k个节点，并返回其修改后的列表。
 * k为正整数，小于或等于链接列表的长度。如果节点数不是k的倍数，那么最后遗漏的节点应该保持原样。
 */
public class ReverseNodesinkGroup_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null) return head;
        ListNode p=head;
        int len=0;
        for(;p!=null;p=p.next)//链表长度
            len++;
        int m=len/k;
        ArrayList<ListNode> result = new ArrayList<>();
        for(int i=0;i<m;i++){
            ListNode pre=null;
            for(int j=0;j<k;j++){
                ListNode temp=head;
                head=head.next;
                temp.next=pre;
                pre=temp;
            }
            result.add(pre);
        }
        result.add(head);
        ListNode finHead=result.get(0);
        for(int i=0;i<result.size()-1;i++){
            ListNode q=result.get(i);
            while (q.next!=null) q=q.next;
            q.next=result.get(i+1);
        }
        return finHead;
    }
}
