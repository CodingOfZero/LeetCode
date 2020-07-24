package Tree;

/**
 * 根据前序中序创建二叉树
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
public class ConstructBinaryTreefromPreorderandInorderTraversal_105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeCore(preorder,0,inorder,0,inorder.length-1);
    }
    private  TreeNode buildTreeCore(int[] preorder,int index,int[] inorder,int lo,int hi){
        if(lo>hi) return null;
        TreeNode root=new TreeNode(preorder[index]);

        int i=0;//左子树点的个数
        while(inorder[lo+i]!=preorder[index])++i;

        root.left=buildTreeCore(preorder,index+1,inorder,lo,lo+i-1);
        root.right=buildTreeCore(preorder,index+1+i,inorder,lo+i+1,hi);
        return root;
    }
}
