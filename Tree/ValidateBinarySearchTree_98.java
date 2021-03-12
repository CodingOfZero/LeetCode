package Tree;


/**
 * 判断是否为二叉排序树，每次记录前一个数，判断当前是否大于，如果不是，则不为二叉排序树
 */
public class ValidateBinarySearchTree_98 {
    private long min=Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root==null) {
            return true;
        }
        boolean left=isValidBST(root.left);
        if(root.val<=min){
            return false;
        }
        min=root.val;
        boolean right=isValidBST(root.right);
        return left&&right;
    }
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
}
