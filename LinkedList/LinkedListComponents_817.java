package Lists;

import java.util.HashSet;
import java.util.Set;

/**
 * 我们得到头，即一个包含唯一整数值的链接列表的头节点。
 * 我们还得到了列表G，是链接列表中的值的子集。
 * 返回G中连接组件的数量，如果两个值在链接列表中连续出现，则它们是连接的。
 */
public class LinkedListComponents_817 {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set=new HashSet<>();//使用set存储链表中的子集
        for(int i:G)
            set.add(i);
        int count=0;
        while (head!=null){
            if(set.contains(head.val)&&(head.next==null||!set.contains(head.next.val)))
                count++;//如果head.val在set中，head.next为空或不在set中，分量加1
            head=head.next;
        }
        return count;
    }
}
