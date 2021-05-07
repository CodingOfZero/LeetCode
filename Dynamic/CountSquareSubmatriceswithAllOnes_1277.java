package Dynamic;

/**
 * 统计全为1的正方形子矩阵
 * dp[i][j] 以(i,j)为右下点的正方形最长边长
 * dp[i][j]=min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1])+1  左，上，左上方  matrix[i][j]==1
 */
public class CountSquareSubmatriceswithAllOnes_1277 {
    public int countSquares(int[][] matrix) {
        if(matrix==null||matrix.length==0) return 0;
        int m=matrix.length,n=matrix[0].length;
        int[][] maxEdge=new int[m][n];
        int res=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==1){
                    if(i==0||j==0){
                        maxEdge[i][j]=1;
                    }else{
                        maxEdge[i][j]=Math.min(maxEdge[i][j-1],Math.min(maxEdge[i-1][j],maxEdge[i-1][j-1]))+1;
                    }
                    res+=maxEdge[i][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CountSquareSubmatriceswithAllOnes_1277 test = new CountSquareSubmatriceswithAllOnes_1277();
        int i = test.countSquares(new int[][]{
                {1,0,1},
                {1,1,0},
                {1,1,0}
        });
        System.out.println(i);

    }
}
