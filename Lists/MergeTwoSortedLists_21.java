package Lists;
/**
 * 合并排序列表
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class MergeTwoSortedLists_21 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode res,head;
        if(l1.val<l2.val) {
            res=l1;
            head=l1;
            l1=l1.next;
        }else{
            res=l2;
            head=l2;
            l2=l2.next;
        }
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                res.next=l1;
                res=res.next;
                l1=l1.next;
            } else{
                res.next=l2;
                res=res.next;
                l2=l2.next;
            }
//            else{                               //先前写成res.next=l1;res.next.next=l2;错误，这样使得链表不断循环
//                res.next=l1;
//                res=res.next;
//                l1=l1.next;
//                res.next=l2;
//                res=res.next;
//                l2=l2.next;
//            }
        }
        if(l1!=null) res.next=l1;
        if(l2!=null) res.next=l2;
        return head;
    }
/*************************************************************************************/
    public static ListNode mergeTwoLists_v2(ListNode l1, ListNode l2) {
        ListNode res=new ListNode(0);
        ListNode head=res;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                res.next=l1;
                l1=l1.next;
            } else{
                res.next=l2;
                l2=l2.next;
            }
            res=res.next;
        }
        res.next=l1!=null?l1:l2;
        return head.next;
    }
/*************************************************************************************/
    public static ListNode mergeTwoLists_v3(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=mergeTwoLists_v3(l1.next,l2);
            return l1;
        }else{
            l2.next=mergeTwoLists_v3(l1,l2.next);
            return l2;
        }
    }
/*
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
 * Memory Usage: 39 MB, less than 74.53% of Java online submissions for Merge Two Sorted Lists.
*/
    public static void main(String[] args) {
        int[] a={1,2,4};
        int[] b={1,3,4};
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(1);
        ListNode headl1=l1;
        ListNode headl2=l2;
        for(int i=1;i<a.length;i++){
            ListNode t=new ListNode(a[i]);
            l1.next=t;
            l1=l1.next;
        }
        for(int i=1;i<b.length;i++){
            ListNode t=new ListNode(b[i]);
            l2.next=t;
            l2=l2.next;
        }

        ListNode res=mergeTwoLists_v3(headl1,headl2);
        while (res!=null){
            System.out.println(res.val);
            res=res.next;
        }
    }
}
