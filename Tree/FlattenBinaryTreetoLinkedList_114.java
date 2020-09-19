package Tree;

import java.util.LinkedList;
import java.util.List;

public class FlattenBinaryTreetoLinkedList_114 {
    public void flatten(TreeNode root) {
        flatHelper(root);
    }
    private TreeNode flatHelper(TreeNode root){
        if(root==null) return null;
        TreeNode left=flatHelper(root.left);//左子树摊平
        TreeNode right=flatHelper(root.right);//右子树摊平
        root.right=append(left,right);//将摊平后的左子树与摊平后的右子树相连，返回首结点，将其设置为根的右子树
        root.left=null;//根左子树为空
        return root;
    }
    //连接操作
    private TreeNode append(TreeNode left, TreeNode right) {
        if(left==null) return right;//如果左边为空，直接返回处理好的右子树
        TreeNode p=left;
        while (p.right!=null) p=p.right;//对处理好的左子树不断遍历，直到它的右子树将要为空
        p.right=right;//连接
        return left;
    }

    //不符合题意
    private List<Integer> con;
    public void flatten_1(TreeNode root) {
        if(root==null) return;
        con=new LinkedList<>();
        helpter(root);
        TreeNode dummy=new TreeNode();
        TreeNode p=dummy;
        for(Integer i:con){
            TreeNode temp=new TreeNode(i);
            p.right=temp;
            p=temp;
        }
        root.left=null;
        root.right=dummy.right.right;
        dummy.right=null;
    }
    private void helpter(TreeNode root){
        if(root!=null){
            con.add(root.val);
            helpter(root.left);
            helpter(root.right);
        }
    }
}
