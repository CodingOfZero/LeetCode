package Lists;

/**
 * 包含随机指针的链表，对其进行复制
 */
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class CopyListwithRandomPointer_138 {
    public Node copyRandomList(Node head) {
        cloneNodes(head);
        connectRandomNodes(head);
        return reconnectNodes(head);
    }
    //复制每个节点并将其接在后面
    private void cloneNodes(Node head){
        Node pNode=head;
        while (pNode!=null){
            Node pCloned=new Node(pNode.val);
            pCloned.next=pNode.next;
            pNode.next=pCloned;
            pNode=pCloned.next;
        }
    }
    //复制随机指针，
    private void connectRandomNodes(Node head){
        Node pNode=head;
        while (pNode!=null){
            Node pCloned=pNode.next;
            if(pNode.random!=null)
                pCloned.random=pNode.random.next;
            pNode=pCloned.next;
        }
    }
    //拆分两个链表
    private Node reconnectNodes(Node head){
        Node pNode=head;
        Node pClonedHead=null;
        Node pClonedNode=null;
        if(pNode!=null){
            pClonedHead=pClonedNode=pNode.next;
            pNode.next=pClonedHead.next;
            pNode=pNode.next;
        }
        while (pNode!=null){
            pClonedNode.next=pNode.next;
            pClonedNode=pClonedNode.next;
            pNode.next=pClonedNode.next;
            pNode=pNode.next;
        }
        return pClonedHead;
    }
}

//    private void connectRandomNodes(Node head){
//        if(head==null) return;
//        Node phead=head;
//        Node clone=head.next;
//        while (phead!=null){
//            if(phead.random!=null)
//                clone.random=phead.random.next;
//            phead=clone.next;
//            if(phead!=null) clone=phead.next;
//        }
//    }

//    private Node reconnectNodes(Node head){
//        if(head==null) return null;
//        Node phead=head;
//        Node clone=head.next;
//        Node pclone=head.next;
//        while (phead!=null){
//            phead.next=pclone.next;
//            phead=phead.next;
//            pclone.next=pclone.next.next;//空指针异常
//            pclone=pclone.next;
//        }
//        return clone;
//    }