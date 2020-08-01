package Tree;

/**
 * 判断树是否存在和为给定数的路径
 */
public class PathSum_112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        sum-=root.val;
        return (sum==0&&root.left==null&&root.right==null)||hasPathSum(root.left,sum)||hasPathSum(root.right,sum);
    }

    public boolean hasPathSum_2(TreeNode root, int sum) {
        if(root==null) return false;
        if(root.left==null&&root.right==null&&sum==root.val) return true;
        return hasPathSum_2(root.left,sum-root.val)||hasPathSum_2(root.right,sum-root.val);
    }
}
