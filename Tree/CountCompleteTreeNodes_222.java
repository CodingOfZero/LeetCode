package Tree;

/**
 * 给定根节点，统计树节点个数
 */
public class CountCompleteTreeNodes_222 {
    public int countNodes(TreeNode root) {
        if(root!=null){
            return countNodes(root.left)+countNodes(root.right)+1;
        }
        return 0;
    }
}
