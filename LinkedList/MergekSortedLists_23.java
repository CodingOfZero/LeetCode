package Lists;

/**
 * 合并k个有序链表
 * 首先考虑两个有序链表合并过程
 * 接着利用分而治之，两两进行合并。
 */

public class MergekSortedLists_23 {
    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length==0) return null;
        int len=lists.length;
        int interval=1;
        while(interval<len){
            for(int i=0;i<len-interval;i=i+2*interval){
                mergeList(lists,i,i+interval);
            }
            interval*=2;
        }
        return lists[0];
    }

    private void mergeList(ListNode[] lists,int l1, int l2){
        if(l2>=lists.length) return;
        ListNode root=new ListNode(-1);
        ListNode p=lists[l1];
        ListNode q=lists[l2];
        ListNode pre=root;
        while (p!=null&&q!=null){
            if(p.val<=q.val){
                pre.next=p;
                pre=p;
                p=p.next;
            }else{
                pre.next=q;
                pre=q;
                q=q.next;
            }
        }
        if(p!=null){
            pre.next=p;
        }
        if(q!=null){
            pre.next=q;
        }
        lists[l1]=root.next;
        lists[l2]=null;
    }
    //暴力，一个一个比较，效率低
    public ListNode mergeKLists_1(ListNode[] lists) {
        ListNode root=new ListNode(-1);
        ListNode p=root;
        for(int i=0;i<lists.length;i++){
            while (lists[i]!=null){
                int index = getMin(lists);
                if(index!=-1){
                    p.next=new ListNode(lists[index].val);
                    p=p.next;
                    lists[index]=lists[index].next;
                }
            }
        }
        return root.next;
    }
    private int getMin(ListNode[] lists){
        int index=-1;
        int min=100000;
        for(int i=0;i<lists.length;i++)
            if(lists[i]!=null){
                if(lists[i].val<min){
                    min=lists[i].val;
                    index=i;
                }
            }
        return index;
    }

    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        l1.next=new ListNode(4);
        l1.next.next=new ListNode(5);

        ListNode l2=new ListNode(1);
        l2.next=new ListNode(3);
        l2.next.next=new ListNode(4);

        ListNode l3=new ListNode(2);
        l3.next=new ListNode(6);
        ListNode[] listNodes={l1,l2,l3};
        MergekSortedLists_23 m=new MergekSortedLists_23();
        ListNode listNode = m.mergeKLists(listNodes);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }

    }
}
