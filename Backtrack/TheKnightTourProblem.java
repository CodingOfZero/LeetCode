package Backtrack;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * 国际象棋里面骑士跳法和中国象棋里面马跳法相似，它有8种选择
 * 给定一个N * N板，骑士放置在空板的第一块上。
 * 根据国际象棋骑士的规则移动必须对每个广场精确地访问一次。打印访问它们的每个单元格的顺序。
 * 最差的运行时间为O（8 N ^ 2）
 * 回溯并非解决该办法的好方法
 */
public class TheKnightTourProblem {
    private int len;
    private int[][] sol;
    public TheKnightTourProblem(int length){
        this.len=length;
        sol=new int[length][length];
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                sol[i][j]=-1;
            }
        }
    }
    /**
     * 判断某个位置是否有效
     */
    private  boolean isSafe(int x,int y){
        return (x>=0&&x<len&&y>=0&&y<len&&sol[x][y]==-1);
    }

    /**
     * 打印解决方案
     */
    private  void printSolution(){
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                System.out.print(sol[i][j]+" ");
            }
            System.out.println();
        }
    }

    public  boolean solveKT(){
        sol[0][0]=0;
        //骑士移动方向坐标  xMove为X坐标，yMove为Y坐标，共8种
        //用以下顺序速度将很慢,几小时还没出来
//        int[] xMove={2,1, 1, 2, -1, -2, -1,-2};
//        int[] yMove={1,2,-2,-1, -2, -1,  2, 1};
        /*启发式
        xMove，yMove的顺序将极大地影响算法的运行时间
        例如，考虑以下情况：第8个选择是正确的选择，
         与尝试随机回溯相比，具有启发性总是一个好主意。
         就像在这种情况下，我们知道下一步可能在南向或东向，然后检查通向第一条路线的路径是一个更好的策略。
         通过测试发现按以下数组顺序运行程序，程序使用了339ms*/
        int[] xMove = { 2, 1, -1, -2, -2, -1,  1,  2 };
        int[] yMove = { 1, 2,  2,  1, -1, -2, -2, -1 };
        if (solveKTHelper(0, 0, 1, xMove, yMove)) {
            printSolution();
        } else {
            System.out.println("不存在");
            return false;
        }
        return true;
    }

    /**
     *
     * @param x ：开始点的横坐标
     * @param y ：纵坐标
     * @param movei：已经经过的位置的个数
     * @param xMove：移动方向
     * @param yMove：移动方向
     */
    private boolean solveKTHelper(int x,int y,int movei,int[] xMove,int[]yMove){
        //所有位置均被访问了
        if(movei==len*len){
            return true;
        }
        int k,nextX,nextY;
        for(k=0;k<8;k++){
            nextX=x+xMove[k];
            nextY=y+yMove[k];
            if(isSafe(nextX,nextY)){
                sol[nextX][nextY]=movei;
                if(solveKTHelper(nextX,nextY,movei+1,xMove,yMove)) {
                    return true;
                } else {
                    //回溯
                    sol[nextX][nextY]=-1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TheKnightTourProblem knight = new TheKnightTourProblem(8);
//        long start = System.currentTimeMillis();
        Instant now = Instant.now();
        knight.solveKT();
        long used = ChronoUnit.SECONDS.between(now, Instant.now());
//        long end=System.currentTimeMillis();
        System.out.println("共耗费 "+ used+"ms");
    }
}
