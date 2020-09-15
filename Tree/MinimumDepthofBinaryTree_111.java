package Tree;

/**
 * 树的最低深度
 */
public class MinimumDepthofBinaryTree_111 {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left=minDepth(root.left);
        int right=minDepth(root.right);
        if(left==0||right==0){
            return 1+left+right;
        }else
            return 1+Math.min(left,right);
    }
}
