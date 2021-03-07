package Lists;

/**
 * 重排链表
 * 思路：首先找到中间结点，将其分为两个链表，将后面链表逐次插入前面链表中
 */
public class ReorderList_143 {
    public  void reorderList(ListNode head) {
        if(head==null) return;
        ListNode left=head;
        ListNode right=head;
        while (right!=null&&right.next!=null){
            left=left.next;
            right=right.next.next;
        }
        right=left.next; //右边链表表头
        left.next=null; //使断链
        left=head;//左边链表表头
        if(right==null) return; //比如只有一个结点或两个结点
        right=reverse(right);//链表翻转

        //逐次插入
        while (right!=null){
            ListNode temp=right;
            right=right.next;
            temp.next=left.next;
            left.next=temp;
            left=left.next.next;
        }
    }
    private  ListNode reverse(ListNode head){
        ListNode pre=null;
        while (head!=null){
            ListNode temp=head;
            head=head.next;//这句不能放下句之后
            temp.next=pre;
            pre=temp;
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] k={1,2,3,4,5};
        ListNode root=new ListNode(-1);
        ListNode Dummy=root;
        for(int i:k){
            ListNode p=new ListNode(i);
            root.next=p;
            root=p;
        }
        ReorderList_143 list143 = new ReorderList_143();
        list143.reorderList(Dummy.next);
    }
}
