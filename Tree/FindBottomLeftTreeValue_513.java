package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求树最后一行最左边结点的值,题目假定树的根非空
 */
public class FindBottomLeftTreeValue_513 {
    private int maxDepth=-1,value=0;
    public int findBottomLeftValue(TreeNode root) {
        inorder(root,0);
        return value;
    }

    private void inorder(TreeNode root, int depth) {
        if(root!=null) {
            inorder(root.left,depth+1);
            //如果深度比先前保存的还要大，则更新
            if(depth>maxDepth){
                maxDepth=depth;
                value=root.val;
            }
            inorder(root.right,depth+1);
        }
    }


    /**
     * 层次遍历思想
     * @param root
     * @return
     */
    public int findBottomLeftValue_1(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        int result=0;
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                //当i=0时，表明当前访问的是新一层的最左边结点
                if(i==0) result=node.val;
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
        }
        return result;
    }

}
