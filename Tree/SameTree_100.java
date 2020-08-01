package Tree;

/**
 * 判断两个树是否相同
 */
public class SameTree_100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null||q==null)
            return p==null&&q==null;
        return p.val==q.val&&isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}
