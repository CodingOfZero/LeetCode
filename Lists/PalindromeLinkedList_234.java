package Lists;

import java.util.Stack;

/**
 * 判断链表是否为回文
 */
public class PalindromeLinkedList_234 {
    //空间为O(1)
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null) return true;
        //找到中间结点
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //翻转链表
        ListNode tmp=null;
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=slow){
            tmp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=tmp;
        }
        //链表长为奇数时
        if(fast!=null&&fast.next==null) slow=slow.next;
        //判断剩下的一半链表是否与翻转的链表相等
        while (slow!=null){
            if(slow.val!=pre.val)
                return false;
            pre=pre.next;
            slow=slow.next;
        }
        return true;
    }

    //空间为O(n)
    public boolean isPalindrome_1(ListNode head) {
        Stack<Integer> room=new Stack<>();
        ListNode pHead=head;
        while (pHead!=null){
            room.push(pHead.val);
            pHead=pHead.next;
        }
        while (head!=null){
            if(head.val!=room.pop())
                return false;
            head=head.next;
        }
        return true;
    }


}
