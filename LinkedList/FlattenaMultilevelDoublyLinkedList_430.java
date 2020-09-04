package Lists;

/**
 *
 */

public class FlattenaMultilevelDoublyLinkedList_430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
    public Node flatten(Node head) {
        if(head==null) return null;
        Node res=head;
        if(head.child==null)
            head.next=flatten(head.next);
        else{
            Node child=flatten(head.child);
            Node next=head.next;
            head.child=null;
            head.next=child;
            child.prev=head;
            while (head.next!=null)//孩子链表走到尾部
                head=head.next;
            if(next!=null){
                head.next=flatten(next);
                head.next.prev=head;
            }

        }
        return res;
    }
}
