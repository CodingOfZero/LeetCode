package Dynamic;

public class MaximalSquare_221 {
    /**
     * 求最大正方形的面积---求最大边长
     * dp[i][j]  以 (i, j)为右下角，且只包含 1 的正方形的边长最大值
     * dp[i][j]= min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1]  )+1  左边，上边，左上边
     * //初始化，i==0||j==0  字符为1，初始化为1
     * @param matrix
     * @return
     */
    public int maximalSquareDP(char[][] matrix) {
        int row=matrix.length,col=matrix[0].length;

        int[][] maxSide=new int[row][col];
//        for(int i=0;i<row;i++){
//            if(matrix[i][0]=='1'){
//                maxSide[i][0]=1;
//            }
//        }
//        for(int j=0;j<col;j++){
//            if(matrix[0][j]=='1'){
//                maxSide[0][j]=1;
//            }
//        }
        int side=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(matrix[i][j]=='1'){
                    if(i==0||j==0){
                        maxSide[i][j]=1;
                    }else{
                        maxSide[i][j]=Math.min(maxSide[i][j-1],Math.min(maxSide[i-1][j],maxSide[i-1][j-1]))+1;
                    }
                    side=Math.max(side,maxSide[i][j]);
                }

            }
        }

        return side*side;
    }
    /**
     * 暴力
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        int m=matrix.length,n=matrix[0].length;
        int maxSide=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]=='0'){
                    continue;
                }
                maxSide = Math.max(maxSide, 1);
                int currentMax=Math.min(m-i,n-j);

                for(int k=1;k<currentMax;k++){
                    boolean flag=true;
                    if(matrix[i+k][j+k]=='0'){
                        break;
                    }
                    for(int x=0;x<k;x++){
                        if(matrix[i+k][j+x]=='0'||matrix[i+x][j+k]=='0'){
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        maxSide=Math.max(maxSide,k+1);
                    }else{
                        break;
                    }
                }
            }
        }

        return maxSide*maxSide;
    }
}
