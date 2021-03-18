package Dynamic;

/**
 * 最长回文子序列
 * dp[][] 从i到j子串中回文子序列的最大长度
 * s[i]=s[j] dp[i][j]=dp[i+1][j-1]+2;
 * s[i]!=s[j] dp[i][j]=Max{dp[i+1][j],dp[i][j-1]}
 */
public class LongestPalindromicSubsequence_516 {
    public int longestPalindromeSubseq(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];
        char[] str = s.toCharArray();

        for(int i=n-1;i>=0;i--){
            //单个是回文
            dp[i][i]=1;
            for(int j=i+1;j<n;j++){
                if(str[i]==str[j]){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
