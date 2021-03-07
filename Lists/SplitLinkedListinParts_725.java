package Lists;

/**
 * 给定一个带有头节点根的（单）链接列表，写一个函数将链接列表分割成k个连续的链接列表 "部分"。
 * 每个部分的长度应该尽可能相等：任何两个部分的大小都不应该相差超过1，这可能导致一些部分为空。
 * 零件应该按照在输入列表中出现的顺序排列，较早出现的零件的大小应该总是大于或等于较晚出现的零件。
 * 返回一个ListNode的列表，代表形成的链接列表部分。
 * 思路：先将链表结点长度分解为合适的k个数，将其存入splitNum[]，再将链表按照里面的长度进行分解
 */
public class SplitLinkedListinParts_725 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int[] splitNum=new int[k];
        ListNode[] result=new ListNode[k];//结果
        ListNode q=root;

        int listLen=0;//链表长度
        while (q!=null){
            listLen++;
            q=q.next;
        }
        if(listLen<=k){//当链表长度小于等于k时，每个应划分为1，最后不够为0
            for(int i=0;i<listLen;i++)
                splitNum[i]=1;
        }else{
            int m=listLen/k;
            int dif=listLen-k*m;//与均分的差值
            for(int i=0;i<k;i++){
                if(dif!=0){
                    splitNum[i]=m+1;
                    dif--;
                }else
                    splitNum[i]=m;
            }
        }
        //分解链表
        for(int i=0;i<k;i++){
            int len=splitNum[i];
            ListNode p=root;
            ListNode pre=null;
            for(int j=0;j<len;j++)
                if(root!=null){
                    pre=root;
                    root=root.next;
                }
            if(pre!=null) pre.next=null;
            result[i]=p;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] i={1,2,3,4,5,6,7};
        ListNode Dummy=new ListNode();
        ListNode p=Dummy;
        for(int j:i){
            ListNode q=new ListNode(j);
            p.next=q;
            p=q;
        }
        SplitLinkedListinParts_725 s=new SplitLinkedListinParts_725();
        s.splitListToParts(Dummy.next,3);
    }
}
