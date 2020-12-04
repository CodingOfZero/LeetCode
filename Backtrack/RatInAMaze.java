package Backtrack;

import java.util.Arrays;




/**
 * 老鼠迷宫问题简化版
 * 它只能往前或下方移动，从左上角开始到右下角截止
 */
public class RatInAMaze {
    public static void solveMaze(int[][] maze){
        int[][] sol=new int[maze.length][maze.length];
        for(int[] s:sol){
            Arrays.fill(s,0);
        }
        if(!mazeHelper(maze,0,0,sol)){
            System.out.println("not exist");
        }
        printSolution(sol);
    }
    private static boolean mazeHelper(int[][] maze,int i,int j,int[][] sol){
        if((i==maze.length-1)&&(j==maze.length-1)){
            sol[i][j]=1;
            return true;
        }
        sol[i][j]=1;
        if(j+1<maze.length&&maze[i][j+1]!=0){
            if(mazeHelper(maze,i,j+1,sol)){
                return true;
            }
        }
        if(i+1<maze.length&&maze[i+1][j]!=0){
            if(mazeHelper(maze,i+1,j,sol)){
                return true;
            }
        }
        sol[i][j]=0;
        return false;
    }
    private static void printSolution(int[][] maze) {
        for (int[] ints : maze) {
            for (int j = 0; j < maze.length; j++) {
                System.out.printf("%d ", ints[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] maze = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 1, 0, 0 },
                { 1, 1, 1, 1 } };

        int n = maze.length;
        solveMaze(maze);
    }
}
