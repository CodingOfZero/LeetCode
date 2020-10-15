package Tree;

/**
 *给定一棵二叉排序树，将树按顺序重新排列，使树中最左边的节点现在是树的根，每个节点没有左子，只有1个右子。
 */
public class IncreasingOrderSearchTree_897 {
    private TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode ans=new TreeNode(0);
        cur=ans;
        inOrder(root);
        return ans.right;
    }
    private void inOrder(TreeNode root){
        if(root!=null){
            inOrder(root.left);
            //左子树置为空
            root.left=null;
            //连接
            cur.right=root;
            //指向最新结点
            cur=cur.right;
            inOrder(root.right);
        }
    }
}
