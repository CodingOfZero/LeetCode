package Backtrack;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 使用Warnsdorff启发式规则来处理骑士问题，该算法形成了一个哈密顿回路
 * 注意：前面问题不需要回路
 *  1）可以从棋盘上的任意位置开始
 *  2）每次总是以最小的度数移动到相邻的、未被访问的方块（未被访问的相邻方块的最小数量）
 *
 *
 */
public class TheKnightTourProblemFaster {
//    private  final int[] xMove={2,1, 1, 2, -1, -2, -1,-2};
//    private  final int[] yMove={1,2,-2,-1, -2, -1,  2, 1};
    private  final int[] xMove= {1, 1, 2, 2, -1, -1, -2, -2};
    private  final int[] yMove= {2, -2, 1, -1, 2, -2, 1, -1};
    private  int[][] sol;
    private  int len;

    TheKnightTourProblemFaster(int length){
        sol=new int[length][length];
        len=length;
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                sol[i][j]=-1;
            }
        }
    }

    /**
     * 判断某个位置是否被访问过
     */
    private  boolean isSafe(int x,int y){
        return (x>=0&&x<len&&y>=0&&y<len&&sol[x][y]==-1);
    }

    /**
     * 求每个位置的度，度定义为从该点可进行访问的位置个数
     */
    private int getDegree(int x,int y){
        int count=0;
        for(int i=0;i<len;i++){
            if(isSafe(x+xMove[i],y+yMove[i])){
                count++;
            }
        }
        return count;
    }
    /**
     * 打印解决方案
     */
    private  void printSolution(){
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                System.out.printf("%-3d",sol[i][j]);
            }
            System.out.println();
        }
    }
    /**
     * 包装x与y坐标，用于保存下一个访问位置
     */
    static class Cell{
        int x;
        int y;
        Cell(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    /**
     * 返回下一个访问位置
     */
    private Cell nextMove(Cell cell){
        //nx,ny表示下一个位置坐标  c为某个位置的度数
        int minDegIdx = -1, c, minDeg = (len + 1), nx, ny;
        //随机选取一个起始点
        int start= ThreadLocalRandom.current().nextInt(1000)%len;
        //在方向数组里面选择最小度数
        //从一个随机相邻开始，尝试所有len个(x, y)的相邻。寻找最小度的相邻点
        //当周围的度都相同时，选择起始点不同最后路线也不同
        for(int i=0;i<len;i++){
            int index=(start+i)%len;
            nx=cell.x+xMove[index];
            ny=cell.y+yMove[index];
            if(isSafe(nx,ny)&&(c=getDegree(nx,ny))<minDeg){
                minDegIdx=index;
                minDeg=c;
            }
        }
        //如果没有发现下一个有效位置
        if(minDegIdx==-1){
            return null;
        }
        nx = cell.x + xMove[minDegIdx];
        ny = cell.y + yMove[minDegIdx];
        sol[nx][ny]=sol[cell.x][cell.y]+1;
        cell.x=nx;
        cell.y=ny;
        return cell;
    }

    /**
     * 如果马在距离开始方格一马之遥的方格上结束，那么巡回赛就结束了。
     */
    private boolean neighbour(int sx, int sy, int ex, int ey){
        for(int i=0;i<len;i++){
            if((sx+xMove[i])==ex&&(sy+yMove[i])==ey){
                return true;
            }
        }
        return false;
    }
    public boolean findClosedTour(){
        //起始点
        int sx=3,sy=2;
        Cell cell=new Cell(sx,sy);
        sol[sx][sy]=1;

        Cell res=null;
        for(int i=0;i<len*len-1;i++){
            if((res=nextMove(cell))==null){
                return false;
            }
        }
        //检查是否已闭合，构成一个哈密顿回路
        if(!neighbour(res.x,res.y,sx,sy)){
            return false;
        }
        printSolution();
        return true;
    }

    public static void main(String[] args) {
        TheKnightTourProblemFaster faster = new TheKnightTourProblemFaster(8);
        while (!faster.findClosedTour()){

        }
//        faster.findClosedTour();
        //运算结果如下：随机输出一个正确方案
//        60 43 16 21 58 33 14 23
//        17 2  59 44 15 22 35 32
//        42 61 20 55 34 57 24 13
//        3  18 1  62 45 50 31 36
//        64 41 54 19 56 37 12 25
//        7  4  63 46 51 28 49 30
//        40 53 6  9  38 47 26 11
//        5  8  39 52 27 10 29 48

    }
}
