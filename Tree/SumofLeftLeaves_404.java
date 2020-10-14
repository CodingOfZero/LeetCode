package Tree;

/**
 * 返回树中所有左叶子的和
 */
public class SumofLeftLeaves_404 {
    private int sum=0;
    public int sumOfLeftLeaves(TreeNode root) {
        sumHelper(root,false);
        return sum;
    }
    /**
     * 多设置一个参数，是左叶子加入sum中，右叶子跳过
     */
    private void sumHelper(TreeNode root,boolean flag){
        if(root!=null){
            if(root.left==null&&root.right==null&&flag){
                sum+=root.val;
            }
            sumHelper(root.left,true);
            sumHelper(root.right,false);
        }
    }
}
