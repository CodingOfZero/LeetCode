package Dynamic;

/**
 * 矩阵链乘法问题：给定一个数组P[]，其中p[i−1]×p[i]表示矩阵Ai，输出最少的乘法次数，并输出此时的括号方案。
 * 例如p[] = {40, 20, 30, 10, 30}，它表示4个矩阵：40×20，20×30，30×10，10×30。
 * 4个矩阵相乘，当括号方案是(A(BC))D时，有最少乘法次数26000。
 *
 * 该问题属于区间DP问题
 * 设链乘的矩阵是AiAi+1…Aj，即区间[i,j]，那么按结合率，可以把它分成2个子区间[i,k]、[k+1,j]，分别链乘，有
 * AiAi+1…Aj=(Ai...Ak)(Ak+1...Aj)
 *
 * 必定有一个k，使得乘法次数最少，记这个k为ki,j。并且记Ai,j为此时AiAi+1…Aj通过加括号后得到的一个最优方案，它被ki,j分开。
 * 那么子链AiAi+1…Ak的方案Ai,k、子链Ak+1Ak+2…Aj的方案Ak+1,j也都是最优括号子方案。这样就形成了递推关系：
 *     A(i,j)=min{   A(i,k) +A(k+1,j)+p[i−1]*p[k]*p[j]   }
 * 用二维矩阵dp[i][j]来表示A(i,j)，得到转移方程为：
 * dp[i][j]={0,i=j min dp[i][k]+dp[k+1][j]+p[i−1]p[k]p[j],i≤k<j
 * dp[1][n]就是答案，即最少乘法次数。
 * 遍历i、j、k，复杂度是O(n3)。
 *
 * 区间DP常常可以用四边形不等式优化，但是这一题不行，因为它不符合四边形不等式优化所需要的单调性条件。
 */
public class MatrixChainMultiplication {
    /**
     * naive recursive implementation
     */
    public static int minMatrixChain(int[] p,int i,int j){
        if(i==j)
            return 0;
        int min=Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int count=minMatrixChain(p,i,k)+minMatrixChain(p,k+1,j)+p[i-1]*p[k]*p[j];
            if(count<min)
                min=count;
        }
        return min;
    }

    public static int minMatrixChainDP(int[] p){
        int n=p.length;

        int[][] table=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            table[i][i]=0;
        }
        //区间dp
        for(int len=2;len<n;len++){//长度
            for(int i=1;i<n-len+1;i++){//区间起点
                int j=i+len-1;//区间终点
                table[i][j]=Integer.MAX_VALUE;
                for(int k=i;k<j;k++){
                    table[i][j]=Math.min(table[i][j],table[i][k]+table[k+1][j]+p[i-1]*p[k]*p[j]);
                }
            }
        }
        return table[1][n-1];
    }
    public static void main(String args[])
    {
        int arr[] = new int[] { 1, 2, 3, 4 };
        int size = arr.length;

        System.out.println(
                "Minimum number of multiplications is "
                        + minMatrixChainDP(arr));
    }
}
