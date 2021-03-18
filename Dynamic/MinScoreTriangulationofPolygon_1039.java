package Dynamic;

import java.util.Arrays;

/**
 * 区间dp
 * 多边形三角剖分的最低得分
 * dp[l][r]=min{dp[l][r],dp[l][k]+dp[k][r]+A[l]*A[r]*A[k]}
 */
public class MinScoreTriangulationofPolygon_1039 {
    public static int minScoreTriangulation(int[] values){
        int n=values.length;
        int[][] dp=new int[n][n];

        for(int len=2;len<=n;len++){
            for(int i=0;i<n;i++){
                int j=i+len;
                if(j>=n) continue;
                dp[i][j]=Integer.MAX_VALUE;
                for(int k=i+1;k<j;k++){
                    dp[i][j]=Math.min(dp[i][j],dp[i][k]+dp[k][j]+values[i]*values[j]*values[k]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        int i = minScoreTriangulation(new int[]{3, 7, 4, 5});
        System.out.println(i);
    }
}
