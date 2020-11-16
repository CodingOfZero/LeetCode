package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Prim算法
 * 利用一个最小堆
 * 1）创建大小为V的最小堆，其中V是给定图中的顶点数。最小堆的每个节点都包含顶点号和顶点的键值。
 * 2）以第一个顶点为根初始化Min Heap（分配给第一个顶点的键值为0）。分配给所有其他顶点的键值为INF（无穷大）。
 * 3）当“最小堆”不为空时
 *      a）从“最小堆”中提取“最小值”节点。令提取的顶点为u。
 *      b）对于u的每个相邻顶点v，检查v是否在Min Heap中（尚未包含在MST中）。
 *      如果v在“最小堆”中，并且其键值大于uv的权重，则将v的键值更新为uv的权重。
 */
public class PrimMST {
    static class Graph{
        int V;
        ArrayList<ArrayList<node>> adj;

        public Graph(int v){
            this.V=v;
            adj=new ArrayList<>();
            for(int i=0;i<v;i++){
                adj.add(new ArrayList<>());
            }
        }
        static class node{
            int des;
            int weight;
            public node(int v,int w){
                this.des=v;
                this.weight=w;
            }
        }
        void addEdge(int v,int u,int w){
            adj.get(v).add(new node(u,w));
            adj.get(u).add(new node(v,w));
        }
    }


    static class FakeNode implements Comparable<FakeNode>{
        int key;
        int vertex;

        @Override
        public boolean equals(Object obj) {
            return this == obj;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public int compareTo(FakeNode o) {
            return this.key-o.key;
        }
    }
    static void prim(Graph graph){
        //某个节点是否被访问
        Boolean[] isAccess=new Boolean[graph.V];
        //构造一个与图中节点相对应的节点集合
        FakeNode[] fakeNodes=new FakeNode[graph.V];
        for (int o = 0; o < graph.V; o++) {
            fakeNodes[o] = new FakeNode();
        }
        //结果数组
        int[] parent=new int[graph.V];
        //将伪造的节点集合中的每个节点值设置为INF，并进行其他初始化
        for(int i=0;i<graph.V;i++){
            isAccess[i]=false;
            fakeNodes[i].key=Integer.MAX_VALUE;
            fakeNodes[i].vertex=i;
            parent[i]=-1;
        }
        //将第一个顶点设置为起点
        fakeNodes[0].key=0;

        //将伪造的节点放置到有序树中
        TreeSet<FakeNode> queue = new TreeSet<FakeNode>(Arrays.asList(fakeNodes));

        while (!queue.isEmpty()){
            //选出最小的
            FakeNode fakeNode = queue.pollFirst();
            isAccess[fakeNode.vertex]=true;

            //判断它的相邻顶点，检查是否在TreeSet中
            for(Graph.node node :graph.adj.get(fakeNode.vertex)){
                //成立表明相邻顶点还未访问
                if(!isAccess[node.des]){
                    if(node.weight<fakeNodes[node.des].key){
                        queue.remove(fakeNodes[node.des]);
                        //更新
                        fakeNodes[node.des].key=node.weight;
                        queue.add(fakeNodes[node.des]);
                        //设置父节点
                        parent[node.des]=fakeNode.vertex;
                    }
                }
            }
        }

        //打印结果
        for (int o = 1; o < graph.V; o++)
            System.out.println(parent[o] + " "
                    + "-"
                    + " " + o);

    }
    public static void main(String[] args)
    {
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

        // Method invoked
        prim(graph);
    }

}
