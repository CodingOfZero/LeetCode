package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageofLevelsinBinaryTree_637 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result=new LinkedList<>();
        Queue<TreeNode> aux=new LinkedList<>();//辅助队列
        if(root==null) return result;
        aux.add(root);//将根结点入队
        while (!aux.isEmpty()){
            double tempSum=0.0;
            int size=aux.size();//队列结果
            for(int i=0;i<size;i++){
                TreeNode pollTree=aux.poll();
                tempSum+=pollTree.val;
                if(pollTree.left!=null)
                    aux.add(pollTree.left);
                if(pollTree.right!=null)
                    aux.add(pollTree.right);
            }
            result.add(tempSum/size);
        }
        return result;
    }
}
