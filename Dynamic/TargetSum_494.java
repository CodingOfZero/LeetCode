package Dynamic;

/**
 * 目标和，给定一个非负整数数组，有两个符号+和-，对于数组种的每个整数，都可以从其中选择一个添加到前面
 * 01背包
 * dp[i][j] 表示前i个元素和为j的方案数
 */
public class TargetSum_494 {

    private int count=0;
    public int findTargetSumWaysDfs(int[] nums, int S) {
        dfs(nums,S,0,0);
        return count;
    }
    private void dfs(int[] nums,int S,int cur,int n){
        if(n== nums.length){
            if(cur==S)
                count++;
            return;
        }
        dfs(nums,S,cur+nums[n],n+1);
        dfs(nums,S,cur-nums[n],n+1);
    }



    public int findTargetSumWays(int[] nums, int S) {
        int n=nums.length;
        int[][] dp=new int[n][2001];
        //特殊情况
        //当num[0]时，应初始化为2，+0  -0 都是0
        if(nums[0]==0){
            dp[0][1000]=2;
        }else{
            dp[0][nums[0]+1000]=1;
            dp[0][-nums[0]+1000]=1;
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<2001;j++){
                if(j+nums[i]<2001&&j-nums[i]>=0){
                    dp[i][j]=dp[i-1][j-nums[i]]+dp[i-1][j+nums[i]];
                }else if(j+nums[i]<2001){
                    dp[i][j]=dp[i-1][j+nums[i]];
                }else{
                    dp[i][j]=dp[i-1][j-nums[i]];
                }

            }
        }
        return S>1000?0:dp[n-1][S+1000];
    }
    public static void main(String[] args) {
        int targetSumWays = new TargetSum_494().findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1);
        System.out.println(targetSumWays);
    }
}
