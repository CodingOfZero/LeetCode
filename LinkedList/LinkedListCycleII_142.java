package Lists;

/**
 * 链表中如果有环，找到它开始的位置
 * 利用Floyd判圈算法
 *      可以判断是否存在环：快慢指针，一个步长为1，一个步长为2，看是否能相遇
 *      可以求得环的长度：在上一步基础上，存在环，此时slow与fast指针指向同一个结点，固定一个，让另一个跑一圈，即为环的长度
 *      可以求得环起始位置：还是接第一步，让slow保持与fast相遇的位置不动，然后让fast从S再次出发，
 *                        此时让fast的速度与slow保持一致，同时前进，当再次相遇的时候，则为环的起点。
 * 第3点证明
 * 1.假定S距离环起点距离为m，环的周长为n，（第一次）相遇点距离环的起点的距离是k。
 * 那么当两者相遇时，慢指针（slow）移动的总距离i = m + a * n + k，
 * 快指针（fast）的移动距离为2i，2i = m + b * n + k。
 * 其中，a和b分别为slow和fast在第一次相遇时转过的圈数。
 * 让两者相减（快减慢），那么有i = (b - a) * n。即i是圈长度的倍数。
 * 2.将一个指针移到出发起点S，另一个指针仍呆在相遇节点M处两者同时移动，每次移动一步。
 * 当第一个指针前进了m，即到达环起点时，另一个指针距离链表起点为i + m。
 * 考虑到i为圈长度的倍数，可以理解为指针从链表起点出发，走到环起点，然后绕环转了几圈，
 * 所以第二个指针也必然在环的起点。即两者相遇点就是环的起点
 *
 */
public class LinkedListCycleII_142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        boolean flag=false;
        //判断是否有环
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast) {
                flag=true;
                break;
            }
        }
        //求环的起始位置
        if(flag){
            fast=head;
            while (fast!=slow){
                fast=fast.next;
                slow=slow.next;
            }
            return slow;
        }
        return null;
    }

}
