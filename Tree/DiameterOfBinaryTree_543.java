/**
 * 求解二叉树的直径，与求解二叉树的最大路径和类似  题目124
 */
public class DiameterOfBinaryTree_543 {
    int res;
    public int diameterOfBinaryTree(TreeNode root) {
        res=1;
        depth(root);
        //左右结点数-1
        return res-1;
    }

    public int depth(TreeNode root){
        if(root==null) return 0;
        int left=depth(root.left);
        int right=depth(root.right);
        //左右最多结点数
        res=Math.max(res,left+right+1);
        //深度
        return  Math.max(left,right)+1;
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
