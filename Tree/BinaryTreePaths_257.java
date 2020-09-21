package Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 求树的所有路径
 */
public class BinaryTreePaths_257 {
    private List<String> res;
    private List<Integer> l1;
    public List<String> binaryTreePaths(TreeNode root) {
        res=new LinkedList<>();
        l1=new LinkedList<>();
        preOrder(root);
        return res;
    }
    private void preOrder(TreeNode root){
        if(root==null) return;
        l1.add(root.val);
        if(root.left==null&&root.right==null){
            res.add(build(new LinkedList<>(l1)));
        }
        preOrder(root.left);
        preOrder(root.right);
        l1.remove(l1.size()-1);
    }
    private String build(List<Integer> l1){
        StringBuilder sb=new StringBuilder();
        boolean flag=true;
        for(Integer k:l1){
            if(flag){
                sb.append(k);
                flag=false;
                continue;
            }
            sb.append("->");
            sb.append(k);
        }
        return sb.toString();
    }
}
