package Dynamic;

/**
 * 奇怪的打印机
 * 本质上，用空串刷几次能刷成字符串s
 * s[i]==s[j]   dp[i][j]=dp[i+1][j]  (或dp[i][j-1])
 * s[i]!=s[j]   dp[i][j]=min{dp[i][j],dp[i][k]+dp[k+1][j]}
 */
public class StrangePrinter_664 {
    public int strangePrinter(String s) {
        if(s==null||s.length()==0) return 0;
        int n=s.length();
        int[][] dp=new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i]=1;
        }

        for(int len=2;len<=n;len++){
            for(int i=0;i<n-len+1;i++){
                int j=i+len-1;
                dp[i][j]=Integer.MAX_VALUE;
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j];
                }else{
                    for(int k=i;k<j;k++){
                        dp[i][j]=Math.min(dp[i][j],dp[i][k]+dp[k+1][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}
