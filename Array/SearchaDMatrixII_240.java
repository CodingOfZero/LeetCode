package Array;

/**
 * 二维数组搜索
 */
public class SearchaDMatrixII_240 {
    /**
     * 时间复杂度为O(m+n)
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        int n=matrix[0].length;
        int x=0,y=n-1;

        while(x<m&&y>=0){
            if(matrix[x][y]==target){
                return true;
            }else if(matrix[x][y]>target){
                y-=1;
            }else{
                x+=1;
            }
        }
        return false;
    }
}
