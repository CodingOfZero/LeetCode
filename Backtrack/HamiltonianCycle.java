package Backtrack;

import java.util.Arrays;
import java.util.Random;

/**
 * 哈密顿环
 * 输入：graph[v][v]
 * 输出：如果存在则打印路径path[v]，否则返回false
 */
public class HamiltonianCycle {

    private boolean[] visited;
    private int[] path;
    private int v;
    private int[][] graph;
    HamiltonianCycle(int[][] graph){
        this.v=graph.length;
        this.graph=graph;
        visited=new boolean[v];
        path=new int[v];
        Arrays.fill(visited,false);
        Arrays.fill(path,-1);
    }
    private void printSolution(){
        Arrays.stream(path).forEach(System.out::println);
        System.out.println(path[0]);
    }
    private boolean isSafe(int v,int pos){
        //判断有没有边
        if(graph[path[pos-1]][v]==0){
            return false;
        }
        //判断是否已经访问过
        for(int i=0;i<pos;i++){
            if(path[i]==v){
                return false;
            }
        }
        return true;
    }

    /**
     * @param pos 下个可用下标
     * @return
     */
    public boolean hamiltonianCycleHelper(int pos){
        if(pos==v){
            return graph[path[pos - 1]][path[0]] == 1;
        }
        //我们已经将顶点0放入到路径当中，所以只需要判断其余顶点即可
        for(int i=1;i<v;i++){
            if(isSafe(i,pos)){
                path[pos]=i;
                if(hamiltonianCycleHelper(pos+1)){
                    return true;
                }
                //如果不能产生一个结果，则移除
                path[pos]=-1;
            }
        }
        return false;
    }
    public boolean hamCycle(){
        path[0]=0;
        if(!hamiltonianCycleHelper(1)){
            System.out.println("不存在");
            return false;
        }
        printSolution();
        return true;
    }

    public static void main(String[] args) {
        int[][] graph1 = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };
        int[][] graph2 = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };

        HamiltonianCycle h1 = new HamiltonianCycle(graph1);
        HamiltonianCycle h2 = new HamiltonianCycle(graph2);
        h1.hamCycle();
        System.out.println("-------------------------");
        h2.hamCycle();
    }
}
