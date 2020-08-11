package Tree;


/**
 * 树每条路径代表的数字和,与题目1022除了进制不同均相同
 */
public class SumRoottoLeafNumbers_129 {
    private int sum=0;
    public int sumNumbers(TreeNode root) {
        getSum(root,0);
        return sum;
    }
    private void getSum(TreeNode root, int sumTemp){
        if(root==null) return;
        boolean isLeaf=root.left==null&&root.right==null;
        if(isLeaf){
            int sum1=10*sumTemp+root.val;
            sum+=sum1;
            return;
        }
        if(root.left!=null)
            getSum(root.left,10*sumTemp+root.val);
        if(root.right!=null)
            getSum(root.right,10*sumTemp+root.val);
    }
}
