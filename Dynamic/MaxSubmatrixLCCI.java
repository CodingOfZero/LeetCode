package Dynamic;


import java.util.Arrays;

/**
 * 该题是第53题，最大子序和的增强版，求解二维矩阵的最大子矩阵之和
 * 该题思路
 * 对于矩阵的中的每一列，进行相加，然后求解该列的最大子序和，就可将二维问题降为一维问题进行处理
 */
public class MaxSubmatrixLCCI {
    public int[] getMaxMatrixDP(int[][] matrix) {
        int[] res=new int[4];
        int n=matrix.length,m=matrix[0].length;
        int[] colSum=new int[m];
        int rowStart=0,colStart=0;
        int maxSum=Integer.MIN_VALUE;

        //矩阵上边界
        for(int i=0;i<n;i++){
            Arrays.fill(colSum,0);
            //矩阵下边界
            for(int j=i;j<n;j++){
                //最大子序和
                int sum=0;

                for(int k=0;k<m;k++){
                    //参加计算最大子序和的数组
                    colSum[k]+=matrix[j][k];
                    //子序和计算
                    if(sum>0){
                        sum+=colSum[k];
                    }else{
                        //单独，重新开始
                        sum=colSum[k];
                        //记录起始坐标
                        rowStart=i;
                        colStart=k;
                    }
                    if(sum>maxSum){
                        maxSum=sum;
                        res[0]=rowStart;
                        res[1]=colStart;
                        res[2]=j;
                        res[3]=k;
                    }
                }
            }
        }

        return  res;
    }
    public int[] getMaxMatrix(int[][] matrix) {

        int n=matrix.length,m=matrix[0].length;
        int sum=Integer.MIN_VALUE;
        int[] res=new int[4];



        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int dpN=n-i;
                int dpM=m-j;
                int[][] dp=new int[dpN][dpM];
                dp[0][0]=matrix[i][j];
                for(int d=1;d<dpM;d++){
                    dp[0][d]=dp[0][d-1]+matrix[i][j+d];
                }
                for(int c=1;c<dpN;c++){
                    dp[c][0]=dp[c-1][0]+matrix[i+c][j];
                }
                for(int c=1;c<dpN;c++){
                    for(int d=1;d<dpM;d++){
                        //dp[c][d]=Math.max(dp[c-1][d],dp[c][d-1])+matrix[i+c][j+d];
                        dp[c][d]=dp[c-1][d]+dp[c][d-1]-dp[c-1][d-1]+matrix[i+c][j+d];

                    }
                }

                for(int k=j;k<m;k++){
                    for(int a=i;a<n;a++){
                        //int tmp=getSum(matrix,i,j,a,k);
                        int tmp=dp[a-i][k-j];
                        if(tmp>sum){
                            sum=tmp;
                            update(res,i,j,a,k);
                        }
                    }
                }
            }
        }
        return res;
    }


    private int getSum(int[][] matrix,int startRow,int startCol,int endRow,int endCol){
        int sum=0;
        for(int i=startRow;i<=endRow;i++){
            for(int j=startCol;j<=endCol;j++){
                sum+=matrix[i][j];
            }
        }
        return sum;
    }
    private void update(int[] res,int i,int j,int a,int k){
        res[0]=i;
        res[1]=j;
        res[2]=a;
        res[3]=k;
    }
    public static void  main(String[] args){
        MaxSubmatrixLCCI test = new MaxSubmatrixLCCI();
        int[] maxMatrix = test.getMaxMatrix(new int[][]{
                {-1, 0},
                {0, -1}
        });
        for(int max:maxMatrix){
            System.out.println(max);
        }
    }
}
