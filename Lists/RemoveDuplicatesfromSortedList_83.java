package Lists;

public class RemoveDuplicatesfromSortedList_83 {
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        ListNode p=head;
        ListNode q=head.next;
        while(q!=null){
            if(p.val==q.val){
                p.next=q.next;
                q=q.next;
            }else{
                p=q;
                q=q.next;
            }
        }
        return head;
    }
    public static ListNode deleteDuplicates_2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head;

        while (temp.next != null) {
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }
    public static void main(String[] args) {
        int[] data={1,1,2};
        ListNode k=new ListNode();
        ListNode temp=k;
        for(int i:data){
            ListNode t=new ListNode(i);
            temp.next=t;
            temp=temp.next;
        }
        ListNode m=deleteDuplicates(k.next);
        while (m!=null){
            System.out.println(m.val);
            m=m.next;
        }
    }
}
