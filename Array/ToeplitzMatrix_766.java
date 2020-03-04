/**
 * 题意：判断矩阵斜线上数字是否相同
 * 时间：2020年1月3日 20:51:18
 *  */
public class ToeplitzMatrix_766 {
    public static void main(String[] args){

        int[][] matrix={{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        int[][] matrix1={{36,59,71,15,26,82,87},{56,36,59,71,15,26,82},{15,0,36,59,71,15,26}};
        boolean t=isToeplitzMatrix(matrix);
        System.out.println(t);
        boolean t1=isToeplitzMatrix(matrix1);
        System.out.println(t1);

    }
    public static boolean isToeplitzMatrix(int[][] matrix) {
        for(int m=0;m<matrix.length;m++){//判断多行
            for(int k=0;k<matrix[0].length-1;k++){//判断某一行的数字
                int i=m;
                int j=k;
                while(i<matrix.length && j<matrix[0].length){//判断某一行某个数字所对应斜线上的数是否相同
                    if(i+1>=matrix.length||j+1>=matrix[0].length)//判断是否越界
                        break;
                    if(matrix[i][j]!=matrix[i+1][j+1])//如果不等直接返回false
                        return false;
                    i++;
                    j++;
                }
            }
        }
        return true;
    }
}
