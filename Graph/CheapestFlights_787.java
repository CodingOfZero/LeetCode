package Graph;

import java.util.*;

/**
 * 最多经过k站中转的最便宜的价格
 * 回溯DFS,即便剪枝后效率仍然很低
 */
public class CheapestFlights_787 {
    //利用dijkstra堆优化算法
    public  int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 使用邻接表
        Map<Integer, List<int[]>> graph=new HashMap<>(n);
        for(int[] flight:flights){
            graph.computeIfAbsent(flight[0],item->new LinkedList<>()).add(new int[]{flight[1],flight[2]});
        }

        PriorityQueue<int[]> minHeap=new PriorityQueue<>((Comparator.comparingInt(o -> o[1])));
        minHeap.add(new int[]{src,0,K+1});
        while (!minHeap.isEmpty()){
            int[] poll = minHeap.poll();
            int node=poll[0],weight=poll[1],k=poll[2];
            if(node==dst){
                return weight;
            }
            if(k>0){
                //邻居
                if(graph.containsKey(node)){
                    for(int[] item:graph.get(node)){
                        minHeap.add(new int[]{item[0],weight+item[1],k-1});
                    }
                }
            }
        }
        return -1;
    }
    public  int findCheapestPriceMatrix(int n, int[][] flights, int src, int dst, int K) {
        // 使用邻接矩阵表示有向图，0 表示不连通
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        PriorityQueue<int[]> minHeap=new PriorityQueue<>((Comparator.comparingInt(o -> o[1])));
        minHeap.add(new int[]{src,0,K+1});
        while (!minHeap.isEmpty()){
            int[] poll = minHeap.poll();
            int node=poll[0],weight=poll[1],k=poll[2];
            if(node==dst){
                return weight;
            }
            if(k>0){
                //邻居
                for(int i=0;i<n;i++){
                    if(graph[node][i]>0){
                        minHeap.add(new int[]{i,weight+graph[node][i],k-1});
                    }
                }
            }
        }
        return -1;
    }


//    DFS
//    private int[][] graph;
//    private boolean[] visited;
//    private int res=Integer.MAX_VALUE;
//    public  int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
//        K=Math.min(K,n-2);
//        graph=new int[n][n];
//        for(int[] flight:flights){
//            graph[flight[0]][flight[1]]=flight[2];
//        }
//        visited=new boolean[n];
//        dfs(src,dst,K+1,0);
//        return res==Integer.MAX_VALUE?-1:res;
//    }
//    private void dfs(int src,int dst,int k,int cost){
//        if(src==dst){
//            res=cost;
//            return;
//        }
//        if(k==0) return;
//        for(int i=0;i<graph[src].length;i++){
//            if(graph[src][i]>0){
//                if(visited[i]) continue;
//                //剪枝
//                if(cost+graph[src][i]>res){
//                    continue;
//                }
//                visited[i]=true;
//                dfs(i,dst,k-1,cost+graph[src][i]);
//                visited[i]=false;
//            }
//        }
//    }

    public static void main(String[] args) {
//        int cheapestPrice = findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0);
//        System.out.println(cheapestPrice);
    }
}
