package Dynamic;

public class MinimumFallingPathSumII_1289 {
    /**
     * 对第三重循环进行优化，对于大部分数而言，最小值是相同的
     * 记录最小值和次小值，以及最小值的位置
     * @param arr
     * @return
     */
    public static int minFallingPathSumSuper(int[][] arr) {
        int n=arr.length;
        int[][] dp=new int[n][n];

        System.arraycopy(arr[0],0,dp[0],0,n);

        for(int i=1;i<n;i++){
            //找最小值，次小值，以及最小值位置
            int minFirst=Integer.MAX_VALUE;
            int minSecond=Integer.MAX_VALUE;
            int minIndex=-1;
            for(int k=0;k<n;k++){
                if(dp[i-1][k]<minFirst){
                    minSecond=minFirst;
                    minFirst=dp[i-1][k];
                    minIndex=k;
                }else if(dp[i-1][k]>=minFirst&&dp[i-1][k]<minSecond){
                    minSecond=dp[i-1][k];
                }
            }
            for(int j=0;j<n;j++){
                //注意，虽然题目中给定数字大小最大为99，但不意味着和不能超100
                //先前错误想法：int min=100;
                int min=minIndex==j?minSecond:minFirst;
                dp[i][j]=min+arr[i][j];
            }
        }
        int res=dp[n-1][0];
        for(int i=1;i<n;i++){
            res=Math.min(res,dp[n-1][i]);
        }
        return res;
    }
    public int minFallingPathSum(int[][] arr) {
        int n=arr.length;
        int[][] dp=new int[n][n];

        System.arraycopy(arr[0],0,dp[0],0,n);

        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                //注意，虽然题目中给定数字大小最大为99，但不意味着和不能超100
                //先前错误想法：int min=100;
                int min=Integer.MAX_VALUE;
                for(int k=0;k<n;k++){
                    if(k==j){
                        continue;
                    }
                    min=Math.min(min,dp[i-1][k]);
                }
                dp[i][j]=min+arr[i][j];
            }
        }
        int res=dp[n-1][0];
        for(int i=1;i<n;i++){
            res=Math.min(res,dp[n-1][i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]{
                {1,2,3},{4,5,6},{7,8,9}
        };
        minFallingPathSumSuper(arr);
    }
}
