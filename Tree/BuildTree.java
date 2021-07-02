package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BuildTree {

    /**
     * Integer.MIN_VALUE表示为null
     * @param input：以完全二叉树形式建立一棵树
     * @param nullValue :空值表示
     * @return 一棵树
     */
    public static TreeNode buildTree(int[] input,int nullValue){
        if(input.length==1) return new TreeNode(input[0]);
        List<TreeNode> nodeList=new ArrayList<>();
        for(int i=0;i<input.length;i++){
            nodeList.add(new TreeNode(input[i]));
        }
        for(int i=1;i<=input.length/2;i++){
            if(2*i-1<=input.length-1){
                if(nodeList.get(2*i-1).val!=nullValue)
                    nodeList.get(i-1).left=nodeList.get(2*i-1);
                else
                    nodeList.get(i-1).left=null;
            }
            if(2*i<=input.length-1){
                if(nodeList.get(2*i).val!=nullValue)
                    nodeList.get(i-1).right=nodeList.get(2*i);
                else
                    nodeList.get(i-1).right=null;
            }
        }
        return nodeList.get(0);
    }
}
