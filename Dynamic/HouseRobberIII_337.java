package Dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍Ⅲ
 * 房子布局为二叉树，小偷不能偷有父子关系的房子，求盗取的最高金额
 * 分析状态,对于节点 root来说   f表示偷，g表示不偷
 *      1.盗取root，最高金额f(root)= g(l)+g(r)+root.val
 *      2.放弃偷盗root  g(root)=max(f(l),g(l))+max(f(r),g(r))
 *      利用后序遍历+两个集合，集合保存f 和 g的函数值
 */
public class HouseRobberIII_337 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private Map<TreeNode,Integer> f=new HashMap<>();
    private Map<TreeNode,Integer> g=new HashMap<>();

    /**
     * 时间空间均为O(n)
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root,0),g.getOrDefault(root,0));
    }
    private void dfs(TreeNode root){
        if(root==null){
            return;
        }
        dfs(root.left);
        dfs(root.right);
        f.put(root,root.val+g.getOrDefault(root.left,0)+g.getOrDefault(root.right,0));
        g.put(root,Math.max(f.getOrDefault(root.left,0),g.getOrDefault(root.left,0))+
                Math.max(f.getOrDefault(root.right,0),g.getOrDefault(root.right,0)));
    }

    /**
     * 利用数组，返回两种状态,省略Map空间
     * @param root
     * @return
     */
    public int robSecond(TreeNode root) {
        int[] rootStatus=dfsSecond(root);
        return Math.max(rootStatus[0],rootStatus[1]);
    }
    private int[] dfsSecond(TreeNode root){
        if(root==null){
            return new int[]{0,0};
        }
        int[] lStatus=dfsSecond(root.left);
        int[] rStatus=dfsSecond(root.right);
        int selected=root.val+lStatus[1]+rStatus[1];
        int noSelected=Math.max(lStatus[0],lStatus[1])+Math.max(rStatus[0],rStatus[1]);
        return new int[]{selected,noSelected};
    }


}
