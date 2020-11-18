package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 图形着色问题  该类问题是NP完全问题，尚未找到多项式时间内的有效算法
 * 顶点着色
 * 使用贪婪思想，该实现不能保证使用最少的颜色，但可以保证颜色数量的上限，
 * 永远不会使用超过d + 1种颜色 d是给定图中顶点的最大度
 * 1. 用第一个颜色给第一个顶点上色。
 * 2. 对剩下的V-1顶点做如下操作。
 *     考虑当前所选的顶点，用最低编号的颜色给它上色，该颜色没有在它相邻的任何先前上色的顶点上使用过。
 *     如果所有之前使用过的颜色都出现在与 v 相邻的顶点上，则给它分配一个新的颜色。
 * 选择顶点的顺序很重要。可利用Welsh–Powell Algorithm提高效率，该算法以度的降序考虑顶点
 * 按顶点的度的大小来依次染色，使用到的颜色要少
 */
public class GraphColor {
    //无向图的邻接表表示
    //顶点
    private int V;
    private ArrayList<LinkedList<Integer>>adj;

    GraphColor(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; ++i) {
            adj.add(new LinkedList<>());
        }
    }

    void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    void greedyColoring() {
        //每个顶点染色的结果
        int[] result = new int[V];
        Arrays.fill(result, -1);
        //分配第一个顶点第一个颜色
        result[0]=0;

        //可用的颜色，如果为false表明颜色被分配给相邻的一个顶点
        boolean[] available=new boolean[V];
        Arrays.fill(available,true);

        for(int u=1;u<V;u++){
            Iterator<Integer> iterator = adj.get(u).iterator();
            //设置它邻居用了哪些颜色
            while (iterator.hasNext()){
                int neighbor=iterator.next();
                if(result[neighbor]!=-1) {
                    available[result[neighbor]]=false;
                }
            }
            //找到可用颜色
            int cr;
            for(cr=0; cr<V;cr++){
                if(available[cr]) {
                    break;
                }
            }
            result[u]=cr;
            //重置可用颜色，为下次迭代做准备
            Arrays.fill(available,true);
        }
        for(int u=0;u<V;u++){
            System.out.println("Vertex "+u+"---> Color" +result[u]);
        }
    }

    public static void main(String[] args) {
        GraphColor g1 = new GraphColor(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        System.out.println("Coloring of graph 1");
        g1.greedyColoring();

        System.out.println();
        GraphColor g2 = new GraphColor(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 2);
        g2.addEdge(1, 4);
        g2.addEdge(2, 4);
        g2.addEdge(4, 3);
        System.out.println("Coloring of graph 2 ");
        g2.greedyColoring();
    }
}
