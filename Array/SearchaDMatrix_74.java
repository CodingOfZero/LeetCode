package Array;

/**
 * 判断在二维数组中是否存在某个数，该二维数组行递增，列递增
 *
 */
public class SearchaDMatrix_74 {
    public static boolean searchMatrix(int[][] matrix, int target) {
         if(matrix==null||matrix.length==0) return false;
         int w=matrix[0].length;
         int h=matrix.length;
         int x=0,y=w-1;
         while (y>=0&&x<=h-1){
             int i=matrix[x][y];
             if(i>target){
                 y=y-1;
             }else if(i<target)
                 x=x+1;
             else
                 return true;
         }
         return false;
    }

    public static void main(String[] args) {
        int[][] m={{1,   3,  5,  7},{10, 11, 16, 20},{23, 30, 34, 50}};
        System.out.println("target:23  "+searchMatrix(m,23));
        System.out.println("target:11  "+searchMatrix(m,11));
        System.out.println("target:70  "+searchMatrix(m,70));
    }
}
