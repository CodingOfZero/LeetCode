package Lists;

import java.util.Stack;

/**
 * 给定一个二元树根和一个以head为首的链接列表，如果从head开始的链接列表中的所有元素都对应于二元树中的某个向下的路径，则返回True，否则返回False。
 * 如果链接列表中从头部开始的所有元素都对应于二叉树中的某个向下的路径，则返回True，否则返回False。
 * 在此上下文中，downward path是指从某个节点开始向下的路径。
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class LinkedListinBinaryTree_1367 {
//    //非递归实现
//    public boolean isSubPath(ListNode head, TreeNode root) {
//        if(root==null) return false;
//        if(preOrder(head,root))
//            return true;
//        else
//            return isSubPath(head,root.left)||isSubPath(head,root.right);
//    }
//    private boolean  preOrder(ListNode head,TreeNode root){
//        //检查从根结点的子树是否与链表相等
////        if(head==null) return true;
////        if(root==null) return false;
////        boolean result=false;
//        Stack<TreeNode> stack=new Stack<>();
//        ListNode p=head;
//        while (root!=null||!stack.isEmpty()){
//            if(root!=null){
//                //访问根
//                if(p==null) return true;
//                if(p.val==root.val)
//                    p=p.next;
//                else{
//
//                }
//
//                if(root.right!=null)
//                    stack.push(root.right);
//                root=root.left;
//            }else{
//                root=stack.pop();
//            }
//        }
//        return false;
//    }


    //递归实现
    public boolean isSubPath_re(ListNode head, TreeNode root) {
        if(root==null) return false;
        if(preOrder_re(head,root))
            return true;
        else
            return isSubPath_re(head,root.left)||isSubPath_re(head,root.right);
    }
    private boolean  preOrder_re(ListNode head,TreeNode root){
        //检查从根结点的子树是否与链表相等
        if(head==null) return true;
        if(root==null) return false;

        if(root.val==head.val)
            return preOrder_re(head.next,root.left)||preOrder_re(head.next,root.right);
        else{
            return false;
        }
    }

//错误答案
//    public boolean isSubPath_1(ListNode head, TreeNode root) {
//        return preOrder(head,root);
//    }
//    private boolean  preOrder(ListNode head,TreeNode root){
//        //访问根结点
//        if(head==null) return true;
//        boolean left=false;
//        boolean right=false;
//        if(root!=null){
//            if(root.val!=head.val){
//                left=preOrder(head,root.left);
//                right=preOrder(head,root.right);
//            }else{
//                left=preOrder(head.next,root.left);
//                right=preOrder(head.next,root.right);
//            }
//        }
//        return left||right;
//    }
}
