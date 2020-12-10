package Backtrack;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 常规回溯题
 */
public class WordSearch_79 {
    private char fake='0';
    public boolean exist(char[][] board, String word) {
        if(word==null||word.length()==0||board==null||board.length==0){
            return false;
        }
        char[] words=word.toCharArray();
        char first=words[0];
        int row=board.length;
        int col=board[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                //找到路线开头
                if(board[i][j]==first){
                    if(existHelper(board,i,j,words,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean existHelper(char[][] board,int x,int y,char[] words,int cur){
        if(cur==words.length-1&&board[x][y]==words[cur]){
            return true;
        }
        char temp=board[x][y];
        board[x][y]=fake;
        //右
        if(y+1<board[0].length&&board[x][y+1]==words[cur+1]){
            if(existHelper(board,x,y+1,words,cur+1)){
                return true;
            }
        }
        //下
        if(x+1<board.length&&board[x+1][y]==words[cur+1]){
            if(existHelper(board,x+1,y,words,cur+1)){
                return true;
            }
        }
        //左
        if(y-1>=0&&board[x][y-1]==words[cur+1]){
            if(existHelper(board,x,y-1,words,cur+1)){
                return true;
            }
        }
        //上
        if(x-1>=0&&board[x-1][y]==words[cur+1]){
            if(existHelper(board,x-1,y,words,cur+1)){
                return true;
            }
        }
        board[x][y]=temp;
        return false;
    }
}
