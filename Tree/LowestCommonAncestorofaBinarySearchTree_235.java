package Tree;

/**
 * 求二叉搜索树中两个节点的最低公共祖先
 */
public class LowestCommonAncestorofaBinarySearchTree_235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result=null;
        if(root!=null&&p!=null&&q!=null){
            if(root.val>p.val&&root.val>q.val)
                result=lowestCommonAncestor(root.left,p,q);
            else if(root.val<p.val&&root.val<q.val)
                result=lowestCommonAncestor(root.right,p,q);
            else
                result= root;
        }
        return result;
    }
}
