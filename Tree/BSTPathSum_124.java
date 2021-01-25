/**
 * 二叉树的最大路径和
 * 时间空间均为O(N)
 * 求解贡献值
 *      1）空节点的最大贡献值等于 0。
 *      2）非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和
 * 由贡献值求最大路径和
 * 该节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值，
 * 如果子节点的最大贡献值为正，则计入该节点的最大路径和，否则不计入该节点的最大路径和
 *
 * 与求二叉树的直径 题目543类似
 */
public class BSTPathSum_124 {
    /**
     *     a
     *    / \
     *   b   c
     *有三种方式
     *     b a c
     *     b a
     *     c a
     *     a一定会经过
     */
    private int maxSum=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        pathSumHelper(root);
        return maxSum;
    }

    private int pathSumHelper(TreeNode root) {
        if(root==null) {
            return 0;
        }
        //递归计算左右子结点的最大贡献值，只有大于0 才计入
        int left=Math.max(pathSumHelper(root.left),0);
        int right=Math.max(pathSumHelper(root.right),0);
        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewPaht=root.val+left+right;
        maxSum=Math.max(priceNewPaht,maxSum);
        return root.val+Math.max(left,right);
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
