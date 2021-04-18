package Backtrack;

import java.util.LinkedList;
import java.util.List;

public class PathSumII_113 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res=new LinkedList<>();
        List<Integer> tmp=new LinkedList<>();
        dfs(res,tmp,root,0,targetSum);
        return res;
    }
    private void dfs(List<List<Integer>> res,List<Integer> tmp,TreeNode root,int currentSum,int targetSum){
        if(root==null){
            return;
        }
        tmp.add(root.val);

        if(root.left==null&&root.right==null&&currentSum+root.val==targetSum){
            res.add(new LinkedList<>(tmp));
        }
        dfs(res,tmp,root.left,currentSum+root.val,targetSum);
        dfs(res, tmp, root.right, currentSum+root.val, targetSum);

        tmp.remove(tmp.size()-1);
    }

}
