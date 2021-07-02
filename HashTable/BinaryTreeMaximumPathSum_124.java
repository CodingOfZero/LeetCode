package HashTable;

import Tree.BuildTree;

import Tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 二叉树中的最大路径和，打印出最大路径
 */
public class BinaryTreeMaximumPathSum_124 {


     private int res=Integer.MIN_VALUE;
     private TreeNode start;
     private Map<TreeNode,Integer> nodeValue=new HashMap<>();


     public int maxPathSum(TreeNode root,List<Integer> path){
        maxSum(root);
        printPath(start,nodeValue,path);
        return res;
     }

    /**
     * 打印最大路径和，思路就是使用最大路径和减去当前节点的值，然后分别获取该节点左右节点对应的路径和，将他们进行对比，
     * 如果等于左右节点之和，使用中序遍历处理左右节点，如果仅与其中的某个节点相等，则仅对其中一个节点遍历
     * @param root  最大路径以root为连接点
     * @param nodeValue 哈希表结构，保存节点以及对应的最大路径和
     * @param path 路径
     */
    private void printPath(TreeNode root,Map<TreeNode, Integer> nodeValue,List<Integer> path) {
         if(root==null) return;
         int sum=nodeValue.get(root)-root.val;
         int left=nodeValue.getOrDefault(root.left,0);
         int right=nodeValue.getOrDefault(root.right,0);
         if(left+right==sum){
             printPath(root.left,nodeValue,path);
             path.add(root.val);
             printPath(root.right,nodeValue,path);
         }else if(left==sum){
             printPath(root.left,nodeValue,path);
             path.add(root.val);
         }else if(right==sum){
             path.add(root.val);
             printPath(root.right,nodeValue,path);
         }
    }

    private int maxSum(TreeNode root){
         if(root==null) return 0;
         int left= Math.max(maxSum(root.left),0);
         int right= Math.max(maxSum(root.right),0);
         //res=Math.max(res,left+right+root.val);
         if(res<left+right+root.val){
             res=left+right+root.val;
             start=root;
         }
         nodeValue.put(root,left+right+root.val);
         return root.val+Math.max(left,right);
     }

    public static void main(String[] args) {
        TreeNode root=BuildTree.buildTree(new int[]{-10,9,20,Integer.MIN_VALUE,Integer.MIN_VALUE,15,7},Integer.MIN_VALUE);
        BinaryTreeMaximumPathSum_124 test = new BinaryTreeMaximumPathSum_124();
        List<Integer> path=new LinkedList<>();
        int i = test.maxPathSum(root,path);
        System.out.println(i);

        for(int x=0;x<path.size();x++){
            if(x==path.size()-1){
                System.out.printf("%d",path.get(x));
            }else{
                System.out.printf("%d-->",path.get(x));
            }
        }
    }


}
