package Lists;

/**
 * 我们给你两个非空的链接列表，代表两个非负整数。这些数字以相反的顺序存储，
 * 并且它们的每个节点都包含一个数字。将这两个数字相加并以链接列表的形式返回。
 * 你可以假设这两个数字不包含任何前导零，除了数字0本身。
 */
public class AddTwoNumbers_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        int sum = 0;
        while (l1 != null || l2 != null || sum != 0) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            sum = sum / 10;
        }
        return dummy.next;
    }
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        int len1=getLength(l1);
        int len2=getLength(l2);
        if(len1<len2){//最后返回链表长度最长的那条
            ListNode temp=l1;
            l1=l2;
            l2=temp;
        }
        ListNode p1=l1,p2=l2;
        ListNode pre=null;
        int carry=0;
        while(p1!=null&&p2!=null){
            int sum=carry+p1.val+p2.val;
            carry=0;
            if(sum>=10){
                carry=1;
                p1.val=sum%10;
            }else
                p1.val=sum;
            pre=p1;
            p1=p1.next;
            p2=p2.next;
        }
        while (carry!=0){
            if(p1==null){
                pre.next=new ListNode(carry);
                break;
            }
            int sum=p1.val+carry;
            carry=0;
            if(sum>=10){
                carry=1;
                p1.val=sum%10;
            }else
                p1.val=sum;
            pre=p1;
            p1=p1.next;
        }
        return l1;
    }
    private int getLength(ListNode head){
        int len=0;
        while (head!=null) {
            len++;
            head=head.next;
        }
        return len;
    }

    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(9);
        l2.next=new ListNode(9);
        AddTwoNumbers_2 a=new AddTwoNumbers_2();
        ListNode listNode = a.addTwoNumbers(l1, l2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }
    }
}
