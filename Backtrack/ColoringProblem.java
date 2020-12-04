package Backtrack;

import java.util.Arrays;

/**
 * 给定一个无向图和一个数字m，请确定该图是否最多可以用m种颜色着色，以使该图的两个相邻顶点都不用相同颜色着色
 */
public class ColoringProblem {
    public static boolean graphColoring(int[][] graph,int m){
        int[] color=new int[graph.length];
        Arrays.fill(color,0);

        if(!ColoringHelper(graph,m,color,0)){
            System.out.println("not exist!");
            return false;
        }
        printSolution(color);
        return true;
    }

    private static boolean ColoringHelper(int[][] graph,int m,int[] color,int v) {
        if(v==graph.length){
            return true;
        }

        for(int c=1;c<=m;c++){
            if(isSafe(graph,v,color,c)){
                color[v]=c;
                if(ColoringHelper(graph,m,color,v+1)){
                    return true;
                }
                color[v]=0;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] graph, int v, int[] color, int c) {
        //遍历顶点v的邻居
        for(int i=0;i<graph.length;i++){
            //如果颜色c已经被分配了，返回false
            if(graph[v][i]==1&&color[i]==c){
                return false;
            }
        }
        return true;
    }

    private static void printSolution(int[] color) {
        Arrays.stream(color).forEach(c-> System.out.printf("%2d ",c));
    }

    public static void main(String[] args) {
        int[][] graph = {
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 1, 0 },
        };
        int m = 3;
        graphColoring(graph,m);
    }
}
