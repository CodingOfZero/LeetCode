package Dynamic;

public class MinimumFallingPathSum_931 {
    public int minFallingPathSum(int[][] matrix) {
        int n=matrix.length;
        int[][] dp=new int[n][n];

        System.arraycopy(matrix[0], 0, dp[0], 0, n);

        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                int min=dp[i-1][j];
                if(j==0){
                    min=Math.min(min,dp[i-1][j+1]);
                }else if(j==n-1){
                    min=Math.min(dp[i-1][j-1],min);
                }else{
                    min=Math.min(Math.min(dp[i-1][j-1],dp[i-1][j+1]),min);
                }
                dp[i][j]=min+matrix[i][j];
            }
        }
        int res=dp[n-1][0];
        for(int j=1;j<n;j++){
            res=Math.min(res,dp[n-1][j]);
        }
        return  res;
    }
}
