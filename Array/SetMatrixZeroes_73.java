package Array;


import java.util.HashSet;
import java.util.Iterator;

/**
 * 矩阵置零
 */
public class SetMatrixZeroes_73 {
    /**
     *  时间O(m*n) 空间O(1)
     *  使用第一行，第一列来充当标记数组
     */
    public static void setZeroesLittle(int[][] matrix) {
        int m=matrix.length,n=matrix[0].length;
        boolean row0=false,col0=false;
        for(int i=0;i<m;i++){
            if(matrix[i][0]==0){
                col0=true;
            }
        }
        for(int j=0;j<n;j++){
            if(matrix[0][j]==0){
                row0=true;
            }
        }
        //记录标记
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=matrix[0][j]=0;
                }
            }
        }
        //置零
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][0]==0||matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }
        //如果先前0行就存在0，置零
        if(row0){
            for(int j=0;j<n;j++)
                matrix[0][j]=0;
        }
        if(col0){
            for(int i=0;i<m;i++)
                matrix[i][0]=0;
        }
    }

    //时间O(m*n) 空间O(m+n)
    public static void setZeroes(int[][] matrix) {
        int m=matrix.length,n=matrix[0].length;
        HashSet<Integer> rows=new HashSet<>(m);
        HashSet<Integer> cols=new HashSet<>(n);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int row : rows) {
            for (int j = 0; j < n; j++) {
                matrix[row][j] = 0;
            }
        }
        for(int col:cols){
            for(int i=0;i<m;i++){
                matrix[i][col]=0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        for(int[] ele:matrix){
            for(int e:ele){
                System.out.printf("%d ",e);
            }
            System.out.println();
        }
    }
}
