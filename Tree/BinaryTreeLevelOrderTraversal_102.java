package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 层次遍历
 */
public class BinaryTreeLevelOrderTraversal_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result=new LinkedList<>();//最后的结果
        Queue<TreeNode> aux=new LinkedList<>();//辅助队列
        if(root==null) return result;
        aux.add(root);//将根结点入队
        while (!aux.isEmpty()){
            List<Integer> levelList=new LinkedList<>();//存放层序遍历的每层结果
            int size=aux.size();//队列结果
            for(int i=0;i<size;i++){
                TreeNode pollTree=aux.poll();
                levelList.add(pollTree.val);
                if(pollTree.left!=null)
                    aux.add(pollTree.left);
                if(pollTree.right!=null)
                    aux.add(pollTree.right);
            }
            if(levelList.size()>0) result.add(levelList);//当每层结果链表不为空时，将其加入到最后的结果当中
        }
        return result;
    }
    public List<List<Integer>> levelOrder_1(TreeNode root) {
        List<List<Integer>> result=new LinkedList<>();//最后的结果
        Queue<TreeNode> aux=new LinkedList<>();//辅助队列
        aux.add(root);//将根结点入队
        //将空也视为一个结点，通过记录每层的个数，用来当作遍历次数，以此推断什么时候到了该层的末尾
        while (!aux.isEmpty()){
            List<Integer> levelList=new LinkedList<>();//存放层序遍历的每层结果
            int size=aux.size();//队列结果
            for(int i=0;i<size;i++){
                TreeNode pollTree=aux.poll();
                if(pollTree!=null){//当该结点不为空时，记录其值，添加它的左右结点，无论是否为空
                    levelList.add(pollTree.val);
                    aux.add(pollTree.left);
                    aux.add(pollTree.right);
                }
            }
            if(levelList.size()>0) result.add(levelList);//当每层结果链表不为空时，将其加入到最后的结果当中
        }
        return result;
    }
}
