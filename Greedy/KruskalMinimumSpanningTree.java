package Greedy;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 * 从边集合里面每次选最短且不构成回路的边   是否构成回路由路径压缩的并查集判断
 * 1）先以边的权重排序
 * 2）选权重最小的边，如果它不构成回路，则加入到结果集中
 * 3）重复步骤2，直到边数为V-1
 */
public class KruskalMinimumSpanningTree {

    static class UnionFind{
        int[] id;
        UnionFind(int n){
            id=new int[n];
            for(int i=0;i<n;i++){
                id[i]=i;
            }
        }
        public boolean isConnect(int p,int q){
            return find(p)==find(q);
        }
        int find(int p){
            int root=p;
            while (root!=id[root]) {
                root=id[root];
            }
            //将从p到根节点的路径上的每个触点都连接到根节点
            while (p!=root){
                int temp=id[p];
                id[p]=root;
                p=temp;
            }
            return root;
        }
        void union(int p,int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot!=qRoot){
                id[pRoot]=qRoot;
            }
        }
    }
    static public Edge[] kruskal(Graph graph){
        Edge[] result=new Edge[graph.V];
        int i;
        for (i = 0; i < graph.V; ++i) {
            result[i] = new Edge();
        }
        //1
        Arrays.sort(graph.edges);
        UnionFind unionFind=new UnionFind(graph.V);
        //最小生成树边为V-1
        int e=0;
        i=0;
        while (e<graph.V-1){
            Edge nextEdge=new Edge();
            nextEdge=graph.edges[i++];
            int src = unionFind.find(nextEdge.src);
            int dest = unionFind.find(nextEdge.dest);
            if(!unionFind.isConnect(src,dest)){
                unionFind.union(src,dest);
                result[e++]=nextEdge;
            }
        }
        return result;
    }
    static class Edge implements Comparable<Edge>{
        int src, dest, weight;
        @Override
        public int compareTo(Edge edge) {
            return this.weight-edge.weight;
        }
    }
    static class Graph{
        int V;
        int E;
        Edge[]  edges;
        Graph(int v,int e){
            V=v;
            E=e;
            edges=new Edge[E];
            for (int i = 0; i < e; ++i) {
                edges[i] = new Edge();
            }
        }
    }
    public static void main(String[] args)
    {

        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;
        graph.edges[0].weight = 10;

        // add edge 0-2
        graph.edges[1].src = 0;
        graph.edges[1].dest = 2;
        graph.edges[1].weight = 6;

        // add edge 0-3
        graph.edges[2].src = 0;
        graph.edges[2].dest = 3;
        graph.edges[2].weight = 5;

        // add edge 1-3
        graph.edges[3].src = 1;
        graph.edges[3].dest = 3;
        graph.edges[3].weight = 15;

        // add edge 2-3
        graph.edges[4].src = 2;
        graph.edges[4].dest = 3;
        graph.edges[4].weight = 4;

        // Function call
        Edge[] result = kruskal(graph);
        System.out.println("Following are the edges in "
                + "the constructed MST");
        int minimumCost = 0;
        int i;
        for (i = 0; i < graph.V-1; ++i)
        {
            System.out.println(result[i].src + " -- "
                    + result[i].dest
                    + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree "
                + minimumCost);
    }

}

