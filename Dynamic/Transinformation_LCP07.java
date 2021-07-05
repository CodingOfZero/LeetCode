package Dynamic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 传递信息，统计从源点到终点有几条路径
 */
public class Transinformation_LCP07 {
    /**
     * dp[i][j]表示经过i轮到达编号j的玩家方案数
     */
    public int numWaysDP(int n,int[][] relation,int k){
        int[][] dp=new int[k+1][n];
        dp[0][0]=1;
        for(int i=0;i<k;i++){
            for(int[] edges:relation){
                int src=edges[0],dst=edges[1];
                dp[i+1][dst]+=dp[i][src];
            }
        }
        return dp[k][n-1];
    }


    private int res;
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> edges=new HashMap<>();
        res=0;
        for(int i=0;i<n;i++){
            edges.put(i,new LinkedList<>());
        }
        for(int[] ele:relation){
            edges.get(ele[0]).add(ele[1]);
        }
        dfs(edges,0,n-1,k);
        return res;
    }

    private void dfs(Map<Integer,List<Integer>> edges,int start,int n,int k){
        if(k==0){
            if(start==n){
                res++;
            }
            return;
        }
        List<Integer> nodes=edges.get(start);
        for(int node:nodes){
            dfs(edges,node,n,k-1);
        }
    }

    public static void main(String[] args) {

    }
}
