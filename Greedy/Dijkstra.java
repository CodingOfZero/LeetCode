package Greedy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

/**
 * Dijkstra算法求最短路径
 * 与Prim算法比较相似
 * 1）创建一个Set（最短路径树集合），该集合跟踪最短路径树中包含的顶点，最初，此集合为空。
 * 2）为输入图中的所有顶点分配一个距离值。将所有距离值初始化为INFINITE。将源顶点的距离值指定为0，以便首先选择它。
 * 3）当Set不包括所有顶点
 *    a）选择一个在Set中不存在的顶点u并具有最小距离值。
 *    b）将u包含到Set中。
 *    c）更新u的所有相邻顶点的距离值。要更新距离值，遍历所有相邻的顶点。对于每个相邻顶点v，如果u（距源）的距离值和边缘uv的权重之和小于v的距离值，则更新v的距离值。
 */
public class Dijkstra {
    //邻接表
    static class Graph{
        int V;
        ArrayList<ArrayList<Node>> adj;

        public Graph(int v){
            this.V=v;
            adj=new ArrayList<>();
            for(int i=0;i<v;i++){
                adj.add(new ArrayList<>());
            }
        }
        static class Node{
            int des;
            int weight;
            public Node(int v,int w){
                this.des=v;
                this.weight=w;
            }
        }
        void addEdge(int v,int u,int w){
            adj.get(v).add(new Node(u,w));
            adj.get(u).add(new Node(v,w));
        }
    }
    //从距离数组中找到未访问的且距离最小的
    private static int minDistance(int[] dist, boolean[] isAccess) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!isAccess[v]  && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    private static int NO_PARENT=-1;
    public static void dijkstra(Graph graph,int src){
        boolean[] isAccess=new boolean[graph.V];
        //存放从src到其余点的最短距离
        int[] dist=new int[graph.V];
        //存放路径
        int[] parent=new int[graph.V];
        parent[src]=NO_PARENT;
        //初始化
        for(int i=0;i<graph.V;i++){
            dist[i]=Integer.MAX_VALUE;
            isAccess[i]=false;
        }
        dist[src]=0;

        for(int i=1;i<graph.V;i++){
            int u=minDistance(dist,isAccess);
            isAccess[u]=true;
            //遍历所有相邻的顶点
            int neighborNodesLen = graph.adj.get(u).size();
            for(Graph.Node node: graph.adj.get(u)){
                int v=node.des;
                if(!isAccess[v]&&dist[u]+node.weight<dist[v]){
                    dist[v]=dist[u]+node.weight;
                    parent[v]=u;
                }
            }
        }
        printSolution(dist,parent,src);
    }
    //打印结果
    private static void printSolution(int[] dist,int[] parent,int src) {
        System.out.print("Vertex\t Distance\tPath");
        for (int dest = 0; dest < dist.length; dest++) {
            if(dest!=src){
                System.out.print("\n" + src + " -> ");
                System.out.print(dest + " \t\t ");
                System.out.print(dist[dest] + "\t\t");
                printPath(dest, parent);
            }
        }
    }


    //打印路径
    private static void printPath(int currentVertex, int[] parents) {
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }
    public static void main(String[] args) {
        int V = 9;

        Graph graph = new Graph(V);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge( 2, 5, 4);
        graph.addEdge( 3, 4, 9);
        graph.addEdge( 3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge( 6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        dijkstra(graph,0);
    }

}
