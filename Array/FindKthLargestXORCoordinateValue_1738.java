package Array;

import java.util.PriorityQueue;

/**
 * 找出第K大的异或坐标值
 */
public class FindKthLargestXORCoordinateValue_1738 {
    public int kthLargestValueSecond(int[][] matrix, int k) {
        int m=matrix.length,n=matrix[0].length;
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        int[][] prefix=new int[m+1][n+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                prefix[i+1][j+1]=prefix[i][j+1]^matrix[i][j]^prefix[i+1][j]^prefix[i][j];
                minHeap.add(prefix[i+1][j+1]);
                if(minHeap.size()>k){
                    minHeap.poll();
                }
            }
        }
        return minHeap.poll();
    }
    public int kthLargestValue(int[][] matrix, int k) {
        int m=matrix.length,n=matrix[0].length;
        int[][] prefix=new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                prefix[i][j]=prefix[i][j-1]^matrix[i-1][j-1];
            }
        }
        for(int j=1;j<=n;j++){
            for(int i=1;i<=m;i++){
                prefix[i][j]^=prefix[i-1][j];
            }
        }
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                minHeap.add(prefix[i][j]);
                if(minHeap.size()>k){
                    minHeap.poll();
                }
            }
        }
        return minHeap.poll();
    }

    public static void main(String[] args) {
        FindKthLargestXORCoordinateValue_1738 test = new FindKthLargestXORCoordinateValue_1738();
        int i = test.kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 1);
        System.out.println(i);
    }
}
