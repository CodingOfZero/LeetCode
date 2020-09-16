package Tree;

import java.util.Stack;

/**
 * 要求实现二叉排序树迭代器，next操作时间为O(1) 空间为O(h) h为树的高度
 */
public class BinarySearchTreeIterator_173 {
    //本质上将中序遍历的非递归形式拆开

    private Stack<TreeNode> iter;
    public BinarySearchTreeIterator_173(TreeNode root) {
        iter =new Stack<>();
        inorderLeft(root);
    }

    private void inorderLeft(TreeNode root) {
        while(root!=null){
            iter.add(root);
            root=root.left;
        }
    }

    /** @return the next smallest number */
    //包含两种情况，第一种弹出的是叶结点，第二种是中间结点，对于中间结点，如果有右孩子需要入栈
    //平均时间复杂度为O(1)
    /*分析如下：
    * 当迭代所有N个节点时，每个节点在next()中正好被推送和弹出一次。
    * 这样算下来，在N次调用next()的过程中，需要2N*O(1)次，平均下来就是O(1)次，也就是摊销了O(1)次。
    * */
    public int next() {
        TreeNode popTreeNode= iter.pop();
        if(popTreeNode.right!=null) {
            inorderLeft(popTreeNode.right);
        }
        return popTreeNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !iter.isEmpty();
    }

/*    //不符合题意
    private Queue<TreeNode> queue;
    public BinarySearchTreeIterator_173(TreeNode root) {
        queue=new LinkedList<>();
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if(root!=null) {
            inorder(root.left);
            queue.add(root);
            inorder(root.right);
        }
    }
    private void inorder_for(TreeNode root) {
        Stack<TreeNode> treeIter=new Stack<>();
        TreeNode p=root;
        while (p!=null||!treeIter.isEmpty()){
            if(p!=null){
                treeIter.push(p);
                p=p.left;
            }else{
                p=treeIter.pop();
                queue.add(p);
                p=p.right;
            }
        }
    }
    *//** @return the next smallest number *//*
    public int next() {
        return queue.poll().val;
    }

    *//** @return whether we have a next smallest number *//*
    public boolean hasNext() {
        return !queue.isEmpty();
    }*/
}
