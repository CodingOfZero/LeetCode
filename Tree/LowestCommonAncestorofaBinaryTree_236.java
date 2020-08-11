package Tree;


import java.util.ArrayList;
import java.util.List;

/**
 * 普通二叉树最低公共祖先
 */
public class LowestCommonAncestorofaBinaryTree_236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List listOne=new ArrayList();
        List listTwo=new ArrayList();
        isFindPath(root,p,listOne);
        isFindPath(root,q,listTwo);
        return findCommon(listOne,listTwo);
    }
    private boolean isFindPath(TreeNode root, TreeNode p, List list){
        if(root==p){
            list.add(root);
            return true;
        }
        boolean find=false;
        list.add(root);

        if(root.left!=null)
            find=isFindPath(root.left,p,list);
        if(!find&&root.right!=null)
            find=isFindPath(root.right,p,list);
        if(!find)
            list.remove(list.size()-1);
        return find;
    }
    private TreeNode findCommon(List listOne,List listTwo){
        int lenOne=0;
        int lenTwo=0;
        TreeNode last=null;
        if(listOne.size()>0&&listTwo.size()>0){
            while (lenOne<listOne.size()&&lenTwo<listTwo.size()&&listOne.get(lenOne)==listTwo.get(lenTwo)){
                last=(TreeNode) listOne.get(lenOne);
                lenOne++;
                lenTwo++;
            }
        }
        return last;
    }

}
