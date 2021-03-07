package Lists;

/**
 * 实现一个链表
 */
public class DesignLinkedList_707 {
    private Node head = null;
    private int size = 0;

    private static class Node {
        private int val;
        private Node next;

        public Node (int v, Node next) {
            val = v;
            this.next = next;
        }

        public int getVal() {return val;}
        public void setVal(int v) {this.val = v;}
        public void setNext(Node next) {
            this.next = next;
        }
        public Node getNext() {return this.next;}
    }


    /** Initialize your data structure here. */
    public DesignLinkedList_707() {
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        Node node = getNode(index);
        return node == null ? -1 : node.getVal();
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) return null;
        Node visit = head;
        while(index != 0) {
            visit = visit.next;
            index--;
        }
        return visit;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node cur = new Node(val, head);
        head = cur;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node trav = head;
        while(trav.next != null) {
            trav = trav.next;
        }
        Node cur = new Node(val, null);
        trav.next = cur;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        if (index == 0) addAtHead(val);
        else if (index == size) addAtTail(val);
        else {
            Node prev = getNode(index-1);
            Node cur = new Node(val, prev.next);
            prev.next = cur;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        if (index == 0) {
            head = head.next;
        } else {
            Node prev = getNode(index-1);
            Node after = prev.next.next;
            prev.next = after;
        }
        size--;
    }
}
