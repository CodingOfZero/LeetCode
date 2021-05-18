package Dynamic;

import java.util.Arrays;

/**
 *   0-1背包问题
 *   给定n个物料的权重和值，将这些物料放在容量为W的背包中，以在背包中获得最大的总价值。
 */
public class KnapsackProblem {
    /**
     * 递归版本 时间O（2 ^ n）
     * n个最大值
     * 1）不包含第n个，即n-1个最大值
     * 2）包含第n个
     * @param W 背包容量
     * @param wt 物料重量
     * @param val 物料价值
     * @param n 物料种类
     * @return 在背包容量范围内的最大价值
     */
    public static int knapSackRecur(int W, int[] wt, int[] val, int n ){
        //base case
        if(n==0||W==0){
            return 0;
        }
        if(wt[n-1]>W){
            return knapSackRecur(W,wt,val,n-1);
        }else{
            return Math.max(knapSackRecur(W,wt,val,n-1),val[n-1]+
                    knapSackRecur(W-wt[n-1], wt, val, n-1));
        }
    }

    /**
     * 动态规划,此方法是递归的扩展，采用top-down 时间空间 O（N * W）。
     * @param W
     * @param wt
     * @param val
     * @param n
     * @return
     */
    public static int knapSack(int W, int[] wt, int[] val, int n ){
        int[][] dp=new int[n][W+1];
        for(int[] item:dp){
            Arrays.fill(item,-1);
        }
        //这里与上面对比，传入的不再是长度了，而是直接传入可用下标
        return knapSackTopDown(W,wt,val,n-1,dp);
    }

    public static int knapSackTopDown(int W, int[] wt, int[] val, int n,int[][] dp){
        if(n<0) {
            return 0;
        }
        if(dp[n][W]!=-1) {
            return dp[n][W];
        }


        if(wt[n]>W){
            dp[n][W]=knapSackTopDown(W,wt,val,n-1,dp);
            return dp[n][W];
        }else{
            dp[n][W]=Math.max(knapSackTopDown(W,wt,val,n-1,dp),val[n]+
                    knapSackTopDown(W-wt[n], wt, val, n-1,dp));
            return dp[n][W];
        }
    }

    //DP
    public static int knapSackBottomUp(int W, int[] wt, int[] val, int n ){
        int[][] dp=new int[n+1][W+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=W;j++){
                if(i==0||j==0){
                    dp[i][j]=0;
                }else if(wt[i-1]<=j){
                    dp[i][j]=Math.max(dp[i-1][j],val[i-1]+dp[i-1][j-wt[i-1]]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }

            }
        }
        return dp[n][W];
    }
    public static void main(String[] args)
    {
        int[] val = new int[] { 60, 100, 120 };
        int[] wt = new int[] { 10, 20, 30 };
        int W = 50;
        int n = val.length;
        System.out.println(knapSackBottomUp(W, wt, val, n));
    }
}
