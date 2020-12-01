package Backtrack;

/**
 * 数独
 */
public class Sudoku {
    public static boolean solveSudoku(int[][] board,int n){
        int row=-1;
        int col=-1;
        boolean flag=false;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==0){
                    row=i;
                    col=j;
                    flag=true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }

        if(!flag){
            return true;
        }

        for(int num=1;num<=n;num++){
            if(isSafe(board,row,col,num)){
                board[row][col] = num;
                if(solveSudoku(board,n)){
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private static void printSolution(int[][] board,int n) {
        for (int r = 0; r < n; r++)
        {
            for (int d = 0; d < n; d++)
            {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        int n=board.length;
        //行
        for(int j=0;j<n;j++){
            if(board[row][j]==num){
                return false;
            }
        }
        //列
        for(int i=0;i<n;i++){
            if(board[i][col]==num){
                return false;
            }
        }
        //每个小矩形
        int sqrt=(int)Math.sqrt(n);
        int rowStart=row-row%sqrt;
        int colStart=col-col%sqrt;
        for(int r=rowStart;r<rowStart+sqrt;r++){
            for(int c=colStart;c<colStart+sqrt;c++){
                if(board[r][c]==num){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = new int[][] {
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
        };
        int N = board.length;

        if(solveSudoku(board,N)){
            printSolution(board,N);
        }else{
            System.out.println("don't exist solution");
        }


    }
}
