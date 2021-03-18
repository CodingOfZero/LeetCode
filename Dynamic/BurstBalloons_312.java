package Dynamic;

/**
 * 戳气球
 * 类似于凸边形分三角形问题
 * 关键在于思路转变：由于戳气球的操作，会导致两个气球从不相邻变成相邻，使得后续操作难以处理，于是可以倒过来看这些操作，将
 * 全过程看成是每次添加一个气球
 * dp[i][j] 表示开区间(i,j)的位置全部填满气球能够得到的最多硬币数
 * dp[i][j]  i<j-1   dp[i][k]+dp[k][j]+val[i]*val[k]*val[j]
 *           i>=j-1  0
 */
public class BurstBalloons_312 {
    public int maxCoins(int[] nums) {
        int n=nums.length;
        //扩充nums的边界，前后分别填充1
        int[] val=new int[n+2];
        int[][] dp=new int[n+2][n+2];
        for(int i=1;i<=n;i++){
            val[i]=nums[i-1];
        }
        val[0]=1;
        val[n+1]=1;
        int total=n+2;
        for(int len=3;len<=total;len++){
            for(int i=0;i<total-len+1;i++){
                int j=i+len-1;
                dp[i][j]=-1;
                for(int k=i+1;k<j;k++){
                    dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]+val[i]*val[k]*val[j]);
                }
            }
        }
        return dp[0][n+1];
    }

}
