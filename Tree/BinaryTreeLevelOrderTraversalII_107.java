package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 层次遍历，自底向上
 * 思路：将自顶向上的结果放到栈中，最后在存在结果链表中
 */
public class BinaryTreeLevelOrderTraversalII_107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result=new LinkedList<>();
        Stack<List<Integer>> dummy=new Stack<>();
        Queue<TreeNode> aux=new LinkedList<>();
        if(root==null) return result;
        aux.add(root);
        while (!aux.isEmpty()){
            List<Integer> levelList=new LinkedList<>();
            int size=aux.size();
            while (size--!=0){
                TreeNode pollTree=aux.poll();
                levelList.add(pollTree.val);
                if(pollTree.left!=null)
                    aux.add(pollTree.left);
                if(pollTree.right!=null)
                    aux.add(pollTree.right);
            }
            if(levelList.size()>0) dummy.push(levelList);
        }
        while (!dummy.isEmpty()){
            result.add(dummy.pop());
        }
        return result;
    }
}
