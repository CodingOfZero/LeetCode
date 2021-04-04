package Backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SudokuSolver_37 {
    //行
    private boolean[][] row=new boolean[9][9];
    //列
    private boolean[][] col=new boolean[9][9];
    //3X3
    private boolean[][][] block=new boolean[3][3][9];
    private boolean vaild=false;
    private List<int[]> space=new ArrayList<>();

    public void solveSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    space.add(new int[]{i,j});
                }else{
                    int digit=board[i][j]-'0'-1;
                    row[i][digit]=col[j][digit]=block[i/3][j/3][digit]=true;
                }
            }
        }
        dfs(board,0);
    }
    private void dfs(char[][] board, int pos){
        if(pos==space.size()){
            vaild=true;
            return;
        }
        int[] tmp=space.get(pos);
        int i=tmp[0],j=tmp[1];
        for(int digit=0;digit<9&&!vaild;digit++){
            if(!row[i][digit]&&!col[j][digit]&&!block[i/3][j/3][digit]){
                row[i][digit]=col[j][digit]=block[i/3][j/3][digit]=true;
                board[i][j]=(char) (digit+'0'+1);
                dfs(board,pos+1);
                row[i][digit]=col[j][digit]=block[i/3][j/3][digit]=false;
            }
        }
    }
}
