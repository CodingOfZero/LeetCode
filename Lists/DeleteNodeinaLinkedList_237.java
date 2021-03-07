package Lists;

/**
 * 在单链表中删除节点
 */
public class DeleteNodeinaLinkedList_237 {
    public void deleteNode(ListNode node) {
        if(node.next!=null){
            node.val=node.next.val;
            node.next=node.next.next;
        }
    }
}
