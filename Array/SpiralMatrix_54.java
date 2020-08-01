package Array;

import java.util.LinkedList;
import java.util.List;

/**
 * 圆圈打印数组
 */
public class SpiralMatrix_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix==null) return new LinkedList<>();
        int rows=matrix.length;
        if(rows==0) return new LinkedList<>();
        int cols=matrix[0].length;

        return spiralOrderPrint(matrix,rows,cols);
    }

    private List<Integer> spiralOrderPrint(int[][] matrix, int rows, int cols) {
        List<Integer> result=new LinkedList<>();
        int start=0;
        while(rows>start*2&&cols>start*2){
            int endX=cols-1-start;
            int endY=rows-1-start;
            //从左到右
            for(int i=start;i<=endX;i++){
                result.add(matrix[start][i]);
            }
            //从上到下
            if(start<endY){
                for(int i=start+1;i<=endY;i++)
                    result.add(matrix[i][endX]);
            }
            //从右到左
            if(start<endY&&start<endX){
                for(int i=endX-1;i>=start;--i){
                    result.add(matrix[endY][i]);
                }
            }
            //从下到上
            if(start<endY-1&&start<endX){
                for(int i=endY-1;i>start;--i)
                    result.add(matrix[i][start]);
            }
            start++;
        }
        return result;
    }

}
