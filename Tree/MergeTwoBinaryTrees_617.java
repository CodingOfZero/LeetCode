package Tree;

/**
 * Input:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * Output:
 * Merged tree:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 */
public class MergeTwoBinaryTrees_617 {
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null){
            return t2;
        }
        if(t2==null){
            return t1;
        }
        t1.val+=t2.val;
        t1.left=mergeTrees(t1.left,t2.left);
        t1.right=mergeTrees(t1.right,t2.right);
        return t1;
    }

    public static void main(String[] args) {
        int[] t1={1,3,2,5};
        int[] t2={2,1,3,-1,4,-1,7};
        TreeNode tree1 = BuildTree.buildTree(t1);
        TreeNode tree2 = BuildTree.buildTree(t2);
        TreeNode node = MergeTwoBinaryTrees_617.mergeTrees(tree1, tree2);

    }
}

