package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PathSumII_113 {
    private List<List<Integer>> res;
    private List<Integer> path;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res=new ArrayList<>();
        path=new ArrayList<>();
        pathHelper(root,sum);
        return res;
    }
    private void pathHelper(TreeNode root,int sum){
        if(root!=null){
            path.add(root.val);
            if(root.left==null&&root.right==null&&sum==root.val){
                res.add(new LinkedList<>(path));
                path.remove(path.size()-1);
                return;//没有必要继续走下去
            }
            pathHelper(root.left,sum-root.val);
            pathHelper(root.right,sum-root.val);
            path.remove(path.size()-1);
        }
    }
    //error
//    private void pathHelper_1(TreeNode root,int sum){
//        Stack<TreeNode> aux=new Stack<>();
//        int curSum=0;
//        while (root!=null||!aux.isEmpty()){
//            if(root!=null){
//                path.add(root.val);
//                curSum+=root.val;
//                if(root.left==null&&root.right==null&&curSum==sum){
//                    res.add(new LinkedList<>(path));
//                }
//                if(root.right!=null)
//                    aux.add(root.right);
//                root=root.left;
//            }else{
//                curSum-=path.get(path.size()-1);
//                path.remove(path.size()-1);
//                root=aux.pop();
//            }
//        }
//    }
    public static void main(String[] args) {
        int[] input={5,4,8,11,-1,13,4,7,2,-1,-1,-1,-1,5,1};
        TreeNode node = BuildTree.buildTree(input,-1);
        PathSumII_113 path=new PathSumII_113();
        List<List<Integer>> lists = path.pathSum(node, 22);
        System.out.println("hh");
    }
}
