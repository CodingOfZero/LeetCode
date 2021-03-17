package String;

/**
 * 动态规划
 * 当T的长度大于S的长度时，S的子序列一定没有T，直接返回0
 * dp[i][j]：表示S[i:]的子序列中T[j:]的个数
 * dp[i][j]
 *          s[i]=[j]有两种选择，可以选择匹配也可以不选择匹配  dp[i+1][j+1]+dp[i+1][j]
 *          s[i]!=[j]  dp[i+1][j]
 */
public class DistinctSubsequences_115 {
    public int numDistinct(String s, String t) {
        int m=s.length(),n=t.length();
        if(n>m) return 0;
        int[][] dp=new int[m+1][n+1];
        //当j为n时，T为空串，是S的子序列，初始化为1，当i为m时，S是空串，初始化为0
        for(int i=0;i<=m;i++){
            dp[i][n]=1;
        }
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(s.charAt(i)==t.charAt(j)){
                    dp[i][j]=dp[i+1][j+1]+dp[i+1][j];
                }else{
                    dp[i][j]=dp[i+1][j];
                }
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        int i = new DistinctSubsequences_115().numDistinct("rabbbit", "rabbit");
        System.out.println(i);
    }
}
