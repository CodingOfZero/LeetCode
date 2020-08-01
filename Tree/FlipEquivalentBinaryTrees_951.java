package Tree;

/**
 * 判断两个树是否翻转等价，翻转次数不定
 */
public class FlipEquivalentBinaryTrees_951 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1==null||root2==null)
            return root1==null&&root2==null;
        return root1.val==root2.val&&(flipEquiv(root1.left,root2.left)||flipEquiv(root1.left,root2.right))
                &&(flipEquiv(root1.right,root2.left)||flipEquiv(root1.right,root2.right));
    }
}
