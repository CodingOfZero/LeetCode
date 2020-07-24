package Tree;

/**
 * 根据中序后序创建二叉树
 */
public class ConstructBinaryTreefromInorderandPostorderTraversal_106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int hi=inorder.length-1;
        int index=postorder.length-1;
        return buildTreeCore(postorder,index,inorder,0,hi);
    }
    private TreeNode buildTreeCore(int[] postorder,int index,int[] inorder,int lo,int hi){
        if(lo>hi) return null;

        TreeNode root=new TreeNode(postorder[index]);
        int i=0;//子树点个数
        while (postorder[index]!=inorder[hi-i]) i++;

        root.right=buildTreeCore(postorder,index-1,inorder,hi-i+1,hi);
        root.left=buildTreeCore(postorder,index-i-1,inorder,lo,hi-i-1);
        return root;
    }
}
