package Tree;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}


public class PopulatingNextRightPointersinEachNode_116 {

    public Node connect(Node root) {
        if(root==null) {
            return null;
        }
        //从树根向下移动
        Node verItr=root;
        while (verItr!=null){
            //移动到树的左子树上
            Node horItr=verItr;
            while (horItr!=null&&horItr.left!=null){
                //左子树的next指向右子树
                horItr.left.next=horItr.right;
                //若next域不为空
                if(horItr.next!=null){
                    //结点右子树的next域指向结点的next的左子树
                    horItr.right.next=horItr.next.left;
                }else{
                    //若为空，结点右子树的next域设置为空
                    horItr.right.next=null;
                }
                horItr=horItr.next;
            }
            //转移到左子树
            verItr=verItr.left;
        }
        return root;
    }

    public Node connect_2(Node root) {
        return helper(root,null);
    }
    private Node helper(Node cur,Node pre){
        if(cur==null) return null;
        Node left=helper(cur.left,cur.right);
        Node right=helper(cur.right,pre==null?null:pre.left);
        if(left!=null){
            left.next=right;
            right.next=pre==null?null:pre.left;
        }
        return cur;
    }

    //利用层次遍历，效率低
    public Node connect_1(Node root) {
        if(root==null) {
            return null;
        }
        Queue<Node> nodes=new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()){
            int size=nodes.size();
            for(int i=0;i<size;i++){
                Node pollNode=nodes.poll();
                if(i==size-1){
                    pollNode.next=null;
                }else{
                    Node rightNode=nodes.peek();
                    pollNode.next=rightNode;
                }
                if(pollNode.left!=null) {
                    nodes.add(pollNode.left);
                }
                if(pollNode.right!=null) {
                    nodes.add(pollNode.right);
                }
            }
        }
        return root;
    }
}
