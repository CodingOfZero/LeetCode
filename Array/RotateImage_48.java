package Array;

public class RotateImage_48 {
    /**
     * 公式为：matrix[row][col]=matrix[col][n-row-1]
     * 通过两次翻转，一次水平，一次对角线翻转;
     * matrix[row][col]=matrix[n-row-1][col]  水平
     * matrix[n-row-1][col]=matrix[col][n-row-1] 对角线
     * @param matrix
     */
    public void rotateSecond(int[][] matrix){
        int n=matrix.length;
        //水平
        for(int i=0;i<n/2;i++){
            for(int j=0;j<n;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[n-i-1][j];
                matrix[n-i-1][j]=temp;
            }
        }
        //主对角线翻转
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
    }

    public void rotate(int[][] matrix) {
        rotateHelper(matrix,matrix.length,0,matrix.length);
    }

    private void rotateHelper(int[][] matrix,int n,int start,int end){
        if(start*2>=n){
            return;
        }
        for(int i=start;i<end-1;i++){
            //3次交换
            swap(matrix,start,i,start+i-start,end-1);
            swap(matrix,start,i,end-1,end-1-(i-start));
            swap(matrix,start,i,end-1-(i-start),start);
        }
        rotateHelper(matrix,n,start+1,n-start-1);
    }

    private void swap(int[][] matrix,int x,int y,int i,int j){
        int temp=matrix[x][y];
        matrix[x][y]=matrix[i][j];
        matrix[i][j]=temp;
    }
}
