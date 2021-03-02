package Design;

/**
 * 给一个二维矩阵，计算子矩阵内的总和
 */
public class RangeSumQueryDImmutable_304 {
    /**
     * 利用一维前缀和
     */
//    private final int[][] sums;
//    public RangeSumQueryDImmutable_304(int[][] matrix) {
//        if(matrix==null||matrix.length==0) {
//            sums=null;
//            return;
//        }
//        int row=matrix.length,col=matrix[0].length;
//        //row行一维前缀和数组
//        sums=new int[row][col+1];
//        for(int i=0;i<row;i++){
//            //每一行求前缀和
//            for(int j=1;j<=col;j++){
//                sums[i][j]=sums[i][j-1]+matrix[i][j-1];
//            }
//        }
//    }
//
//    public int sumRegion(int row1, int col1, int row2, int col2) {
//        int res=0;
//        if(sums==null){return res;}
//        for(int i=row1;i<=row2;i++){
//            res+=sums[i][col2+1]-sums[i][col1];
//        }
//        return res;
//    }
    /**
     * 利用二维前缀和
     */
    private final int[][] sums;
    public RangeSumQueryDImmutable_304(int[][] matrix) {
        if(matrix==null||matrix.length==0) {
            sums=null;
            return;
        }
        int row=matrix.length,col=matrix[0].length;
        sums=new int[row+1][col+1];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                sums[i+1][j+1]=sums[i+1][j]+sums[i][j+1]-sums[i][j]+matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res=0;
        if(sums==null){return res;}
        res=sums[row2+1][col2+1]-sums[row1][col2+1]-sums[row2+1][col1]+sums[row1][col1];
        return res;
    }
}
