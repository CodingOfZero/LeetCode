package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 之字型遍历
 */
public class BinaryTreeZigzagLevelOrderTraversal_103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists=new LinkedList<>();
        if(root==null) return lists;
        Stack[] stacks=new Stack[2];
        int current=0;
        int next=1;
        stacks[0]=new Stack();//从左到右打印，即从右到左入栈
        stacks[1]=new Stack();//从右到左打印
        stacks[current].push(root);
        List<Integer> list=new LinkedList<>();
        while (!stacks[current].isEmpty()||!stacks[next].isEmpty()){
             TreeNode node= (TreeNode) stacks[current].pop();
             list.add(node.val);

             if(current==0){
                 if(node.left!=null)
                     stacks[next].push(node.left);
                 if(node.right!=null)
                     stacks[next].push(node.right);
             }else{
                 if(node.right!=null)
                     stacks[next].push(node.right);
                 if(node.left!=null)
                     stacks[next].push(node.left);
             }
             if(stacks[current].isEmpty()){//此时表明树的某一层已经收集完成，将其加入lists中，且交换current与next值
                 lists.add(new LinkedList<>(list));
                 list.clear();
                 current=1-current;
                 next=1-next;
             }
        }
        return lists;
    }
}
