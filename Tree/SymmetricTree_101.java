package Tree;

public class SymmetricTree_101 {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        //如果都为空，则为镜像
        if(node1==null&&node2==null)
            return true;
        //两个树为镜像，需要满足三个条件
        //1-根结点必须相同
        //2-左树的左子树与右树的右子树相同
        //3=左树的右子树与右树的左子树相同
        if(node1!=null&&node2!=null&&node1.val==node2.val)
            return isMirror(node1.left,node2.right)&&isMirror(node1.right,node2.left);
        //如果都不满足，则为非镜像树
        return false;
    }
}
