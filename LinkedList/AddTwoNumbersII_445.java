package Lists;

import java.util.Stack;

/**
 * 两个非负数相加
 * 要考虑边界情况，考虑和为0或和超过范围 （大数问题）
 */
public class AddTwoNumbersII_445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        while (l1!=null){
            stack1.push(l1.val);
            l1=l1.next;
        }
        while (l2!=null){
            stack2.push(l2.val);
            l2=l2.next;
        }
        ListNode Dummy=new ListNode();
        Dummy.next=null;
        int carry=0;
        while (carry!=0||!stack1.isEmpty()||!stack2.isEmpty()){
            int m=0;//m为单位计算的结果
            if(!stack1.isEmpty()&&!stack2.isEmpty())
                m=stack1.pop()+stack2.pop()+carry;//当两者都非空时，两者数加上进位
            else if(!stack1.isEmpty()) m=stack1.pop()+carry;//当一个为空时，单个数加进位
            else if(!stack2.isEmpty()) m=stack2.pop()+carry;
            else m=carry;//都空时，进位为此时的结果
            carry=0;
            ListNode p=new ListNode(m%10);//新建结点，头插法
            p.next=Dummy.next;
            Dummy.next=p;
            if(m>=10) carry=1;//大于等于10就有进位
        }
        return Dummy.next;
    }

    //未考虑超出int表示的范围情况
   public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        int num1=0;
        int num2=0;
        while (l1!=null){
            num1=10*num1+l1.val;
            l1=l1.next;
        }
        while (l2!=null){
            num2=10*num2+l2.val;
            l2=l2.next;
        }
        int sum=num1+num2;
        if(sum==0) return new ListNode(0);//特殊情况
        ListNode Dummy=new ListNode();
        Dummy.next=null;
        while (sum!=0){//头插法
            int value=sum%10;
            sum/=10;
            ListNode p=new ListNode(value);
            p.next=Dummy.next;
            Dummy.next=p;
        }
        return Dummy.next;
    }
}
