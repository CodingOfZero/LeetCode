package Backtrack;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 在迷宫中从四个角落中的任意一个出发，搜索到达中间顶点的所有路径，它可以从东南西北四个方向精确地移动n步，其中n是该单元格的值
 */
public class MazeProblem {
    private int len;
    private int[][] maze;
    private int[] row={0,1,-1, 0};
    private int[] col={1,0, 0,-1};
//    private int[] rowPoint={0,0,len-1,len-1};//错误写法，len默认为0，这样就不是四个边角点了
//    private int[] colPoint={0,len-1,0,len-1};//同上

    public MazeProblem(int[][] maze){
        this.len=maze.length;
        this.maze=maze;
    }

    private void printSolution(List<Pair<Integer,Integer>> path){
        path.forEach(k->{
            System.out.println("("+k.getKey()+","+k.getValue()+")-->");
        });
        System.out.println("MID");
    }

    private boolean isValid(Set<Pair<Integer,Integer>> visited,Pair<Integer,Integer> pt){
        int x=pt.getKey();
        int y=pt.getValue();
        return ((x >= 0)&&(x<len)&&(y>=0)&&(y<len)&&(!visited.contains(pt)));
    }

    private void pathHelper(List<Pair<Integer,Integer>> path,
                               Set<Pair<Integer,Integer>> visited,
                               Pair<Integer,Integer> cur){
        if(cur.getKey()==len/2&&cur.getValue()==len/2){
            printSolution(path);
            return;
        }

        for(int i=0;i<4;i++){
            int x=cur.getKey();
            int y=cur.getValue();
            int n=maze[x][y];

            int nx=x+row[i]*n;
            int ny=y+col[i]*n;
            Pair<Integer, Integer> pt = new Pair<>(nx, ny);
            if(isValid(visited,pt)){
                visited.add(pt);
                path.add(pt);
                pathHelper(path,visited,pt);
                path.remove(pt);
                visited.remove(pt);
            }
        }
    }

    public void pathMaze(){
        List<Pair<Integer,Integer>> path=new LinkedList<>();
        Set<Pair<Integer,Integer>> visited=new HashSet<>();
        int[] rowPoint={0,0,len-1,len-1};
        int[] colPoint={0,len-1,0,len-1};
        for(int i=0;i<4;i++){
            int x=rowPoint[i];
            int y=colPoint[i];
            Pair<Integer, Integer> pt = new Pair<>(x, y);
            visited.add(pt);
            path.add(pt);
            pathHelper(path,visited,pt);
            path.remove(pt);
            visited.remove(pt);
        }
    }
    //使用该方法
    public static void printPathMaze(int[][] maze,int i,int j, String res){
        if(i==maze.length/2&&j==maze.length/2){
            res+="("+i+","+j+")-->MID";
            System.out.println(res);
            return;
        }
        //如果访问过将迷宫的值设置为0，
        //先取出坐标内的值
        int k=maze[i][j];
        //表明已访问过
        if(k==0){
            return;
        }
        maze[i][j]=0;
        //south
        if(j+k<maze.length){
            printPathMaze(maze,i,j+k,res+"("+i+","+j+")-->");
        }
        //east
        if(i+k<maze.length){
            printPathMaze(maze,i+k,j,res+"("+i+","+j+")-->");
        }
        //west
        if(i-k>=0){
            printPathMaze(maze,i-k,j,res+"("+i+","+j+")-->");
        }
        //north
        if(j-k>=0){
            printPathMaze(maze,i,j-k,res+"("+i+","+j+")-->");
        }
        //还原
        maze[i][j]=k;
    }
    public static void main(String[] args) {
        int[][] maze =
        {
            { 3, 5, 4, 4, 7, 3, 4, 6, 3 },
            { 6, 7, 5, 6, 6, 2, 6, 6, 2 },
            { 3, 3, 4, 3, 2, 5, 4, 7, 2 },
            { 6, 5, 5, 1, 2, 3, 6, 5, 6 },
            { 3, 3, 4, 3, 0, 1, 4, 3, 4 },
            { 3, 5, 4, 3, 2, 2, 3, 3, 5 },
            { 3, 5, 4, 3, 2, 6, 4, 4, 3 },
            { 3, 5, 1, 3, 7, 5, 3, 6, 4 },
            { 6, 2, 4, 3, 4, 5, 4, 5, 1 }
        };
        Backtrack.MazeProblem mazeProblem = new Backtrack.MazeProblem(maze);
        mazeProblem.pathMaze();


//        int len=maze.length;
//        int[] rowPoint={0,0,len-1,len-1};
//        int[] colPoint={0,len-1,0,len-1};
//        for(int i=0;i<4;i++) {
//            int x = rowPoint[i];
//            int y = colPoint[i];
//            printPathMaze(maze,x,y,"");
//        }

    }
}
