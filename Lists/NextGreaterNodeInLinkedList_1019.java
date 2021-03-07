package Lists;

import java.util.Stack;

/**
 * 一条链表，结点从1开始计数
 * 对于结点i,从i+1开始找到第一个结点值比它大的数
 */
public class NextGreaterNodeInLinkedList_1019 {
    public int[] nextLargerNodes(ListNode head) {
        if(head==null)
            return new int[0];

        int cnt=0;
        ListNode node=head;
        while(node!=null){//链表长度
            cnt++;
            node=node.next;
        }

        Stack<int[]> stk=new Stack<int[]>();//存放数组
        int[] rst=new int[cnt];//结果
        node=head;
        for(int i=0;i<cnt;i++){
            while(!stk.isEmpty()&&stk.peek()[1]<node.val)//将比当前结点值小的结点全部设置为当前结点的值
                rst[stk.pop()[0]]=node.val;

            stk.push(new int[]{i,node.val});//初始化数组，第一个元素是位置，第二个是结点值。将数组压入栈中
            node=node.next;
        }

        return rst;
    }
    //暴力，时间复杂度为O(n^2)
    public int[] nextLargerNodes_1(ListNode head) {
        ListNode t=head;
        int len=0;
        while (t!=null){
            len++;
            t=t.next;
        }
        int[] result=new int[len];
        int i=0;
        while (head!=null){
            ListNode p=head;
            ListNode q=head.next;
            while (q!=null&&p.val>=q.val)
                q=q.next;
            result[i++]=q==null?0:q.val;//空表示没有比它大的，赋值为0；否则填入第一个比它大的数
            head=head.next;
        }
        return result;
    }


}
