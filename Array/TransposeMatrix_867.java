import java.util.Arrays;
/**
 * 矩阵转置
 * */
public class TransposeMatrix_867 {
    public static void main(String[] args){
        int[][] A={{1,2,3},{4,5,6},{7,8,9}};
       // int[][] B=new int[1005][1005];
        int[][] B=new int[A[0].length][A.length];
        for(int i=0;i<A[0].length;i++){
            for(int j=0;j<A.length;j++){
                B[i][j]=A[j][i];
            }
        }
        System.out.println(Arrays.deepToString(B));

    }
}
