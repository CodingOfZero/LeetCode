package Tree;

/**
 * 填充普通树的next域
 */
public class PopulatingNextRightPointersinEachNodeII_117 {
    public Node connect(Node root) {
        if(root==null) {
            return null;
        }
        //左结点不为空
        if(root.left!=null){
            //如果右结点存在，则左结点的next域指向右结点
            if(root.right!=null){
                root.left.next=root.right;
            }else{
                //当右结点不存在时
                //从双亲结点的next域出发，
                Node curr=root.next;
                while (curr!=null){
                    if(curr.left!=null){
                        root.left.next=curr.left;
                        break;
                    }
                    if(curr.right!=null){
                        root.left.next=curr.right;
                        break;
                    }
                    curr=curr.next;
                }
            }
        }
        //如果右结点不为空
        if(root.right!=null){
            Node curr=root.next;
            while (curr!=null){
                if(curr.left!=null){
                    root.right.next=curr.left;
                    break;
                }
                if(curr.right!=null){
                    root.right.next=curr.right;
                    break;
                }
                curr=curr.next;
            }
        }
        //先右边
        connect(root.right);
        connect(root.left);
        return root;
    }
}
