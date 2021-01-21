package Graph;

import java.util.*;

/**
 * 枚举+克鲁斯卡尔
 * 关键边：
 *      当删除该条边时，可能导致图不连通，或者图连通但是生成树的权值增大
 * 非关键边：
 *      可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边
 *      我们可以计算生成树的过程中，先考虑这条边，如果生成树的权值与先前计算的最小生成树权值相等
 *      那么该条边就是伪关键边
 *      由于关键边也符合这条规律，所以先判断是否是关键边，如果不是，然后再判断是否是非关键边
 *
 */
public class FindMSTKeyEdge_1489 {
    /**
     * 基于路径压缩的并查集时间复杂度为阿克曼函数的反函数
     * 时间复杂度：O(m^2* a(n))  一次Kruskal算法的时间复杂度为O(m⋅α(n))，其中a(n)阿克曼函数的反函数
     *          我们最多需要执行 2m + 1次Kruskal 算法
     * 空间复杂度：O(m+n)进行排序时，我们必须要额外存储每条边原始的编号，用来返回答案，空间复杂度为 O(m)
     *          Kruskal算法中的并查集需要使用O(n) 的空间
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int len= edges.length;
        //比原始数据多一列，用来保存边的序号
        int[][] newEdges=new int[len][4];
        for(int i=0;i<len;i++){
            for(int j=0;j<edges[0].length;j++){
                newEdges[i][j]=edges[i][j];
            }
            newEdges[i][3]=i;
        }
        //按权值对边进行排序
        Arrays.sort(newEdges, Comparator.comparingInt(k -> k[2]));
        //计算MST权值和
        UnionFind uf=new UnionFind(n);
        int value=0;
        for(int i=0;i<len;i++){
            int from=newEdges[i][0],to=newEdges[i][1];
            if(uf.isConnect(from,to)){
                continue;
            }
            uf.union(from,to);
            value+=newEdges[i][2];
        }
        //结果列表初始化
        List<List<Integer>> results=new LinkedList<>();
        for(int i=0;i<2;i++){
            results.add(new LinkedList<>());
        }
        //
        for(int i=0;i<len;i++){
            //先判断是否为关键边
            UnionFind uf1=new UnionFind(n);
            //不加入该条边，看能否连通，或者连通后权值是否变大，如果是，则表明是关键边
            int v=0;
            for(int j=0;j<len;j++){
                if(i!=j){
                    int from=newEdges[j][0],to=newEdges[j][1];
                    if(uf1.isConnect(from,to)){
                        continue;
                    }
                    uf1.union(from,to);
                    v+=newEdges[j][2];
                }
            }
            if(uf1.getCount()!=1||v>value){
                results.get(0).add(newEdges[i][3]);
                continue;
            }
            //判断是否为伪关键边
            UnionFind uf2=new UnionFind(n);
            //先将这条边加入到并查集中
            uf2.union(newEdges[i][0],newEdges[i][1]);
            v=newEdges[i][2];

            for(int j=0;j<len;j++){
                if(i!=j){
                    int from=newEdges[j][0],to=newEdges[j][1];
                    if(uf2.isConnect(from,to)){
                        continue;
                    }
                    uf2.union(from,to);
                    v+=newEdges[j][2];
                }
            }
            if(v==value){
                results.get(1).add(newEdges[i][3]);
            }
        }
        return results;
    }
    static class UnionFind{
        private int[] ids;
        private int count;
        UnionFind(int n){
            ids=new int[n];
            count=n;
            for(int i=0;i<n;i++){
                ids[i]=i;
            }
        }
        public boolean isConnect(int v,int w){
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
        public int getCount(){return count;}
    }
}
