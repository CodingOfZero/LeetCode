package Graph;

import java.util.*;

/**
 * 求最少需要多长时间所有节点都能接收到消息，即求所有节点最短距离中的最大值
 * 有向图的最短路径，利用dijkstra堆优化算法
 */
public class NetworkDelayTime_743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        //1.建立图
        Map<Integer, List<int[]>> graph=new HashMap<>();
        //[目标节点，边权]
        for(int[] time:times){
//            if(graph.containsKey(time[0])){
//                List<int[]> tmp = graph.get(time[0]);
//                tmp.add(new int[]{time[1],time[2]});
//                graph.put(time[0],tmp);
//            }else{
//                List<int[]> tmp=new LinkedList<>();
//                tmp.add(new int[]{time[1],time[2]});
//                graph.put(time[0],tmp);
//            }
            graph.computeIfAbsent(time[0],item->new LinkedList<>()).add(new int[]{time[1],time[2]});
        }
        //2.最小堆
        //[目标节点，边权]
        PriorityQueue<int[]> pq=new PriorityQueue<>((num1,num2)->num1[1]-num2[1]);
        pq.add(new int[]{k,0});
        //3.路径信息
        Map<Integer,Integer> dist=new HashMap<>();
        //4.从堆中每次选最小
        while (!pq.isEmpty()){
            int[] poll = pq.poll();
            int node=poll[0],weight=poll[1];
            if(dist.containsKey(node)) continue;
            dist.put(node,weight);
            //如果有出边
            if(graph.containsKey(node)){
                //邻居
                for(int[] item:graph.get(node)){
                    //如果邻居尚未处理
                    if(!dist.containsKey(item[0])){
                        pq.add(new int[]{item[0],item[1]+weight});
                    }
                }
            }
        }
        if(dist.size()!=n){
            return -1;
        }
        int res=0;
        for(int cand:dist.values()){
            res=Math.max(res,cand);
        }
        return res;
    }
}
