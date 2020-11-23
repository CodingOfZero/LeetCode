package Backtrack;

import java.util.Arrays;

/**
 * N皇后
 * 1）从左侧开始
 * 2）如果N个皇后全部放入且没有发生冲突则打印解决方案
 * 3）在当前行尝试所有列
 *       1）如果皇后可以安全的被放置在该列，则暂时标记它，作为解决方案的一部分
 *       然后递归地检查在此处放置皇后是否会产生一个有效的解决方案
 *       2）如果不能产生一个有效解，则撤销标记（回溯），然后尝试另外的列
 * 4）如果所有列都没有有效解，则返回false
 * ----->
 * 00(1)  01(0)   从上往下  所以只需要检查左边即可
 *        11(0)   00表示0行0列 括号里面的1表示放置一个皇后，左侧图示表示开始执行的流程
 *        21(1)   每新的一列都从头开始到行尾判断是否可以放置一个皇后
 */
public class NQueenProblem {
    /**
     * 用于检查是否可以将皇后放在board[row][col]上。
     * 注意，当 "col "的皇后已经被放置在0到col-1的列中时，这个函数会被调用。所以我们只需要检查左边的。
     */
    static boolean isSafe(int[][] board,int row,int col){
        //水平行
        for(int i=0;i<col;i++){
            if(board[row][i]==1){
                return false;
            }
        }
        //左上方斜对角线
        for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if(board[i][j]==1){
                return false;
            }
        }
        //左下方斜对角线
        for(int i=row+1,j=col-1;j>=0&&i<board.length;i++,j--){
            if(board[i][j]==1){
                return false;
            }
        }
        return true;
    }
    public static boolean solveQueen(int[][] board, int col){
        //检查0-N-1，完了之后如果都满足，此时col等于N
        if(col==board.length){
            //print
            printSolution(board);
            return true;
        }
        boolean res=false;
        for(int row=0;row<board.length;row++){
            //判断是否能够放置
            if(isSafe(board,row,col)){
                //放置一个皇后
                board[row][col]=1;
                //递归，判断是否能够产生一个有效解
                res=solveQueen(board,col+1)||res;
                //回溯,撤销标记
                board[row][col]=0;
            }
        }
        return res;
    }

    private static void printSolution(int[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] board=new int[4][4];
        if(!solveQueen(board,0)){
            System.out.println("解决方案不存在");
        }
    }
}
