package Array;


/**
 *生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix
 */
public class SpiralMatrixII_59 {
    public static int[][] generateMatrix(int n) {
        int[][] ans=new int[n][n];
        int start=0;
        int index=1;
        while(start*2<n){
            int end=n-start-1;
            for(int i=start;i<=end;i++){
                ans[start][i]=index++;
            }
            if(start<end){
                for(int i=start+1;i<=end;i++){
                    ans[i][end]=index++;
                }
            }
            if(start<end){
                for(int i=end-1;i>=start;i--){
                    ans[end][i]=index++;
                }
            }
            if(start<end-1){
                for(int i=end-1;i>start;i--){
                    ans[i][start]=index++;
                }
            }
            start++;
        }
        return ans;
    }
    public static void main(String[] args) {
        generateMatrix(3);
    }

}
