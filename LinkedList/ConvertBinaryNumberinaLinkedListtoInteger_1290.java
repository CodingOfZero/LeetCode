package Lists;

/**
 * 求链表表示的总和，每位结点视为一位二进制数
 */
public class ConvertBinaryNumberinaLinkedListtoInteger_1290 {
    public int getDecimalValue(ListNode head) {
        int sum=0;
        while (head!=null){
            sum=2*sum+head.val;
            head=head.next;
        }
        return sum;
    }
}
