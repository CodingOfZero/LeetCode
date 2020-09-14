package Tree;

/**
 * 判断是否为平衡二叉树
 */
public class BalancedBinaryTree_110 {
    private boolean flag=true;
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        height(root);
        return flag;
    }
    private int height(TreeNode root){
        if(root==null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight=height(root.right);
        int diff=Math.abs(leftHeight-rightHeight);
        if(diff>1) {
            flag=false;
        }
        return 1+Math.max(leftHeight,rightHeight);
    }
}
