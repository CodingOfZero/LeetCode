package Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PathSumIII_437 {
    /**
     * 思路三：相比于其他思路，该方法非常快    2ms
     * 1）preSum存储从根到递归当前结点的总和
     * 2）在到达当前节点之前，该映射存储<前缀和，频数>对。我们可以想象从根到当前节点的路径。
     *    从路径中间的任何节点到当前节点的总和=从根到当前节点的总和与中间节点的前缀总和之差。
     * 3）我们正在寻找一些总和达到给定目标值的连续节点，这意味着2）中讨论的差应等于目标值。此外，我们需要知道多少差等于目标值。
     * 4）map将所有可能的总和的频率存储到当前节点的路径中。如果map中存在当前总和与目标值之间的差，则路径中间必须存在一个节点，以便从该节点到当前节点的总和等于目标值。
     * 5)每次递归都会返回以当前节点为根的子树中有效路径的总和,包含三个部分
     *      当前节点左侧子树的子树中的有效路径总数。
     *      以当前节点的右子节点为根的有效路径总数。
     *      当前节点结束的有效路径数。
     * 6）最后递归返回时，将该结点表示的前缀和的频数减一（有点类似树的路径，递归返回时，剔除当前访问的结点）
        前缀是从顶部（根）到底部（叶）计数的，而总计数的结果是从底部到顶部计算的
     */
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer,Integer> preSum =new HashMap<>();
        //处理树只有根节点情况
        preSum.put(0,1);
        return Helper(root,0,sum,preSum);
    }

    private int Helper(TreeNode root, int curSum, int target, HashMap<Integer, Integer> preSum) {
        if(root==null) return 0;
        curSum+=root.val;
        int count=preSum.getOrDefault(curSum-target,0);
//        preSum.put(curSum,preSum.getOrDefault(curSum,0)+1);
        preSum.merge(curSum,1,Integer::sum);

        count=count+Helper(root.left,curSum,target,preSum)+Helper(root.right,curSum,target,preSum);
        preSum.put(curSum,preSum.get(curSum)-1);
        return count;
    }


    //思路一：两个递归，思路清晰，速度适中， 24ms
    private int DFS(TreeNode root, int sum){
        if(root==null)
            return 0;
        //如果发现，继续向下寻找
        if(root.val==sum){
            return 1+DFS(root.left,sum-root.val)+DFS(root.right,sum-root.val);
        }else{
            return DFS(root.left,sum-root.val)+DFS(root.right,sum-root.val);
        }
    }
    public int pathSum_1(TreeNode root, int sum) {
        if(root==null){
            return 0;
        }else{
            //对根节点进行一次DFS
            //在左子树寻找
            //在右子树寻找
            return DFS(root,sum)+pathSum_1(root.left,sum)+pathSum_1(root.right,sum);
        }
    }
    //思路二：慢  70ms
    /**   对于树中的每个父节点，我们有2个选择。
        1. 把它包括在达到总和的路径中.
        2. 不将其包含在达到总和的路径中。

        对于树上的每个子节点，我们有2个选择。
        1. 拿出你的父节点留给你的东西
        2.从自己开始形成路径。

        有一点要注意。
        树上的每一个节点只能做一次起点。
    * */
    int target;
    Set<TreeNode> visited;
    public int pathSum_2(TreeNode root, int sum) {
        target=sum;
        visited=new HashSet<>();//当某个结点视为根时，存储该结点
        return pathSumHelper(root,sum,false);
    }

    private int pathSumHelper(TreeNode root, int sum, boolean hasParent) {
        if(root==null) return 0;
        //当sum等于target且没有父结点时，说明从某个结点开始将它作为根重新寻找路径了，如果它已经在
        //set集合中，说明已经统计过了，如果没有，将其加入set集合中，进行接下来的寻找。防止重复计算
        if(sum==target&&visited.contains(root)&&!hasParent) return 0;
        if(sum==target&&!hasParent) visited.add(root);

        int count=(root.val==sum)?1:0;
        count+=pathSumHelper(root.left,sum-root.val,true);
        count+=pathSumHelper(root.right,sum-root.val,true);
        count+=pathSumHelper(root.left,target,false);
        count+=pathSumHelper(root.right,target,false);
        return count;
    }

    public static void main(String[] args) {
        int[] input={10,5,-3,3,2,-1,11,3,-2,-1,1,-1,-1};
        TreeNode node = BuildTree.buildTree(input,-1);
        PathSumIII_437 path=new PathSumIII_437();
        int res = path.pathSum(node, 8);
        System.out.println(res);
    }
}
