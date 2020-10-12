package Dynamic;

/**
 * 排列
 *
 */
public class PermutationCoefficient {
    /**
     * 有以下递归形式：P(n, k) = P(n-1, k) + k* P(n-1, k-1)
     * 可以看到有重叠功能，所以使用动态规划
     * 时间复杂度为 O(n*k) 空间复杂度为 O(n*k)
     */
    public static int permutationCoeff_1(int n,int k){
        int[][] dp=new int[n+1][k+1];
//        int[][] dp=new int[n+2][k+2];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=Math.min(i,k);j++){
                if(j==0) {
                    dp[i][j]=1;
                } else {
                    dp[i][j]=dp[i-1][j]+j*dp[i-1][j-1];
                }
//                //as P(i,j)=0 for j>i  ???
//                dp[i][j+1]=0;
            }
        }
        return dp[n][k];
    }

    /**
     * 利用公式P(n,k) = n! / (n - k)!  时间O（1） 空间O（n）
     * @param n
     * @param k
     * @return
     */
    public static int permutationCoeff_2(int n,int k){
        int[] fact=new int[n+1];
        fact[0]=1;
        for(int i=1;i<=n;i++){
            fact[i]=i*fact[i-1];
        }
        //P(n,k) = n! / (n - k)!
        return fact[n]/fact[n-k];
    }

    /**
     * n(n-1)(n-2)...(n-m+1)
     * @param n
     * @param k
     * @return
     */
    public static int permutationCoeff_3(int n,int k){
        if(k>n) {
            return -1;
        }
        int end=n-k+1,res=1;
        for(int i=n;i>=end;i--){
            res*=i;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 10, k = 2;
        System.out.println("Value of P( " + n + ","+ k +")" +
                " is " + permutationCoeff_1(n, k) );

        System.out.println("Value of P( " + n + ","+ k +")" +
                " is " + permutationCoeff_2(n, k) );

        System.out.println("Value of P( " + n + ","+ k +")" +
                " is " + permutationCoeff_3(n, k) );
    }
}
