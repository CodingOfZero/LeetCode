package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 求树中结点之间最小的差值
 */
public class MinimumAbsoluteDifferenceinBST_530 {
    private Integer minDiff=Integer.MAX_VALUE;
    private Integer preVal;
    //改进
    public int getMinimumDifference(TreeNode root){
        helper(root);
        return minDiff;
    }
    private void helper(TreeNode root){
        if(root!=null){
            helper(root.left);
            //处理第一次preVal为空，不能比较，直接赋值
            if(preVal!=null){
                minDiff=Math.min(minDiff,root.val-preVal);
            }
            preVal=root.val;
            helper(root.right);
        }
    }
    public int getMinimumDifference1(TreeNode root) {
        LinkedList<Integer> list=new LinkedList<Integer>();
        inorder(root,list);
        //初始时，迭代器指向第一个元素的前面
        Iterator<Integer> iter1 = list.iterator();
        Iterator<Integer> iter2 =list.iterator();
        //hasNext看下一个位置是否含有元素，并不移动指针
        if(iter2.hasNext()) {
            iter2.next();
        }
        int min=Integer.MAX_VALUE;
        while (iter2.hasNext()){
            int head=iter2.next();
            int tail = iter1.next();
            if((head-tail)<min) {
                min=head-tail;
            }
        }
        return min;
    }

    /**
     * 中序遍历二叉搜索树是有序的
     * @param root
     * @param list
     */
    private void inorder(TreeNode root,LinkedList list) {
        if(root!=null){
            inorder(root.left,list);
            list.add(root.val);
            inorder(root.right,list);
        }
    }
}
