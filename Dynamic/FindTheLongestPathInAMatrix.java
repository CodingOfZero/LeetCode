package Dynamic;

import java.util.Arrays;

/**
 * 给一个矩阵，找到从某个点出发，它的下一结点与它相差1的路径的最大长度
 * Input:  mat[][] = {{1, 2, 9}
 *                    {5, 3, 8}
 *                    {4, 6, 7}}
 * Output: 4
 * The longest path is 6-7-8-9.
 * 思路：从每个点开始，找到最长路径，然后从其中选择最长路径。
 * 从每个点开始，有四个方法，从其中找到最长路径
 * 以下方法时间复杂度为O(n^2)乍看起来似乎更多。如果仔细研究，我们会发现dp [i] [j]的所有值仅计算一次。
 */
public class FindTheLongestPathInAMatrix {
    /**
     * 从单个点出发，找到的最长路径长度
     * @param mat   矩阵
     * @param table 存放对应最长路径长度
     * @param i 行
     * @param j 列
     * @return 最长路径长度
     */
    public static int findMaxPathFromACell(int[][] mat,int[][] table,int i,int j){
        if(mat==null) {
            return 0;
        }
        //nxn矩阵
        int n=mat.length;
        if(i<0||i>=n||j<0||j>=n){
            return 0;
        }
        if(table[i][j]!=-1) {
            return table[i][j];
        }
        int x=Integer.MIN_VALUE,y=Integer.MIN_VALUE,z=Integer.MIN_VALUE,w=Integer.MIN_VALUE;
        //四个方向
        if(j<n-1&&(mat[i][j]+1)==mat[i][j+1]){
            x=table[i][j]=1+findMaxPathFromACell(mat,table,i,j+1);
        }
        if(j>0&&(mat[i][j]+1)==mat[i][j-1]){
            y=table[i][j]=1+findMaxPathFromACell(mat,table,i,j-1);
        }
        if(i<n-1&&(mat[i][j]+1)==mat[i+1][j]){
            z=table[i][j]=1+findMaxPathFromACell(mat,table,i+1,j);
        }
        if(i>0&&(mat[i][j]+1)==mat[i-1][j]){
            w=table[i][j]=1+findMaxPathFromACell(mat,table,i-1,j);
        }
        //如果相邻的4个方向都不大于1，我们就取1
        //否则选择四个方向最大的值
        return table[i][j]=Math.max(x,Math.max(y,Math.max(z,Math.max(w,1))));
    }

    public static int findMaxPathAll(int[][] mat){
        int n=mat.length;
        int[][] table=new int[n][n];
        //初始化
        for(int[] arr:table){
            Arrays.fill(arr,-1);
        }
        int result=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(table[i][j]==-1){
                    findMaxPathFromACell(mat,table,i,j);
                }
                //如果需要，进行更新
                result=Math.max(result,table[i][j]);
            }
        }
        return result;
    }
    public static void main(String[] args)
    {
        int mat[][] = { { 1, 2, 9 },
                { 5, 3, 8 },
                { 4, 6, 7 } };
        System.out.println("Length of the longest path is " + findMaxPathAll(mat));
    }
}
