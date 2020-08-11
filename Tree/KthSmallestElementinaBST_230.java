package Tree;



/**
 * 找到二叉搜索树第k个最小的数
 */
public class KthSmallestElementinaBST_230 {
    private int index=0;
    private int result;
    public int kthSmallest(TreeNode root, int k) {
        if(root!=null){
            if(root.left!=null)
                result=kthSmallest(root.left,k);
            index++;
            if(index==k)
                result=root.val;
            if(root.right!=null)
                result=kthSmallest(root.right,k);
        }
        return result;
    }
}
