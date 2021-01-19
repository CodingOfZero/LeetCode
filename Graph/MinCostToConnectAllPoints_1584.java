package Graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 克鲁斯卡尔与并查集
 */
public class MinCostToConnectAllPoints_1584 {
    public static int minCostConnectPoints(int[][] points) {
        if(points==null) return 0;
        int len=points.length;
        PriorityQueue<int[]> queue=new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                queue.add(new int[]{
                        i,j,Math.abs(points[j][1]-points[i][1])+Math.abs(points[j][0]-points[i][0])
                });
            }
        }
        UnionFind unionFind=new UnionFind(len);
        int minDis=0;
        while (unionFind.getCount()!=1&&!queue.isEmpty()){
            int[] poll = queue.poll();
            int x=poll[0],y=poll[1];
            if(unionFind.isConnect(x,y)){
                continue;
            }
            unionFind.union(x,y);
            minDis+=poll[2];
        }
        return minDis;
    }
    static class UnionFind{
        int[] ids;
        int count;
        UnionFind(int len){
            ids=new int[len];
            for(int i=0;i<ids.length;i++){
                ids[i]=i;
            }
            count=len;
        }
        int getCount(){
            return count;
        }
        boolean isConnect(int v,int w){
            return find(v)==find(w);
        }
        public void union(int v,int w){
            int vRoot = find(v);
            int wRoot = find(w);
            if(vRoot==wRoot) return;
            ids[vRoot]=wRoot;
            count--;
        }
        public int find(int v) {
            int root=v;
            while (root!=ids[root]){ root=ids[root];}
            while (v!=root){
                int temp=ids[v];
                ids[v]=root;
                v=temp;
            }
            return root;
        }
    }
    public static void main(String[] args) {
        int[][] points=new int[][]{{2,-3},{-17,-8},{13,8},{-17,-15}};
        int i = minCostConnectPoints(points);
        System.out.println(i);
    }

}